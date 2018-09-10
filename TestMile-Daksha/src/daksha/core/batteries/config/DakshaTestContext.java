package daksha.core.batteries.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import daksha.tpi.TestContext;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

public class DakshaTestContext implements TestContext {
	private String name;
	private Map<String, String> map;
	private ContextConfiguration config;
	
	public DakshaTestContext(String name) {
		this.name = name;
		this.map = new HashMap<String, String>();
	}
	
	public DakshaTestContext(String name, Map<String, String> map) {
		this.name = name;
		this.map = map;
	}
	
	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#setOptions(daksha.core.batteries.config.ContextConfiguration)
	 */
	@Override
	public void setOptions(ContextConfiguration config) {
		this.config = config;
	}	
	
	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#add(java.lang.String, java.lang.String)
	 */
	@Override
	public void add(String option, String value) {
		this.map.put(option, value);
	}
	
	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getAsMap()
	 */
	@Override
	public Map<String, String> getAsMap(){
		return this.map;
	}
	
	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getConfig()
	 */
	@Override
	public Configuration getConfig() {
		return this.config;
	}

	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getAutomationContext()
	 */
	@Override
	public GuiAutomationContext getAutomationContext() throws Exception {
		return GuiAutomationContext.valueOf(this.config.value(DakshaOption.GUIAUTO_CONTEXT).asString());
	}

	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#setAutomationContext(daksha.tpi.guiauto.enums.GuiAutomationContext)
	 */
	@Override
	public void setAutomationContext(GuiAutomationContext context) throws Exception {
		this.config.add(DakshaOption.GUIAUTO_CONTEXT, context.toString());
	}
	
	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getAppDir()
	 */
	@Override
	public String getAppDir() throws Exception {
		GuiAutomationContext aContext = getConfig().value(DakshaOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
		Value testOS = null;
		if (GuiAutomationContext.isDesktopContext(aContext)) {
			testOS = getConfig().value(DakshaOption.TEST_PC_PLATFORM);
		} else {
			testOS = getConfig().value(DakshaOption.TEST_MOBILE_PLATFORM);
		}
		return getConfig().value(DakshaOption.APPS_DIR).asString() 
				+ File.separator 
				+ testOS.asString().toLowerCase() + File.separator; 
	}
}
