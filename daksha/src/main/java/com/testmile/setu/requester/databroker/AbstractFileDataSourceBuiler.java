package com.testmile.setu.requester.databroker;

import java.util.*;

import com.testmile.daksha.tpi.ddauto.ListDataRecord;
import com.testmile.daksha.tpi.ddauto.ListDataSource;
import com.testmile.daksha.tpi.ddauto.MapDataRecord;
import com.testmile.daksha.tpi.ddauto.MapDataSource;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.setu.requester.BaseSetuObject;
import com.testmile.setu.requester.SetuActionType;
import com.testmile.setu.requester.SetuArg;
import com.testmile.setu.requester.SetuResponse;

abstract class AbstractFileDataSourceBuilder<T> {
	private TestSession testSession;
	private String fileName;
	private List<SetuArg> argPairs = new ArrayList<SetuArg>();
	private String delimiter;
	private DataRecordType recordType;

	public AbstractFileDataSourceBuilder(TestSession session, String fileName, DataRecordType recordType) {
		this.testSession = session;
		this.fileName = fileName;
		this.recordType = recordType;
	}

	public AbstractFileDataSourceBuilder<T> delimiter(String delimiter) {
		this.delimiter = delimiter;
		return this;
	}
	
	protected String createDataSource() throws Exception {
		if (delimiter != null) {
			argPairs.add(SetuArg.arg("delimiter", this.delimiter));
		}
		return testSession.createFileDataSource(recordType, this.fileName, argPairs);		
	}
	
	protected TestSession getTestSession() {
		return this.testSession;
	}
	
	public abstract T build() throws Exception;
}

class DefaultFileListDataSourceBuilder extends AbstractFileDataSourceBuilder<ListDataSource> implements FileListDataSourceBuilder {
	
	public DefaultFileListDataSourceBuilder(TestSession session, String fileName) {
		super(session, fileName, DataRecordType.LIST);
	}
	
	public ListDataSource build() throws Exception {
		String sourceSetuId = createDataSource();
		return new SetuListDataSource(this.getTestSession(), sourceSetuId);
	}	
}


class DefaultFileMapDataSourceBuilder extends AbstractFileDataSourceBuilder<MapDataSource> implements FileMapDataSourceBuilder{
	
	public DefaultFileMapDataSourceBuilder(TestSession session, String fileName) {
		super(session, fileName, DataRecordType.MAP);
	}
	
	public MapDataSource build() throws Exception {
		String sourceSetuId = createDataSource();
		return new SetuMapDataSource(this.getTestSession(), sourceSetuId);
	}	

}

//TestNG does not provide any straight-foward support for lazy data generators.
//Calling hasNext and then next to a remote service is not good especially in case of shared data sources.
//So, for Daksha, which uses TestNG as its primary engine, irrespective of nature of data source,
//only greedy data sources are provided. Don't use for large data sets.

abstract class SetuDataSource<D> extends BaseSetuObject {
	private boolean iterCreated = false;
	List<D> records;
	
	public SetuDataSource(TestSession testSession, String setuId) {
		super();
		this.setSetuId(setuId);
		this.setSelfSetuIdArg("sourceSetuId");
		this.setTestSessionSetuIdArg(testSession.getSetuId());
	}
	
	private void populateAllRecords() throws Exception{
		if (records != null) {
			return;
		}
		records = this.getAllRecords();	
	}
	
	public Iterator<Object[]> iterRecordsForTestNG() throws Exception {
		populateAllRecords();
		if (!iterCreated) {
			this.iterCreated = true;
			return new DataRecordIteratorForTestNG<D>(records);
		} else {
			throw new Exception("You must reset the data source before creating a new iterator.");
		}
	}

	public Iterator<D> iterRecords() throws Exception {
		populateAllRecords();
		if (!iterCreated) {
			populateAllRecords();
			this.iterCreated = true;
			return records.iterator();
		} else {
			throw new Exception("You must reset the data source before creating a new iterator.");
		}	
	}	
	
	protected SetuResponse sendAllRecordRequestToSetu() throws Exception {
		return this.sendRequest(SetuActionType.DATASOURCE_GET_ALL_RECORDS);
	}
	
	protected SetuResponse sendNextRequestToSetu() throws Exception {
		return this.sendRequest(SetuActionType.DATASOURCE_GET_NEXT_RECORD);	
	}
	
	public void reset() throws Exception {
		// No use in Daksha
		// However Setu has concept of resetting of Data source. Can not be used with TestNG.
		this.iterCreated = false;
	}

	protected abstract D next() throws Exception;
	
	protected abstract List<D> getAllRecords() throws Exception;
}

class SetuListDataSource extends SetuDataSource<ListDataRecord> implements ListDataSource {

	public SetuListDataSource(TestSession testSession, String sourceSetuId) {
		super(testSession, sourceSetuId);
	}

	@Override
	protected ListDataRecord next() throws Exception {
		SetuResponse response = this.sendNextRequestToSetu();
		boolean finished = (boolean) response.getData().get("finished");
		if (finished) {
			throw new Exception("Finished");
		} else {
			return new DefaultListDataRecord((List<Object>) response.getData().get("record"));
		}
	}
	
	@Override
	protected List<ListDataRecord> getAllRecords() throws Exception {
		SetuResponse response = this.sendAllRecordRequestToSetu();
		List<List<Object>> records = (List<List<Object>>) response.getData().get("records");
		List<ListDataRecord> lRecords = new ArrayList<ListDataRecord>();
		for (List<Object> rec: records) {
			lRecords.add(new DefaultListDataRecord(rec));
		}
		return lRecords;
	}


}

class SetuMapDataSource extends SetuDataSource<MapDataRecord> implements MapDataSource {
	
	public SetuMapDataSource(TestSession testSession, String sourceSetuId) {
		super(testSession, sourceSetuId);
	}

	@Override
	protected MapDataRecord next() throws Exception {
		SetuResponse response = this.sendNextRequestToSetu();
		boolean finished = (boolean) response.getData().get("finished");
		if (finished) {
			throw new Exception("Finished");
		} else {
			return new DefaultMapDataRecord((Map<String, Object>) response.getData().get("record"));
		}
	}
	
	@Override
	protected List<MapDataRecord> getAllRecords() throws Exception {
		SetuResponse response = this.sendAllRecordRequestToSetu();
		List<Map<String, Object>> records = (List<Map<String, Object>>) response.getData().get("records");
		List<MapDataRecord> lRecords = new ArrayList<MapDataRecord>();
		for (Map<String, Object> rec: records) {
			lRecords.add(new DefaultMapDataRecord(rec));
		}
		return lRecords;
	}

}
