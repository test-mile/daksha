package com.testmile.setu.requester.databroker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.testmile.daksha.tpi.ddauto.ListDataRecord;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.setu.requester.DefaultSetuObject;
import com.testmile.setu.requester.Response;
import com.testmile.setu.requester.SetuSvcRequester;
import com.testmile.setu.requester.testsession.TestSessionAction;
import com.testmile.setu.requester.testsession.TestSessionActionType;


// TestNG does not provide any straight-foward support for lazy data generators.
// Calling hasNext and then next to a remote service is not good especially in case of shared data sources.
// So, for Daksha, which uses TestNG as its primary engine, irrespective of nature of data source,
// only greedy data sources are provided. Don't use for large data sets.

public abstract class SetuDataSource<D> extends DefaultSetuObject {
	private SetuSvcRequester setuRequester;
	private String baseActionUri = "/datasource/action";
	private boolean iterCreated = false;
	List<D> records;
	
	public SetuDataSource(TestSession testSession, String setuId) {
		super();
		this.setuRequester = testSession.getSetuRequester();
		this.setSetuId(setuId);
		this.setTestSessionSetuId(testSession.getSetuId());
	}
	
	private void populateAllRecords() throws Exception{
		if (records != null) {
			return;
		}
		records = this.getAllRecords();
//		while(true) {
//			try {
//				D record = this.next();
//				records.add(record);
//			} catch (Exception e) {
//				break;
//			}
//		}		
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
	
	protected Response sendAllRecordRequestToSetu() throws Exception {
		DataSourceAction action = new DataSourceAction(this, DataSourceActionType.GET_ALL_RECORDS);
		action.addArg("sourceSetuId", this.getSetuId());
		Response response = this.setuRequester.post(baseActionUri, action);
		return response;		
	}
	
	protected Response sendNextRequestToSetu() throws Exception {
		DataSourceAction action = new DataSourceAction(this, DataSourceActionType.GET_NEXT_RECORD);
		action.addArg("sourceSetuId", this.getSetuId());
		Response response = this.setuRequester.post(baseActionUri, action);
		return response;		
	}
	
	public void reset() throws Exception {
		// No use in Daksha
		// However Setu has concept of resetting of Data source. Can not be used with TestNG.
		this.iterCreated = false;
	}

	protected abstract D next() throws Exception;
	
	protected abstract List<D> getAllRecords() throws Exception;
}
