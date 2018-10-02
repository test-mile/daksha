package daksha.core.batteries.context;

import java.util.HashMap;
import java.util.Map;

import daksha.Daksha;
import daksha.core.batteries.config.ContextConfiguration;
import daksha.core.guiauto.enums.OSType;
import daksha.tpi.TestContext;

public class DakshaTestContext extends AbstractTestContext implements TestContext {
	protected String name;
	
	public DakshaTestContext(String name, Map<String, String> map) throws Exception {
		super(name);		
		this.setContextConfig(new ContextConfiguration(
				Daksha.getCentralContext().getConfig(), 
		map));
	}
	
	public DakshaTestContext(String name) throws Exception {
		this(name, new HashMap<String, String>());
	}

}
