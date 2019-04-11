package arjex.s02guiauto.ep04compositepom;

import arjuna.tpi.guiauto.Gui;
import arjuna.tpi.guiauto.GuiAutomator;

public class LeftNavSideBar extends WPChildPage{

	public LeftNavSideBar(GuiAutomator automator, Gui parent) throws Exception {
		super(automator, parent);
	}
	
	public SettingsPage goToSettings() throws Exception {
		this.Element("settings").click();
		return new SettingsPage(this.getAutomator());
	}
}
