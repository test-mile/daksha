package com.testmile.daksha.tpi.test;

import java.util.List;
import java.util.Map;

import com.testmile.setu.requester.SetuArg;
import com.testmile.setu.requester.SetuManagedObject;
import com.testmile.setu.requester.databroker.DataRecordType;
import com.testmile.trishanku.tpi.value.Value;

public interface TestSession extends SetuManagedObject {

	TestConfig init(String rootDir) throws Exception;
	void finish() throws Exception;
	String registerConfig(Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	String registerConfig(String parentConfigSetuId, Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	String createFileDataSource(DataRecordType recordType, String fileName, List<SetuArg> argPairs) throws Exception;	
}
