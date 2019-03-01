package com.testmile.daksha.core.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.testmile.trishanku.tpi.enums.SetuOption;

public class CLIConfiguration {
	Pattern dPattern = Pattern.compile("(?i)(DCentral|DTest)\\s*:\\s*(\\S+)\\s*");
	Pattern uPattern = Pattern.compile("(?i)(UCentral|UTest)\\s*:\\s*(\\S+)\\s*");
	Map<ConfigPropertyType, Map<SetuOption,String>> cliDakshaOptionsMap = new HashMap<>();
	Map<ConfigPropertyType, Map<String,String>> cliUserOptionsMap = new HashMap<>();
	
	public CLIConfiguration() throws Exception {
		Properties props = System.getProperties();
		Enumeration keys = props.keys();
		
		cliDakshaOptionsMap.put(ConfigPropertyType.DCENTRAL, new HashMap<SetuOption,String>());
		cliDakshaOptionsMap.put(ConfigPropertyType.DTEST, new HashMap<SetuOption,String>());
		cliUserOptionsMap.put(ConfigPropertyType.UCENTRAL, new HashMap<String,String>());
		cliUserOptionsMap.put(ConfigPropertyType.UTEST, new HashMap<String,String>());
		
		Matcher dMatcher = null;
		Matcher uMatcher = null;
		while (keys.hasMoreElements()) {
		    String key = (String) keys.nextElement();
		    
		    dMatcher = dPattern.matcher(key);
		    if (dMatcher.find()) {
		    	ConfigPropertyType cType = ConfigPropertyType.valueOf(dMatcher.group(1).toUpperCase());
		    	String dProp;
		    	SetuOption dOption;
		    	
		    	try {
		    		dProp = dMatcher.group(2);
		    	} catch (Exception e) {
		    		throw new Exception(String.format("Empty Daksha Option Key provided in CLI: %s", key));
		    	}
		    	
		    	try {
		    		dOption = Configuration.convertToDakshaOption(dMatcher.group(2));
		    	} catch (Exception e) {
		    		throw new Exception(String.format("You have provided an invalid Daksha Option in CLI: %s", key));
		    	}
		    	
		    	cliDakshaOptionsMap.get(cType).put(dOption, (String) props.getProperty(key));
		    	continue;
		    }
		    
		    uMatcher = uPattern.matcher(key);
		    if (uMatcher.find()) {
		    	ConfigPropertyType cType = ConfigPropertyType.valueOf(uMatcher.group(1).toUpperCase());
		    	String uProp;
		    	
		    	try {
		    		uProp = uMatcher.group(2).replace("_", "\\.").toLowerCase();
		    	} catch (Exception e) {
		    		throw new Exception(String.format("Empty User Option key provided in CLI: %s", key));
		    	}
		    	
		    	cliUserOptionsMap.get(cType).put(uProp, (String) props.getProperty(key));
		    	continue;
		    }
		}
	}
	
	public Map<SetuOption,String> getDakshaCentralOptions(){
		return this.cliDakshaOptionsMap.get(ConfigPropertyType.DCENTRAL);
	}
	
	public Map<SetuOption,String> getDakshaTestOptions(){
		return this.cliDakshaOptionsMap.get(ConfigPropertyType.DTEST);
	}
	
	public Map<String,String> getUserCentralOptions(){
		return this.cliUserOptionsMap.get(ConfigPropertyType.UCENTRAL);
	}
	
	public Map<String,String> getUserTestOptions(){
		return this.cliUserOptionsMap.get(ConfigPropertyType.UTEST);
	}

}
