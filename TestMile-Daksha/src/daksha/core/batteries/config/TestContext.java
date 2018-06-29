package daksha.core.batteries.config;

import java.io.File;
import java.util.Map;

import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.uiauto.enums.GuiAutomationContext;

public class TestContext {
	private String name;
	private Map<String, String> map;
	private ContextConfiguration config;
	
	public TestContext(String name, Map<String, String> map) {
		this.name = name;
		this.map = map;
	}
	
	public void setOptions(ContextConfiguration config) {
		this.config = config;
	}	
	
	public Map<String, String> getAsMap(){
		return this.map;
	}
	
	public String getName() {
		return this.name;
	}

	public Configuration getConfig() {
		return this.config;
	}

	public GuiAutomationContext getAutomationContext() throws Exception {
		return GuiAutomationContext.valueOf(this.config.value(DakshaOption.GUIAUTO_CONTEXT).asString());
	}

	public void setAutomationContext(GuiAutomationContext context) throws Exception {
		this.config.add(DakshaOption.GUIAUTO_CONTEXT, context.toString());
	}
	
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
