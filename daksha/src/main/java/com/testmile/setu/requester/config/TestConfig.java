package com.testmile.setu.requester.config;

import com.testmile.setu.requester.connector.SetuManagedObject;
import com.testmile.setu.requester.testsession.TestSession;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.Value;

public interface TestConfig extends SetuManagedObject {
	
	Value getSetuOptionValue(String option) throws Exception;	
	Value getSetuOptionValue(SetuOption option) throws Exception;
	Value getUserOptionValue(String option) throws Exception;
	String getName();
	TestSession getTestSession();
}