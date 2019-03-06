package daksha.ex.selenium.using.gui;

import com.testmile.daksha.tpi.guiauto.DefaultGui;
import com.testmile.setu.requester.guiauto.With;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;

public class WordPress extends DefaultGui{

	public WordPress(GuiAutomator automator) throws Exception {
		super(automator);
	}

	public void login() throws Exception {
		this.getAutomator().launch();
		this.goToUrl("http://192.168.56.103/wp-admin");
		this.element(With.ID, "user_login").setText("user");
		this.element(With.ID, "user_pass").setText("bitnami");
		this.element(With.ID, "wp-submit").click();
		this.element(With.CLASS_NAME, "welcome-view-site").waitUntilClickable();
	}
	
	public void logout() throws Exception {
		this.goToUrl("http://192.168.56.103/wp-login.php?action=logout");
		this.getAutomator().quit();		
	}
}
