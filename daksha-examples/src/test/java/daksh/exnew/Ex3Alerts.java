package daksh.exnew;

import setuexp.DefaultGuiAutomator;
import setuexp.GuiAutomator;
import setuexp.GuiMultiElement;
import setuexp.With;

public class Ex3Alerts {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		WPLoginLogout.login(automator);
		
		automator.executeJavaScript("alert('dummy')");
		automator.confirmAlert();
		automator.executeJavaScript("alert('dummy')");
		automator.dismissAlert();
		automator.executeJavaScript("alert('Sample')");
		assert automator.getTextFromAlert() == "Sample";
		automator.confirmAlert();
		automator.executeJavaScript("prompt('Are You Sure?')");
		automator.sendTextToAlert("Yes");	
		automator.confirmAlert();
		
		WPLoginLogout.logout(automator);
	}

}
