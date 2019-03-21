package com.testmile.arjuna.lib.setu.testsession.requester;

import java.util.List;
import java.util.Map;
import com.testmile.arjuna.lib.setu.core.requester.connector.SetuArg;
import com.testmile.arjuna.lib.setu.core.requester.connector.SetuManagedObject;
import com.testmile.arjuna.lib.setu.databroker.requester.DataRecordType;
import com.testmile.arjuna.tpi.guiauto.GuiAutomator;
import com.testmile.arjuna.tpi.test.TestConfig;
import com.testmile.arjuna.tpi.value.Value;

public interface TestSession extends SetuManagedObject {

	TestConfig init(String rootDir) throws Exception;
	void finish() throws Exception;
	String registerConfig(Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	String registerChildConfig(String parentConfigSetuId, Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception;
	String createFileDataSource(DataRecordType recordType, String fileName, List<SetuArg> argPairs) throws Exception;
	String createGui(GuiAutomator automator, SetuArg... args) throws Exception;	
}
