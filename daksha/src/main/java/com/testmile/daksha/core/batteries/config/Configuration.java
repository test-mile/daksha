package com.testmile.daksha.core.batteries.config;

import java.util.Map;

import com.testmile.daksha.core.value.NotSetValue;
import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.DakshaOption;

public interface Configuration {
	static final ConfigOptionHandler optionsHandler = new ConfigOptionHandler();
	static final NotSetValue notSetValue = new NotSetValue();
	
	Map<String, ConfigProperty> getAllOptions();

	void add(String k, String v) throws Exception;
	
	void add(DakshaOption option, String v) throws Exception;
	
	void add(String option, Value value) throws Exception;

	void addAll(Map<String, String> map) throws Exception;

	Value value(DakshaOption option) throws Exception;
	
	static DakshaOption convertToDakshaOption(String strOption) {
		return DakshaOption.valueOf(strOption.toUpperCase().replace(".", "_"));
	}

	static String convertOptionToPath(String strOption) {
		return strOption.toLowerCase().replace("_", ".");
	}
	
	static String convertOptionToPath(DakshaOption option) {
		return option.toString().toLowerCase().replace("_", ".");
	}
}