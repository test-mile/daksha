package com.testmile.setu.requester.testsession;

import java.util.List;
import java.util.Map;

import com.testmile.setu.requester.config.TestConfig;
import com.testmile.setu.requester.connector.SetuArg;
import com.testmile.setu.requester.connector.SetuManagedObject;
import com.testmile.setu.requester.databroker.DataRecordType;
import com.testmile.trishanku.tpi.value.Value;

public interface TestSession extends SetuManagedObject {

	TestConfig init(String rootDir) throws Exception;
	void finish() throws Exception;
	String registerConfig(Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	String registerConfig(String parentConfigSetuId, Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	String createFileDataSource(DataRecordType recordType, String fileName, List<SetuArg> argPairs) throws Exception;	
}
