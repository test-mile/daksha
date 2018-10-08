package daksha.core.batteries.config;

import java.util.Map;

import daksha.tpi.batteries.container.Value;

public class ContextConfiguration extends BaseConfiguration{
	
	 public ContextConfiguration(Configuration centralConf, Map<String, String> contextOptions) throws Exception {
		 super(centralConf);
		 this.addAll(contextOptions);
	 }
	 
	 public ContextConfiguration(Configuration centralConf) throws Exception {
		 super(centralConf);
	 }
}
