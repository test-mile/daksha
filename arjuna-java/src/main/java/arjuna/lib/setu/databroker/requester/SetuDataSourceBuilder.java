package arjuna.lib.setu.databroker.requester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import arjuna.lib.core.value.AbstractValueList;
import arjuna.lib.core.value.StringKeyValueMap;
import arjuna.lib.setu.core.requester.config.SetuActionType;
import arjuna.lib.setu.core.requester.connector.BaseSetuObject;
import arjuna.lib.setu.core.requester.connector.SetuArg;
import arjuna.lib.setu.core.requester.connector.SetuResponse;
import arjuna.lib.setu.testsession.requester.TestSession;
import arjuna.tpi.ddauto.ListDataRecord;
import arjuna.tpi.ddauto.ListDataSource;
import arjuna.tpi.ddauto.MapDataRecord;
import arjuna.tpi.ddauto.MapDataSource;

public class SetuDataSourceBuilder {
	
	public static DataContainerBuilder<ListDataSource> listDataContainer() {
		return new ListDataContainerBuilder();
	}

	public static DataContainerBuilder<MapDataSource> mapDataContainer(String... headers) throws Exception {
		return new MapDataContainerBuilder(headers);
	}
	
	public static DefaultFileListDataSourceBuilder fileListDataContainer(TestSession session, String fileName) {
		return new DefaultFileListDataSourceBuilder(session, fileName);
	}

	public static DefaultFileMapDataSourceBuilder fileMapDataContainer(TestSession session, String fileName) {
		return new DefaultFileMapDataSourceBuilder(session, fileName);
	}
}

class DefaultListDataRecord extends AbstractValueList implements ListDataRecord{
	
	public DefaultListDataRecord(Object[] values) {
		super(values);
	}

	public DefaultListDataRecord(List<Object> values) {
		super(values);
	}
}

class DefaultMapDataRecord extends StringKeyValueMap implements MapDataRecord{
	
	public DefaultMapDataRecord(Map<String, Object> nvMap){
		super(nvMap);	
	}
}

abstract class BaseDataSource<T> extends BaseSetuObject implements DataSource<T> {
	private List<T> records = new ArrayList<T>();
	private boolean iterCreated = false;
	
	public BaseDataSource() {
		super();
	}
	
	@Override
	public void reset() throws Exception {
		this.iterCreated = false;
	}
	
	protected void addSingleRecord(T record) {
		this.records.add(record);
	}
	
	protected abstract void add(Object[] record) throws Exception;
	
	public void addAll(Object[][] records) throws Exception {
		for (Object[] record : records) {
			this.add(record);
		}
	}
	
	private List<T> allRecords(){
		return this.records;
	}
	
	@Override
	public Iterator<Object[]> iterRecordsForTestNG() throws Exception {
		if (!iterCreated) {
			this.iterCreated = true;
			return new DataRecordIteratorForTestNG<T>(this.allRecords());
		} else {
			throw new Exception("You must reset the data source before creating a new iterator.");
		}
	}
	
	@Override
	public Iterator<T> iterRecords() throws Exception {
		if (!iterCreated) {
			this.iterCreated = true;
			return this.records.iterator();
		} else {
			throw new Exception("You must reset the data source before creating a new iterator.");
		}	
	}	

}

class DataRecordIteratorForTestNG<T> implements Iterator<Object[]>{
	private List<T> records;

	public DataRecordIteratorForTestNG(List<T> records) {
		this.records = records;
	}

	@Override
	public boolean hasNext() {
		return records.size() > 0;
	}
	
	@Override
	public Object[] next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		} else {
			return new Object[] {records.remove(0)};
		}
	}
}


class ListDataContainerBuilder implements DataContainerBuilder<ListDataSource>{
	private ListDataRecordContainer container;
	
	public ListDataContainerBuilder() {
		container = new ListDataRecordContainer();
	}
	
	public ListDataContainerBuilder record(Object... record) throws Exception {
		container.add(record);
		return this;
	}
	
	public ListDataSource build() {
		return this.container;
	}
	
}

class ListDataRecordContainer extends BaseDataSource<ListDataRecord> implements ListDataSource{
	
	public ListDataRecordContainer(){
		super();
	}
	
	public ListDataRecordContainer(Object[][] records) throws Exception {
		super();
		this.addAll(records);
	}
	
	public void add(Object[] record) throws Exception {
		if (record.length == 0){
			throw new Exception("Empty record can not be added.");					
		}
		super.addSingleRecord(new DefaultListDataRecord(record));
	}

}

class MapDataContainerBuilder implements DataContainerBuilder<MapDataSource>{
	private MapDataRecordContainer container;
	
	public MapDataContainerBuilder(String... headers) throws Exception {
		container = new MapDataRecordContainer(headers);
	}
	
	public MapDataContainerBuilder record(Object... record) throws Exception {
		container.add(record);
		return this;
	}
	
	public MapDataSource build() {
		return this.container;
	}
}


class MapDataRecordContainer extends BaseDataSource<MapDataRecord> implements MapDataSource {
	private String[] headers = null;
	private int refLen = -1;
	
	public MapDataRecordContainer(String[] headers) throws Exception{
		super();
		this.headers = headers;
		this.refLen = headers.length;
		validate();
	}
	
	public MapDataRecordContainer(String[] headers, Object[][] records) throws Exception {
		this(headers);
		this.addAll(records);
	}
	
	public void add(Object[] record) throws Exception {
		if (record.length != refLen){
			throw new Exception(String.format("All records must match the length of headers: %d. Current Record Length: %d. Record: [%s]", refLen, record.length, Arrays.toString(record)));					
		}
		Map<String, Object> map = new HashMap<String,Object>();
		for (int i=0; i < this.refLen; i++) {
			map.put(headers[i],record[i]);
		}
		super.addSingleRecord(new DefaultMapDataRecord(map));
	}

	private void validate() throws Exception {
		if ((this.headers == null) || (this.headers.length == 0)){
			throw new Exception("You can not use a Map Data Record container without no headers.");
		}
	}

}

abstract class DefaultFileDataSourceBuiler<T> implements FileDataSourceBuilder<T> {
	private TestSession testSession;
	private String fileName;
	private List<SetuArg> argPairs = new ArrayList<SetuArg>();
	private String delimiter;
	private DataRecordType recordType;

	public DefaultFileDataSourceBuiler(TestSession session, String fileName, DataRecordType recordType) {
		this.testSession = session;
		this.fileName = fileName;
		this.recordType = recordType;
	}
	
	@Override
	public FileDataSourceBuilder<T> delimiter(String delimiter) {
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
	
	@Override
	public abstract T build() throws Exception;
}

class DefaultFileListDataSourceBuilder extends DefaultFileDataSourceBuiler<ListDataSource> {
	
	public DefaultFileListDataSourceBuilder(TestSession session, String fileName) {
		super(session, fileName, DataRecordType.LIST);
	}
	
	public ListDataSource build() throws Exception {
		String sourceSetuId = createDataSource();
		return new SetuListDataSource(this.getTestSession(), sourceSetuId);
	}	
}


class DefaultFileMapDataSourceBuilder extends DefaultFileDataSourceBuiler<MapDataSource>{
	
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
//So, for Arjuna, which uses TestNG as its primary engine, irrespective of nature of data source,
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
		// No use in Arjuna
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

