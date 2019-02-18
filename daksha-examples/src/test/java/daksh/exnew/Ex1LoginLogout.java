package daksh.exnew;

import setuexp.DefaultGuiAutomator;
import setuexp.GuiAutomator;
import setuexp.With;

public class Ex1LoginLogout {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		automator.launch();
		automator.goToUrl("http://192.168.56.103/wp-admin");
		automator.element(With.ID, "user_login").setText("user");
		automator.element(With.ID, "user_pass").setText("bitnami");
		automator.element(With.ID, "wp-submit").click();
		automator.element(With.CLASS_NAME, "welcome-view-site").waitUntilClickable();
		automator.goToUrl("http://192.168.56.103/wp-login.php?action=logout");
		automator.quit();
	}

}
