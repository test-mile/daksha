package arjex.s02guiauto.ep03simplepom;

import arjuna.tpi.guiauto.GuiAutomator;

public class DashboardPage extends WPBasePage{

	public DashboardPage(GuiAutomator automator) throws Exception {
		super(automator);
	}
	
	public SettingsPage goToSettings() throws Exception {
		this.Element("settings").click();
		return new SettingsPage(this.getAutomator());
	}
}
