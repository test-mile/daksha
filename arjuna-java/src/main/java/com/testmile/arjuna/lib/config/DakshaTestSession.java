package com.testmile.arjuna.lib.config;

import com.testmile.arjuna.lib.setu.requester.config.TestConfig;
import com.testmile.arjuna.lib.setu.requester.testsession.DefaultTestSession;
import com.testmile.arjuna.tpi.test.DakshaTestConfig;

public class DakshaTestSession extends DefaultTestSession {
	
	public DakshaTestConfig initSession(String rootDir) throws Exception {
		// Get Default Setu Config
		TestConfig config = super.init(rootDir);
		
		// Convert to Daksha Test Config
		return new DefaultDakshaTestConfig(config);
	}

}
