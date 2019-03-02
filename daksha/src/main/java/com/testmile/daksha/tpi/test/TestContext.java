package com.testmile.daksha.tpi.test;

import java.util.Map;

import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.OSType;
import com.testmile.trishanku.tpi.enums.SetuOption;

public interface TestContext {

	void addSetuOption(SetuOption option, Object value) throws Exception;

	void addUserOption(String option, Object obj) throws Exception;

	void addOption(String option, Object obj) throws Exception;

	void addOptions(Map<String, String> options) throws Exception;

	TestContext automationContext(GuiAutomationContext context) throws Exception;

	TestContext browserType(Browser browser) throws Exception;

	TestContext targetPlatform(OSType osType) throws Exception;

	TestContext uuiAutoMaxWaitTime(int seconds) throws Exception;

	TestConfig build() throws Exception;

}