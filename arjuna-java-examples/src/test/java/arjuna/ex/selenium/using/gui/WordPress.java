package arjuna.ex.selenium.using.gui;

import com.testmile.arjuna.tpi.guiauto.GuiAutomator;
import com.testmile.arjuna.tpi.guiauto.SimpleBaseGui;
import com.testmile.arjuna.tpi.guiauto.With;

public class WordPress extends SimpleBaseGui{

	public WordPress(GuiAutomator automator) throws Exception {
		super(automator, "simpleapp");
	}

	public void login() throws Exception {
		this.browser().goToUrl("http://192.168.56.103/wp-admin");
		this.element(With.id("user_login")).setText("user");
		this.element(With.id("user_pass")).setText("bitnami");
		this.element(With.id("wp-submit")).click();
		this.element(With.className("welcome-view-site")).waitUntilClickable();
	}
	
	public void logout() throws Exception {
		this.browser().goToUrl("http://192.168.56.103/wp-login.php?action=logout");
		this.getAutomator().quit();		
	}
}
