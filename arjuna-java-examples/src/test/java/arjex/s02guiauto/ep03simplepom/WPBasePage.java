package arjex.s02guiauto.ep03simplepom;

import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.SimpleBaseGui;

public class WPBasePage extends SimpleBaseGui{

	public WPBasePage(GuiAutomator automator) throws Exception {
		super(automator, "wordpress");
	}
	
	public void logout() throws Exception {
		this.Browser().goToUrl(this.getAutomator().getConfig().getUserOptionValue("wp.logout.url").asString());
	}
}
