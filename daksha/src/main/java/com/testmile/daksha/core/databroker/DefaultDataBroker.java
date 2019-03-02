package com.testmile.daksha.core.databroker;

import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.setu.SetuSvcRequester;
import com.testmile.daksha.tpi.ddauto.ListDataSource;
import com.testmile.daksha.tpi.ddauto.MapDataSource;
import com.testmile.daksha.tpi.test.TestSession;

public class DefaultDataBroker extends DefaultSetuObject implements DataBroker{
	private SetuSvcRequester setuRequester;
	private String baseActionUri = "/action";

	public DefaultDataBroker(TestSession testSession) {
		this.setuRequester = testSession.getSetuRequester();
		this.setTestSessionSetuId(testSession.getSetuId());
	}

	@Override
	public MapDataSource createIniFileMapDataSource(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListDataSource createTabDelimitedFileListDataSource(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapDataSource createTabDelimitedFileMapDataSource(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListDataSource createDelimitedFileListDataSource(String fileName, String delimiter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListDataSource createDelimitedFileMapDataSource(String fileName, String delimiter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListDataSource createExcelFileListDataSource(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapDataSource createExcelFileMapDataSource(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
