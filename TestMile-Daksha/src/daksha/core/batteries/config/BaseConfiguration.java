package daksha.core.batteries.config;

import java.util.HashMap;
import java.util.Map;

import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.DakshaOption;

public class BaseConfiguration implements Configuration {
	private Map<String, ConfigProperty> options = new HashMap<String, ConfigProperty>();
	
	 public BaseConfiguration() throws Exception {
	 }
	
	 public BaseConfiguration(Map<String, String> map) throws Exception {
		 if (map!= null) {
			 addAll(map);
		 }
	 }
	 
	 public BaseConfiguration(Configuration conf) throws Exception {
		 if (conf != null) {
			 options.putAll(conf.getAllOptions());
		 }
	 }

	@Override
	public Map<String, ConfigProperty> getAllOptions(){
		 return this.options;
	 }
	 
	@Override
	public void add(DakshaOption option, String value) throws Exception {
		optionsHandler.process(options, option, value);
	 } 
	
	@Override
	public void add(String option, String value) throws Exception {
		optionsHandler.process(options, option, value); 
	 } 

	@Override
	public void add(String option, Value value) throws Exception {
		optionsHandler.process(options, option, value);
	}
	 
	@Override
	public void addAll(Map<String,String> map) throws Exception {
		 for (String k : map.keySet()) {
			 this.add(k, map.get(k));
		 } 
	 } 
	
	public Value value(String option) throws Exception {
		try {
			return this.options.get(Configuration.convertOptionToPath(option)).value();
		} catch (NullPointerException e) {
			return Configuration.notSetValue;
		}
	}
	
	public Value value(DakshaOption option) throws Exception {
		try {
			return this.options.get(Configuration.convertOptionToPath(option)).value();
		} catch (NullPointerException e) {
			return Configuration.notSetValue;
		}
	}
	
}
