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
	Map<ConfigPropertyType, Map<String,String>> cliArjunaOptionMap = new HashMap<>();
	Map<ConfigPropertyType, Map<String,String>> cliUserOptionMap = new HashMap<>();
	
	public CliArgsConfig() throws Exception {
		Properties props = System.getProperties();
		Enumeration keys = props.keys();
		
		cliArjunaOptionMap.put(ConfigPropertyType.ACO, new HashMap<String,String>());
		cliArjunaOptionMap.put(ConfigPropertyType.ATO, new HashMap<String,String>());
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
		    		throw new Exception(String.format("Empty Arjuna Option Key provided in CLI: %s", key));
		    	}
		    	
		    	try {
		    		dOption = ArjunaSingleton.INSTANCE.normalizeArjunaOption(dMatcher.group(2));
		    	} catch (Exception e) {
		    		throw new Exception(String.format("You have provided an invalid Arjuna Option in CLI: %s", key));
		    	}
		    	
		    	cliArjunaOptionMap.get(cType).put(dOption.toString(), (String) props.getProperty(key));
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
	
	public Map<String,Map<String,String>> asMap(){
		Map<String,Map<String,String>> outMap = new HashMap<String,Map<String,String>>();
		outMap.put("arjunaCentralOptions", getArjunaCentralOptions());
		outMap.put("arjunaTestOptions", getArjunaTestOptions());
		outMap.put("userCentralOptions", getUserCentralOptions());
		outMap.put("userTestOptions", getUserTestOptions());
		return outMap;
	}
	
	private Map<String,String> getArjunaCentralOptions(){
		return this.cliArjunaOptionMap.get(ConfigPropertyType.ACO);
	}
	
	private Map<String,String> getArjunaTestOptions(){
		return this.cliArjunaOptionMap.get(ConfigPropertyType.ATO);
	}
	
	private Map<String,String> getUserCentralOptions(){
		return this.cliUserOptionMap.get(ConfigPropertyType.UCO);
	}
	
	private Map<String,String> getUserTestOptions(){
		return this.cliUserOptionMap.get(ConfigPropertyType.UTO);
	}

}
