package com.testmile.arjuna.lib.setu.core.requester.config;

import com.testmile.arjuna.lib.enums.BrowserName;
import com.testmile.arjuna.lib.enums.GuiAutomationContext;
import com.testmile.arjuna.lib.setu.core.requester.connector.BaseSetuObject;
import com.testmile.arjuna.lib.setu.core.requester.connector.SetuArg;
import com.testmile.arjuna.lib.setu.core.requester.connector.SetuResponse;
import com.testmile.arjuna.lib.setu.testsession.requester.TestSession;
import com.testmile.arjuna.lib.state.ArjunaSingleton;
import com.testmile.arjuna.tpi.enums.ArjunaOption;
import com.testmile.arjuna.tpi.test.TestConfig;
import com.testmile.arjuna.tpi.value.Value;

public class DefaultTestConfig extends BaseSetuObject implements TestConfig {
	private String name;
	private TestSession session;
	
	public DefaultTestConfig(TestSession testSession, String name, String setuId) {
		this.session = testSession;
		this.name = name;
		
		this.setSetuId(setuId);
		this.setSelfSetuIdArg("configSetuId");
		this.setTestSessionSetuIdArg(testSession.getSetuId());
	}

	@Override
	public TestSession getTestSession() {
		return this.session;
	}
	
	private Value fetchConfOptionValue(SetuActionType actionType, String option) throws Exception {
		SetuResponse response = this.sendRequest(
				actionType,
				SetuArg.arg("option", option)
		);
		return response.getValue();	
	}
	
	public Value getArjunaOptionValue(String option) throws Exception{
		return this.fetchConfOptionValue(
				SetuActionType.CONFIGURATOR_GET_SETU_OPTION_VALUE,
				ArjunaSingleton.INSTANCE.normalizeSetuOption(option).toString()
		);
	}	
	
	public Value getArjunaOptionValue(ArjunaOption option) throws Exception{
		return this.fetchConfOptionValue(
				SetuActionType.CONFIGURATOR_GET_SETU_OPTION_VALUE,
				option.toString()
		);
	}
	
	public Value getUserOptionValue(String option) throws Exception{
		return this.fetchConfOptionValue(
				SetuActionType.CONFIGURATOR_GET_USER_OPTION_VALUE,
				ArjunaSingleton.INSTANCE.normalizeUserOption(option)
		);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public GuiAutomationContext getGuiAutoContext() throws Exception {
		return GuiAutomationContext.valueOf(getArjunaOptionValue(ArjunaOption.GUIAUTO_CONTEXT).asString());
	}
	
	@Override
	public BrowserName getBrowserType() throws Exception {
		return getArjunaOptionValue(ArjunaOption.BROWSER_NAME).asEnum(BrowserName.class);
	}

	@Override
	public String getBrowerVersion() throws Exception {
		return getArjunaOptionValue(ArjunaOption.BROWSER_VERSION).asString();
	}

	@Override
	public String getBrowserBinaryPath() throws Exception {
		return getArjunaOptionValue(ArjunaOption.BROWSER_BIN_PATH).asString();
	}
	
	@Override
	public String getTestRunEnvName() throws Exception {
		return getArjunaOptionValue(ArjunaOption.TESTRUN_ENVIRONMENT).asString();
	}

	@Override
	public String getScreenshotsDir() throws Exception {
		return getArjunaOptionValue(ArjunaOption.SCREENSHOTS_DIR).asString();
	}
	
	@Override
	public String getLogDir() throws Exception {
		return getArjunaOptionValue(ArjunaOption.LOG_DIR).asString();
	}
	
	public int getGuiAutoMaxWaitTime() throws Exception {
		return getArjunaOptionValue(ArjunaOption.GUIAUTO_MAX_WAIT).asInt();
	}
}
