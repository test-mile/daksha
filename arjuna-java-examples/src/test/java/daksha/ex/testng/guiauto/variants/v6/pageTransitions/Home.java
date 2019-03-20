package daksha.ex.testng.guiauto.variants.v6.pageTransitions;

import com.testmile.arjuna.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.arjuna.tpi.guiauto.element.SetuClientGuiElement;

public class Home extends BaseSimpleGui{

	public Home(SetuClientGuiAutomator automator) throws Exception {
		super(automator);
	}
	
	protected void load() throws Exception{
		this.goTo(this.getTestContext().getValue("wp.admin.url").asString());
	}
	
	public Dashboard login() throws Exception {
		SetuClientGuiElement userTextBox = this.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(this.getTestContext().getValue("wp.username").asString());
		this.element("PASSWORD").enterText(this.getTestContext().getValue("wp.password").asString());
		this.element("SUBMIT").click();		
		this.waitForBody();
		return new Dashboard(this.getAutomator());
	}

}
