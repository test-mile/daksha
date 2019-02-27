package daksh.exnew;

import com.testmile.daksha.tpi.guiauto.GuiAutomator;
import com.testmile.daksha.tpi.guiauto.With;

public class WPLoginLogout {
	
	public static void login(GuiAutomator automator) throws Exception {
		automator.launch();
		automator.goToUrl("http://192.168.56.103/wp-admin");
		automator.element(With.ID, "user_login").setText("user");
		automator.element(With.ID, "user_pass").setText("bitnami");
		automator.element(With.ID, "wp-submit").click();
		automator.element(With.CLASS_NAME, "welcome-view-site").waitUntilClickable();
	}
	
	public static void logout(GuiAutomator automator) throws Exception {
		automator.goToUrl("http://192.168.56.103/wp-login.php?action=logout");
		automator.quit();		
	}

}
