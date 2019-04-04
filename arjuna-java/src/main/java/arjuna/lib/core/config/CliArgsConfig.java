package arjuna.lib.core.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import arjuna.lib.state.ArjunaSingleton;
import arjuna.tpi.enums.ArjunaOption;

public class CliArgsConfig {
	Pattern dPattern = Pattern.compile("(?i)(aco|ato)\\s*:\\s*(\\S+)\\s*");
	Pattern uPattern = Pattern.compile("(?i)(uco|uto)\\s*:\\s*(\\S+)\\s*");
	Map<ConfigPropertyType, Map<ArjunaOption,String>> cliArjunaOptionMap = new HashMap<>();
	Map<ConfigPropertyType, Map<String,String>> cliUserOptionMap = new HashMap<>();
	
	public CliArgsConfig() throws Exception {
		Properties props = System.getProperties();
		Enumeration keys = props.keys();
		
		cliArjunaOptionMap.put(ConfigPropertyType.ACO, new HashMap<ArjunaOption,String>());
		cliArjunaOptionMap.put(ConfigPropertyType.ATO, new HashMap<ArjunaOption,String>());
		cliUserOptionMap.put(ConfigPropertyType.UCO, new HashMap<String,String>());
		cliUserOptionMap.put(ConfigPropertyType.UTO, new HashMap<String,String>());
		
		Matcher dMatcher = null;
		Matcher uMatcher = null;
		while (keys.hasMoreElements()) {
		    String key = (String) keys.nextElement();
		    
		    dMatcher = dPattern.matcher(key);
		    if (dMatcher.find()) {
		    	ConfigPropertyType cType = ConfigPropertyType.valueOf(dMatcher.group(1).toUpperCase());
		    	String dProp;
		    	ArjunaOption dOption;
		    	
		    	try {
		    		dProp = dMatcher.group(2);
		    	} catch (Exception e) {
		    		throw new Exception(String.format("Empty Daksha Option Key provided in CLI: %s", key));
		    	}
		    	
		    	try {
		    		dOption = ArjunaSingleton.INSTANCE.normalizeSetuOption(dMatcher.group(2));
		    	} catch (Exception e) {
		    		throw new Exception(String.format("You have provided an invalid Daksha Option in CLI: %s", key));
		    	}
		    	
		    	cliArjunaOptionMap.get(cType).put(dOption, (String) props.getProperty(key));
		    	continue;
		    }
		    
		    uMatcher = uPattern.matcher(key);
		    if (uMatcher.find()) {
		    	ConfigPropertyType cType = ConfigPropertyType.valueOf(uMatcher.group(1).toUpperCase());
		    	String uProp;
		    	
		    	try {
		    		uProp = ArjunaSingleton.INSTANCE.normalizeUserOption(uMatcher.group(2));
		    	} catch (Exception e) {
		    		throw new Exception(String.format("Empty User Option key provided in CLI: %s", key));
		    	}
		    	
		    	cliUserOptionMap.get(cType).put(uProp, (String) props.getProperty(key));
		    	continue;
		    }
		}
	}
	
	public Map<ArjunaOption,String> getArjunaCentralOptions(){
		return this.cliArjunaOptionMap.get(ConfigPropertyType.ACO);
	}
	
	public Map<ArjunaOption,String> getArjunaTestOptions(){
		return this.cliArjunaOptionMap.get(ConfigPropertyType.ATO);
	}
	
	public Map<String,String> getUserCentralOptions(){
		return this.cliUserOptionMap.get(ConfigPropertyType.UCO);
	}
	
	public Map<String,String> getUserTestOptions(){
		return this.cliUserOptionMap.get(ConfigPropertyType.UTO);
	}

}
