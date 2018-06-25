package daksha.core.batteries.config;

import java.util.Map;

import daksha.tpi.meaningful.enums.GuiAutomationContext;
import daksha.tpi.enums.DakshaOption;

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

}
