package com.testmile.daksha.core.batteries.context;

import java.util.Map;

import com.testmile.daksha.core.batteries.config.CentralConfiguration;
import com.testmile.daksha.core.batteries.config.ContextConfiguration;
import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.trishanku.TrishankuSingleton;

public class CommonTestContext extends BaseTestContext {

	public CommonTestContext(CentralConfiguration config) throws Exception {	
		super("central");
		this.setContextConfig(new ContextConfiguration(config));
	}
	
	 public void freeze() throws Exception{
		validateFrozen();
		Map<DakshaOption, String> properties = TrishankuSingleton.INSTANCE.getDakshaCentralCliOptions();
		for (DakshaOption name: properties.keySet()) {
			setOption(name, properties.get(name));
		}
		
		Map<String, String> userProps = TrishankuSingleton.INSTANCE.getUserCentralCliOptions();
		for (String name: userProps.keySet()) {
			setOption(name, userProps.get(name));
		}
		super.freeze();
		TrishankuSingleton.INSTANCE.actOnFrozenCentralContext();
	 }

}
