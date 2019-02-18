package daksh.exnew;

import setuexp.DefaultGuiAutomator;
import setuexp.DropDown;
import setuexp.GuiAutomator;
import setuexp.GuiMultiElement;
import setuexp.RadioGroup;
import setuexp.With;

public class Ex6RadioGroup {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		automator.launch();
		automator.goToUrl("http://192.168.56.103/wp-admin");
		automator.element(With.ID, "user_login").setText("user");
		automator.element(With.ID, "user_pass").setText("bitnami");
		automator.element(With.ID, "wp-submit").click();
		automator.element(With.CLASS_NAME, "welcome-view-site").waitUntilClickable();
		
		automator.element(With.LINK_TEXT,"Settings").click();
		RadioGroup dateFormat = automator.radioGroup(With.NAME, "date_format");
		System.out.println(dateFormat.hasValueSelected("Y-m-d"));
		System.out.println(dateFormat.hasIndexSelected(1));
		System.out.println(dateFormat.getFirstSelectedOptionValue());
		dateFormat.selectByValue("\\c\\u\\s\\t\\o\\m");
		dateFormat.selectByIndex(2);
		
		automator.goToUrl("http://192.168.56.103/wp-login.php?action=logout");
		automator.quit();
	}

}
