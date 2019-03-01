package com.testmile.daksha.tpi.test;

import com.testmile.daksha.core.setu.SetuManagedObject;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.Value;

public interface TestSession extends SetuManagedObject {

	TestConfig init(String rootDir) throws Exception;
	void finish() throws Exception;
	Value getSetuConfValue(SetuOption option);
	
}
