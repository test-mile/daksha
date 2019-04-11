package arjex.s02guiauto.ep04compositepom;

import arjuna.tpi.guiauto.Gui;
import arjuna.tpi.guiauto.GuiAutomator;

public class TopMenuBar extends WPChildPage{

	public TopMenuBar(GuiAutomator automator, Gui parent) throws Exception {
		super(automator, parent);
	}

	public HomePage logout() throws Exception {
		this.Browser().goToUrl(this.getAutomator().getConfig().getUserOptionValue("wp.logout.url").asString());	
		return new HomePage(this.getAutomator());
	}
}
