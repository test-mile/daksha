package daksh.exnew;

import setuexp.Alert;
import setuexp.DefaultGuiAutomator;
import setuexp.GuiAutomator;
import setuexp.GuiMultiElement;
import setuexp.With;

public class Ex3Alerts {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		WPLoginLogout.login(automator);
		
		automator.executeJavaScript("alert('dummy')");
		automator.alert().confirm();
		automator.executeJavaScript("alert('dummy')");
		automator.alert().dismiss();
		
		automator.executeJavaScript("alert('Sample')");
		Alert alert = automator.alert();
		assert alert.getText() == "Sample";
		alert.confirm();
		
		automator.executeJavaScript("prompt('Are You Sure?')");
		alert = automator.alert();
		alert.sendText("Yes");	
		alert.confirm();
		
		WPLoginLogout.logout(automator);
	}

}
