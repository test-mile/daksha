package com.testmile.setu.requester.databroker;

import com.testmile.daksha.tpi.test.TestSession;

public class DataSourceBuilder{
	private TestSession session;
	
	public DataSourceBuilder(TestSession session) {
		this.session = session;
	}
	
	public FileListDataSourceBuilder fileListDataSource(String fileName) {
		return new DefaultFileListDataSourceBuilder(session, fileName);
	}
	
	public FileMapDataSourceBuilder fileMapDataSource(String fileName) {
		return new DefaultFileMapDataSourceBuilder(session, fileName);
	}
	
	public ListDataContainerBuilder listDataContainer() {
		return new ListDataContainerBuilder();
	}
	
	public MapDataContainerBuilder mapDataContainer(String... headers) throws Exception {
		return new MapDataContainerBuilder(headers);
	}
}


