package com.testmile.setu.requester.databroker;

import java.util.Iterator;

import com.testmile.daksha.tpi.ddauto.ListDataRecord;
import com.testmile.daksha.tpi.ddauto.ListDataSource;
import com.testmile.daksha.tpi.test.TestSession;

public class SetuListDataSource extends SetuDataSource<ListDataRecord> implements ListDataSource {

	public SetuListDataSource(TestSession testSession, String sourceSetuId) {
		super(testSession, sourceSetuId);
	}

	@Override
	protected ListDataRecord next() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
