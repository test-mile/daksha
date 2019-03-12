package com.testmile.daksha.core.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testmile.daksha.tpi.test.DakshaTestConfig;
import com.testmile.setu.requester.config.DefaultTestConfig;
import com.testmile.setu.requester.config.TestConfig;
import com.testmile.setu.requester.guiauto.GuiDriverExtendedConfig;
import com.testmile.setu.requester.guiauto.automator.DefaultGuiAutomator;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;
import com.testmile.setu.requester.testsession.TestSession;
import com.testmile.trishanku.tpi.enums.BrowserName;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.SetuOption;

public class DefaultDakshaTestConfig extends DefaultTestConfig implements DakshaTestConfig {
	
	public DefaultDakshaTestConfig(TestSession testSession, String name, String setuId) {
		super(testSession, name, setuId);
	}
	
	public DefaultDakshaTestConfig(TestConfig config) {
		super(config.getTestSession(), config.getName(), config.getSetuId());
	}

	public GuiAutomationContext getGuiAutoContext() throws Exception {
		return GuiAutomationContext.valueOf(getSetuOptionValue(SetuOption.GUIAUTO_CONTEXT).asString());
	}
	
	@Override
	public BrowserName getBrowserType() throws Exception {
		return getSetuOptionValue(SetuOption.BROWSER_NAME).asEnum(BrowserName.class);
	}

	@Override
	public String getBrowerVersion() throws Exception {
		return getSetuOptionValue(SetuOption.BROWSER_VERSION).asString();
	}

	@Override
	public String getBrowserBinaryPath() throws Exception {
		return getSetuOptionValue(SetuOption.BROWSER_BIN_PATH).asString();
	}
	
	@Override
	public String getTestRunEnvName() throws Exception {
		return getSetuOptionValue(SetuOption.TESTRUN_ENVIRONMENT).asString();
	}

	@Override
	public String getScreenshotsDir() throws Exception {
		return getSetuOptionValue(SetuOption.SCREENSHOTS_DIR).asString();
	}
	
	@Override
	public String getLogDir() throws Exception {
		return getSetuOptionValue(SetuOption.LOG_DIR).asString();
	}
	
	public int getGuiAutoMaxWaitTime() throws Exception {
		return getSetuOptionValue(SetuOption.GUIAUTO_MAX_WAIT).asInt();
	}
}
