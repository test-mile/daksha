package com.testmile.setu.requester.databroker;

import com.testmile.daksha.tpi.ddauto.MapDataRecord;
import com.testmile.daksha.tpi.ddauto.MapDataSource;
import com.testmile.daksha.tpi.test.TestSession;

public class SetuMapDataSource extends SetuDataSource<MapDataRecord> implements MapDataSource {
	
	public SetuMapDataSource(TestSession testSession, String sourceSetuId) {
		super(testSession, sourceSetuId);
	}

	@Override
	protected MapDataRecord next() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
