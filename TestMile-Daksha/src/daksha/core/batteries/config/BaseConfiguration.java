package daksha.core.batteries.config;

import java.util.Arrays;
import java.util.Map;

import daksha.core.batteries.container.OptionContainer;
import daksha.core.batteries.container.ValueContainer;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.sysauto.utils.DataUtils;

public class BaseConfiguration implements Configuration {
	private ValueContainer<DakshaOption> options = new OptionContainer();
	
	 public BaseConfiguration() throws Exception {
	 }
	
	 public BaseConfiguration(Map<String, String> map) throws Exception {
		 if (map!= null) {
			 add(map);
		 }
	 }
	 
	 public BaseConfiguration(Configuration conf) throws Exception {
		 if (conf != null) {
			 options.add(conf.getAllOptions());
		 }
	 }
	 
		private DakshaOption convertPropertyToOption(String strOption) {
			return DakshaOption.valueOf(DataUtils.join(DataUtils.split(strOption, "\\."), "_").toUpperCase());
		}

	@Override
	public ValueContainer<DakshaOption> getAllOptions(){
		 return this.options;
	 }
	 
	@Override
	public void add(DakshaOption option, String v) throws Exception {
		 options.add(option, v); 
	 } 
	
	@Override
	public void add(String k, String v) throws Exception {
		 options.add(convertPropertyToOption(k), v); 
	 } 
	 
	@Override
	public void add(Map<String,String> map) throws Exception {
		 for (String k : map.keySet()) {
			 this.add(k, map.get(k));
		 } 
	 } 
	
	public Value value(DakshaOption option) throws Exception {
		return this.options.value(option);
	}
}
