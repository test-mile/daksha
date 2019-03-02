package com.testmile.daksha.tpi.test;

import java.util.Map;

import com.testmile.daksha.core.setu.SetuManagedObject;
import com.testmile.daksha.core.setu.SetuSvcRequester;
import com.testmile.trishanku.tpi.value.Value;

public interface TestSession extends SetuManagedObject {

	TestConfig init(String rootDir) throws Exception;
	void finish() throws Exception;
	Value getSetuOptionValue(String configSetuId, String option) throws Exception;
	Value getUserOptionValue(String configSetuId, String option) throws Exception;
	String registerConfig(Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	String registerConfig(String parentConfigSetuId, Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	SetuSvcRequester getSetuRequester();
	
}
