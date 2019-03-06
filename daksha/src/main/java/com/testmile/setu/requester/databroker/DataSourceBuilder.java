package com.testmile.setu.requester.databroker;

import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.daksha.tpi.ddauto.*;

public class DataSourceBuilder{
	private TestSession session;
	
	public DataSourceBuilder(TestSession session) {
		this.session = session;
	}
	
	public FileDataSourceBuilder<ListDataSource> fileListDataSource(String fileName) {
		return new DefaultFileListDataSourceBuilder(session, fileName);
	}
	
	public FileDataSourceBuilder<MapDataSource> fileMapDataSource(String fileName) {
		return new DefaultFileMapDataSourceBuilder(session, fileName);
	}
	
	public ListDataContainerBuilder listDataContainer() {
		return new ListDataContainerBuilder();
	}
	
	public MapDataContainerBuilder mapDataContainer(String... headers) throws Exception {
		return new MapDataContainerBuilder(headers);
	}
}


