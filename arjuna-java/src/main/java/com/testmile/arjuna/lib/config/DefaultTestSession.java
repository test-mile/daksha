package com.testmile.arjuna.lib.config;

import com.testmile.arjuna.lib.setu.requester.config.SetuTestConfig;
import com.testmile.arjuna.lib.setu.requester.testsession.BaseTestSession;
import com.testmile.arjuna.tpi.test.TestConfig;

public class DefaultTestSession extends BaseTestSession {
	
	public TestConfig initSession(String rootDir) throws Exception {
		// Get Default Setu Config
		SetuTestConfig config = super.init(rootDir);
		
		// Convert to Daksha Test Config
		return new DefaultTestConfig(config);
	}

}
