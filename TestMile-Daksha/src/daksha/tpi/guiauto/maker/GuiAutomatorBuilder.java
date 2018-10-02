package daksha.tpi.guiauto.maker;

import daksha.tpi.TestContext;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

public abstract class GuiAutomatorBuilder {
	private TestContext testContext = null;
	private GuiAutomationContext automationContext = null;
	
	public GuiAutomatorBuilder(TestContext testContext) throws Exception{
		this.testContext = testContext;
		this.automationContext = testContext.getGuiAutoContext();
	}
	
	protected TestContext getTestContext() {
		return this.testContext;
	}
	
	protected GuiAutomationContext getAutomationContext() {
		return this.automationContext;
	}

}
