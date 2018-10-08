package daksha.ex.testng.guiauto.variants.v7.pageTransitions;

import daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.guiauto.element.GuiElement;

public class Home extends BaseSimpleGui{

	public Home(GuiAutomatorProxy automator) throws Exception {
		super(automator);
	}
	
	protected void load() throws Exception{
		this.goTo(this.getTestContext().getValue("wp.admin.url").asString());
	}
	
	public Dashboard login() throws Exception {
		GuiElement userTextBox = this.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(this.getTestContext().getValue("wp.username").asString());
		this.element("PASSWORD").enterText(this.getTestContext().getValue("wp.password").asString());
		this.element("SUBMIT").click();		
		this.waitForBody();
		return new Dashboard(this.getAutomator());
	}

}
