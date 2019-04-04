package arjuna.tpi.test;

import java.util.Map;

import arjuna.tpi.enums.ArjunaOption;

public interface TestContext {

	void addArjunaOption(ArjunaOption option, Object value) throws Exception;

	void addUserOption(String option, Object obj) throws Exception;

	void addOption(String option, Object obj) throws Exception;

	void addOptions(Map<String, String> options) throws Exception;

	TestContext guiAutoMaxWaitTime(int seconds) throws Exception;

	TestConfig build() throws Exception;

	TestContext firefox() throws Exception;

}