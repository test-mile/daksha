package arjuna.ex.selenium.using.gui;

import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.SimpleBaseGui;
import arjuna.tpi.guiauto.With;

public class WordPress extends SimpleBaseGui{

	public WordPress(GuiAutomator automator) throws Exception {
		super(automator, "simpleapp");
	}

	public void login() throws Exception {
		this.Browser().goToUrl("http://192.168.56.103/wp-admin");
		this.Element(With.id("user_login")).setText("user");
		this.Element(With.id("user_pass")).setText("bitnami");
		this.Element(With.id("wp-submit")).click();
		this.Element(With.className("welcome-view-site")).waitUntilClickable();
	}
	
	public void logout() throws Exception {
		this.Browser().goToUrl("http://192.168.56.103/wp-login.php?action=logout");
		this.getAutomator().quit();		
	}
}
