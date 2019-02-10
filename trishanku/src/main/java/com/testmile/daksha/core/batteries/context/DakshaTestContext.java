package com.testmile.daksha.core.batteries.context;

import java.util.HashMap;
import java.util.Map;

import com.testmile.daksha.core.batteries.config.ContextConfiguration;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.trishanku.Trishanku;
import com.testmile.trishanku.TrishankuSingleton;

public class DakshaTestContext extends BaseTestContext implements TestContext {
	protected String name;
	
	public DakshaTestContext(String name, Map<String, String> map) throws Exception {
		super(name);		
		this.setContextConfig(new ContextConfiguration(
				Trishanku.getCentralContext().getConfig(), 
		map));
	}
	
	public DakshaTestContext(String name) throws Exception {
		this(name, new HashMap<String, String>());
	}

	public DakshaTestContext(String name, TestContext parentContext, Map<String, String> overrideParams) throws Exception {
		this(name, parentContext.asRawMap());
		for(String key: overrideParams.keySet()) {
			this.setOption(key, overrideParams.get(key));
		}
	}
	
	 public void freeze() throws Exception{
		validateFrozen();
		Map<DakshaOption, String> properties = TrishankuSingleton.INSTANCE.getDakshaTestCliOptions();
		for (DakshaOption name: properties.keySet()) {
			setOption(name, properties.get(name));
		}
		
		Map<String, String> userProps = TrishankuSingleton.INSTANCE.getUserTestCliOptions();
		for (String name: userProps.keySet()) {
			setOption(name, userProps.get(name));
		}
		super.freeze();
	 }

}
