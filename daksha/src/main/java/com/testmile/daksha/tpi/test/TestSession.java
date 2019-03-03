package com.testmile.daksha.tpi.test;

import java.util.Map;

import com.testmile.setu.requester.SetuManagedObject;
import com.testmile.setu.requester.SetuSvcRequester;
import com.testmile.setu.requester.databroker.DataRecordType;
import com.testmile.trishanku.tpi.value.Value;

public interface TestSession extends SetuManagedObject {

	TestConfig init(String rootDir) throws Exception;
	void finish() throws Exception;
	String registerConfig(Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	String registerConfig(String parentConfigSetuId, Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	SetuSvcRequester getSetuRequester();
	String createFileDataSource(DataRecordType recordType, String fileName, Map<String, Object> argPairs) throws Exception;
	
}
