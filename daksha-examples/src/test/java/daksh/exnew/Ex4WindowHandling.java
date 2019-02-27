package daksh.exnew;

import setuexp.DefaultGuiAutomator;
import setuexp.GuiAutomator;
import setuexp.With;

public class Ex4WindowHandling {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		WPLoginLogout.login(automator);
		
		automator.maximizeWindow();
		System.out.println(automator.getWindowTitle());
		automator.executeJavaScript("window.open('/abc')");
		automator.switchToNewWindow();
		System.out.println(automator.getWindowTitle());
		automator.closeCurrentWindow();
		automator.executeJavaScript("window.open('/def')");
		automator.executeJavaScript("window.open('/xyz')");
		automator.closeAllChildWindows();
		System.out.println(automator.getWindowTitle());
		
		WPLoginLogout.logout(automator);
	}

}
