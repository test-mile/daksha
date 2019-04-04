package arjex.s02guiauto.ep04compositepom;

import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.With;

public class HomePage extends WPBasePage{

	public HomePage(GuiAutomator automator) throws Exception {
		super(automator);
	}
	
	public DashboardPage login() throws Exception {
		this.Browser().goToUrl(this.getAutomator().getConfig().getUserOptionValue("wp.login.url").asString());
		this.Element(With.id("user_login")).setText("user");
		this.Element(With.id("user_pass")).setText("bitnami");
		this.Element(With.id("wp-submit")).click();
		this.Element(With.className("welcome-view-site")).waitUntilClickable();
		return new DashboardPage(this.getAutomator());
	}
	
}
