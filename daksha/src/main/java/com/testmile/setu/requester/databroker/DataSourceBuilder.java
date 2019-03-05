package com.testmile.setu.requester.databroker;

import java.util.*;

import com.testmile.daksha.tpi.ddauto.*;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.setu.requester.*;
import com.testmile.trishanku.tpi.value.*;
import com.testmile.setu.requester.databroker.*;

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


