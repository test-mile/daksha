package daksha.tpi.cleanup.constructor;

import daksha.core.batteries.config.TestContext;
import daksha.tpi.cleanup.enums.GuiAutomationContext;

public abstract class AutomatorBuilder {
	private TestContext testContext = null;
	private GuiAutomationContext automationContext = null;
	
	public AutomatorBuilder(TestContext testContext) throws Exception{
		this.testContext = testContext;
		this.automationContext = testContext.getAutomationContext();
	}
	
	protected TestContext getTestContext() {
		return this.testContext;
	}
	
	protected GuiAutomationContext getGuiAutomationContext() {
		return this.automationContext;
	}

}
