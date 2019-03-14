package com.testmile.daksha.tpi.test;

import java.util.Map;

import com.testmile.trishanku.tpi.enums.SetuOption;

public interface TestContext {

	void addSetuOption(SetuOption option, Object value) throws Exception;

	void addUserOption(String option, Object obj) throws Exception;

	void addOption(String option, Object obj) throws Exception;

	void addOptions(Map<String, String> options) throws Exception;

	TestContext guiAutoMaxWaitTime(int seconds) throws Exception;

	DakshaTestConfig build() throws Exception;

	TestContext firefox() throws Exception;

}