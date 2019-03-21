package com.testmile.arjuna.tpi.ddauto;

import com.testmile.arjuna.lib.setu.databroker.requester.DataContainerBuilder;
import com.testmile.arjuna.lib.setu.databroker.requester.FileDataSourceBuilder;
import com.testmile.arjuna.lib.setu.databroker.requester.SetuDataSourceBuilder;
import com.testmile.arjuna.lib.setu.testsession.requester.TestSession;

public class DataSourceBuilder{
	private TestSession session;
	
	public DataSourceBuilder(TestSession session) {
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

