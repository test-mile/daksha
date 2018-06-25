package daksha.tpi.cleanup.constructor;

import daksha.core.batteries.config.TestContext;
import daksha.tpi.cleanup.enums.UiAutomationContext;

public abstract class UiAutomatorBuilder {
	private TestContext testContext = null;
	private UiAutomationContext automationContext = null;
	
	public UiAutomatorBuilder(TestContext testContext) throws Exception{
		this.testContext = testContext;
		this.automationContext = testContext.getAutomationContext();
	}
	
	protected TestContext getTestContext() {
		return this.testContext;
	}
	
	protected UiAutomationContext getAutomationContext() {
		return this.automationContext;
	}

}
