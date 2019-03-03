package com.testmile.setu.requester.databroker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.testmile.daksha.tpi.ddauto.ListDataRecord;
import com.testmile.daksha.tpi.ddauto.MapDataRecord;
import com.testmile.daksha.tpi.ddauto.MapDataSource;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.setu.requester.Response;

public class SetuMapDataSource extends SetuDataSource<MapDataRecord> implements MapDataSource {
	
	public SetuMapDataSource(TestSession testSession, String sourceSetuId) {
		super(testSession, sourceSetuId);
	}

	@Override
	protected MapDataRecord next() throws Exception {
		Response response = this.sendNextRequestToSetu();
		boolean finished = (boolean) response.getData().get("finished");
		if (finished) {
			throw new Exception("Finished");
		} else {
			return new MapDataRecord((Map<String, Object>) response.getData().get("record"));
		}
	}
	
	@Override
	protected List<MapDataRecord> getAllRecords() throws Exception {
		Response response = this.sendAllRecordRequestToSetu();
		List<Map<String, Object>> records = (List<Map<String, Object>>) response.getData().get("records");
		List<MapDataRecord> lRecords = new ArrayList<MapDataRecord>();
		for (Map<String, Object> rec: records) {
			lRecords.add(new MapDataRecord(rec));
		}
		return lRecords;
	}

}
