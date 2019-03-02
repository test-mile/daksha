package com.testmile.daksha.tpi.test;

import java.util.Map;

import com.testmile.daksha.core.setu.SetuManagedObject;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.Value;

public interface TestSession extends SetuManagedObject {

	TestConfig init(String rootDir) throws Exception;
	void finish() throws Exception;
	Value getSetuOptionValue(String configSetuId, String option);
	Value getUserOptionValue(String configSetuId, String option);
	String registerConfig(Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	
}
