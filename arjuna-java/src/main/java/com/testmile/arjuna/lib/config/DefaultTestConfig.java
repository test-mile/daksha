package com.testmile.arjuna.lib.config;

import com.testmile.arjuna.lib.enums.BrowserName;
import com.testmile.arjuna.lib.enums.GuiAutomationContext;
import com.testmile.arjuna.lib.enums.SetuOption;
import com.testmile.arjuna.lib.setu.requester.config.BaseTestConfig;
import com.testmile.arjuna.lib.setu.requester.config.SetuTestConfig;
import com.testmile.arjuna.lib.setu.requester.testsession.TestSession;
import com.testmile.arjuna.tpi.test.TestConfig;

public class DefaultTestConfig extends BaseTestConfig implements TestConfig {
	
	public DefaultTestConfig(TestSession testSession, String name, String setuId) {
		super(testSession, name, setuId);
	}
	
	public DefaultTestConfig(SetuTestConfig config) {
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
