package com.testmile.daksha.core.config;

import com.testmile.daksha.tpi.test.DakshaTestConfig;
import com.testmile.setu.requester.config.TestConfig;
import com.testmile.setu.requester.testsession.DefaultTestSession;

public class DakshaTestSession extends DefaultTestSession {
	
	public DakshaTestConfig initSession(String rootDir) throws Exception {
		// Get Default Setu Config
		TestConfig config = super.init(rootDir);
		
		// Convert to Daksha Test Config
		return new DefaultDakshaTestConfig(config);
	}

}
