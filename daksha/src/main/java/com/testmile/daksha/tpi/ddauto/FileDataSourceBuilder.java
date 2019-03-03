package com.testmile.daksha.tpi.ddauto;

import java.util.HashMap;
import java.util.Map;

import com.testmile.daksha.core.databroker.DataRecordType;
import com.testmile.daksha.core.databroker.SetuListDataSource;
import com.testmile.daksha.core.databroker.SetuMapDataSource;
import com.testmile.daksha.tpi.test.TestSession;

public class FileDataSourceBuilder {
	private TestSession testSession;
	private String fileName;
	private Map<String, Object> argPairs = new HashMap<String, Object>();
	private String delimiter;

	public FileDataSourceBuilder(TestSession session, String fileName) {
		this.testSession = session;
		this.fileName = fileName;
	}
	
	public FileDataSourceBuilder delimiter(String delimiter) {
		this.delimiter = delimiter;
		return this;
	}
	
	private String createDataSource() throws Exception {
		if (delimiter != null) {
			argPairs.put("delimiter", this.delimiter);
		}
		return testSession.createFileDataSource(DataRecordType.LIST, this.fileName, argPairs);		
	}
	
	public ListDataSource buildListDataSource() throws Exception {
		String sourceSetuId = createDataSource();
		return new SetuListDataSource(testSession, sourceSetuId);
	}
	
	public MapDataSource buildMapDataSource() throws Exception {
		String sourceSetuId = createDataSource();
		return new SetuMapDataSource(testSession, sourceSetuId);
	}	

}
