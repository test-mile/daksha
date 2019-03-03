package com.testmile.setu.requester.databroker;

import java.util.ArrayList;
import java.util.List;

import com.testmile.daksha.tpi.ddauto.ListDataRecord;
import com.testmile.daksha.tpi.ddauto.ListDataSource;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.setu.requester.Response;

public class SetuListDataSource extends SetuDataSource<ListDataRecord> implements ListDataSource {

	public SetuListDataSource(TestSession testSession, String sourceSetuId) {
		super(testSession, sourceSetuId);
	}

	@Override
	protected ListDataRecord next() throws Exception {
		Response response = this.sendNextRequestToSetu();
		boolean finished = (boolean) response.getData().get("finished");
		if (finished) {
			throw new Exception("Finished");
		} else {
			return new ListDataRecord((List<Object>) response.getData().get("record"));
		}
	}
	
	@Override
	protected List<ListDataRecord> getAllRecords() throws Exception {
		Response response = this.sendAllRecordRequestToSetu();
		List<List<Object>> records = (List<List<Object>>) response.getData().get("records");
		List<ListDataRecord> lRecords = new ArrayList<ListDataRecord>();
		for (List<Object> rec: records) {
			lRecords.add(new ListDataRecord(rec));
		}
		return lRecords;
	}


}
