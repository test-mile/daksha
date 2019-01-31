package com.testmile.daksha.core.batteries.context;

import java.util.Map;

import com.testmile.daksha.DakshaSingleton;
import com.testmile.daksha.core.batteries.config.CentralConfiguration;
import com.testmile.daksha.core.batteries.config.ContextConfiguration;
import com.testmile.daksha.tpi.enums.DakshaOption;

public class CommonTestContext extends BaseTestContext {

	public CommonTestContext(CentralConfiguration config) throws Exception {	
		super("central");
		this.setContextConfig(new ContextConfiguration(config));
	}
	
	 public void freeze() throws Exception{
		validateFrozen();
		Map<DakshaOption, String> properties = DakshaSingleton.INSTANCE.getDakshaCentralCliOptions();
		for (DakshaOption name: properties.keySet()) {
			setOption(name, properties.get(name));
		}
		
		Map<String, String> userProps = DakshaSingleton.INSTANCE.getUserCentralCliOptions();
		for (String name: userProps.keySet()) {
			setOption(name, userProps.get(name));
		}
		super.freeze();
		DakshaSingleton.INSTANCE.actOnFrozenCentralContext();
	 }

}
