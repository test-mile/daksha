package arjuna.lib.core.config;

import java.util.Map;

import arjuna.lib.core.value.StringKeyValueMap;
import arjuna.lib.state.ArjunaSingleton;
import arjuna.tpi.enums.ArjunaOption;

public class ConfigContainer {
	private ArjunaOptionMap arjunaOptions = new ArjunaOptionMap();
	private StringKeyValueMap userOptions = new StringKeyValueMap();
	
	public ArjunaOptionMap getArjunaOptions() {
		return this.arjunaOptions;
	}
	
	public StringKeyValueMap getUserOptions() {
		return this.userOptions;
	}
	
	public void setArjunaOption(ArjunaOption option, Object value) throws Exception {
		arjunaOptions.addObject(option, value);
	 }

	public void setUserOption(String option, Object obj) throws Exception {
		userOptions.addObject(ArjunaSingleton.INSTANCE.normalizeUserOption(option), obj);
	} 
	
	public void setOption(String option, Object obj) throws Exception {
		String normalizedOption = ArjunaSingleton.INSTANCE.normalizeUserOption(option);
		try {
			ArjunaOption sOption = ArjunaOption.valueOf(normalizedOption);
			this.arjunaOptions.addObject(sOption, obj);
		} catch (Exception e) {
			userOptions.addObject(option, obj);
		}	
	} 
	
	public void setOptions(Map<String,String> options) throws Exception {
		for(String option: options.keySet()) {
			this.setOption(option,  options.get(option));
		}
	}

}
