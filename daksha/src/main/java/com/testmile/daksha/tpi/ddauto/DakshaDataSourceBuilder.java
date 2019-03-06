package com.testmile.daksha.tpi.ddauto;

import com.testmile.setu.requester.databroker.DataContainerBuilder;
import com.testmile.setu.requester.databroker.FileDataSourceBuilder;
import com.testmile.setu.requester.databroker.ListDataSource;
import com.testmile.setu.requester.databroker.MapDataSource;
import com.testmile.setu.requester.databroker.SetuDataSourceBuilder;
import com.testmile.setu.requester.testsession.TestSession;

public class DakshaDataSourceBuilder{
	private TestSession session;
	
	public DakshaDataSourceBuilder(TestSession session) {
		this.session = session;
	}
	
	public FileDataSourceBuilder<ListDataSource> fileListDataSource(String fileName) {
		return SetuDataSourceBuilder.fileListDataContainer(session, fileName);
	}
	
	public FileDataSourceBuilder<MapDataSource> fileMapDataSource(String fileName) {
		return SetuDataSourceBuilder.fileMapDataContainer(session, fileName);
	}
	
	public DataContainerBuilder<ListDataSource> listDataContainer() {
		return SetuDataSourceBuilder.listDataContainer();
	}
	
	public DataContainerBuilder<MapDataSource> mapDataContainer(String... headers) throws Exception {
		return SetuDataSourceBuilder.mapDataContainer(headers);
	}
}


