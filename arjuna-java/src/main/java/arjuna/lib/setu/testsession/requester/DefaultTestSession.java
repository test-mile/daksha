package arjuna.lib.setu.testsession.requester;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import arjuna.lib.setu.core.requester.config.DefaultTestConfig;
import arjuna.lib.setu.core.requester.config.SetuActionType;
import arjuna.lib.setu.core.requester.connector.BaseSetuObject;
import arjuna.lib.setu.core.requester.connector.SetuArg;
import arjuna.lib.setu.core.requester.connector.SetuResponse;
import arjuna.lib.setu.databroker.requester.DataRecordType;
import arjuna.lib.state.ArjunaSingleton;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.test.TestConfig;
import arjuna.tpi.value.Value;

public class DefaultTestSession extends BaseSetuObject implements TestSession {
	private final String DEF_CONF_NAME = "central";

	@Override
	public TestConfig init(String rootDir) throws Exception {
		SetuResponse response = this.sendRequest(
				SetuActionType.TESTSESSION_INIT, 
				SetuArg.arg("projectRootDir", rootDir),
				SetuArg.arg("cliConfig", ArjunaSingleton.INSTANCE.getCliArgsConfig().asMap())
				);
		this.setSetuId(response.getValueForTestSessionSetuId());
		this.setSelfSetuIdArg("testSessionSetuId");
		TestConfig config = new DefaultTestConfig(this, DEF_CONF_NAME, response.getValueForConfigSetuId());
		return config;
	}

	@Override
	public void finish() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private String registerConfig(boolean hasParent, String parentConfigId, Map<String, String> arjunaOptions, Map<String, Value> userOptions) throws Exception {
		SetuResponse response = this.sendRequest(
				SetuActionType.TESTSESSION_REGISTER_CONFIG, 
				SetuArg.arg("hasParent", hasParent),
				SetuArg.arg("parentConfigId", parentConfigId),
				SetuArg.arg("arjunaOptions", arjunaOptions),
				SetuArg.arg("userOptions", userOptions)
		);
		return response.getValueForConfigSetuId();
	}
	
	@Override
	public String registerConfig(Map<String, String> arjunaOptions, Map<String, Value> userOptions) throws Exception {
		return registerConfig(false, null, arjunaOptions, userOptions);
	}
	
	@Override
	public String registerChildConfig(String parentConfigId, Map<String, String> arjunaOptions, Map<String, Value> userOptions) throws Exception {
		return registerConfig(true, parentConfigId, arjunaOptions, userOptions);
	}
	
	@Override
	public String createFileDataSource(DataRecordType recordType, String fileName, List<SetuArg> argPairs) throws Exception {
		SetuResponse response = this.sendRequest(
				SetuActionType.TESTSESSION_CREATE_FILE_DATA_SOURCE, 
				concat(
						new SetuArg[] {SetuArg.arg("fileName", fileName), SetuArg.arg("recordType", recordType)}, 
						argPairs.toArray(new SetuArg[] {})
				)
		);
		return response.getDataSourceSetuId();
	}
	
	@Override
	public String createGui(GuiAutomator automator, SetuArg... args) throws Exception {
		SetuResponse response = this.sendRequest(
				SetuActionType.TESTSESSION_CREATE_GUI, 
				concat (args, 
						new SetuArg[] {SetuArg.arg("automatorSetuId", automator.getSetuId())}
				)
		);
		return response.getGuiSetuId();
	}
	
	// From Joachim Sauer's Stackoverflow answer: https://stackoverflow.com/a/784842
	public <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}

}
