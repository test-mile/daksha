package daksha.core.batteries.context;

import java.util.Map;

import daksha.DakshaSingleton;
import daksha.core.batteries.config.CentralConfiguration;
import daksha.core.batteries.config.ContextConfiguration;
import daksha.tpi.CentralTestContext;
import daksha.tpi.batteries.container.Value;

public class CommonTestContext extends BaseTestContext implements CentralTestContext {

	public CommonTestContext(CentralConfiguration config) throws Exception {	
		super("central");
		this.setContextConfig(new ContextConfiguration(config));
	}
	
	 public void freeze() throws Exception{
		super.setFrozen();
		DakshaSingleton.INSTANCE.actOnFrozenCentralContext();
	 }

}
