package daksha.core.batteries.context;

import java.util.HashMap;
import java.util.Map;

import daksha.Daksha;
import daksha.DakshaSingleton;
import daksha.core.batteries.config.BaseConfiguration;
import daksha.core.batteries.config.CentralConfiguration;
import daksha.core.batteries.config.ContextConfiguration;
import daksha.tpi.CentralTestContext;
import daksha.tpi.TestContext;

public class CommonTestContext extends AbstractTestContext implements CentralTestContext {

	public CommonTestContext(CentralConfiguration config) throws Exception {	
		super("central");
		this.setContextConfig(new ContextConfiguration(config));
	}
	
	 public void freeze() throws Exception{
		super.setFrozen();
		DakshaSingleton.INSTANCE.actOnFrozenCentralContext();
	 }
}
