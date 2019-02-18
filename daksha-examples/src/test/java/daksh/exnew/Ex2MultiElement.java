package daksh.exnew;

import setuexp.DefaultGuiAutomator;
import setuexp.GuiAutomator;
import setuexp.GuiMultiElement;
import setuexp.With;

public class Ex2MultiElement {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		automator.launch();
		automator.goToUrl("http://192.168.56.103/wp-admin");
		
		automator.element(With.LINK_TEXT,"Posts").click();
		automator.element(With.LINK_TEXT,"Categories").click();
		GuiMultiElement checkboxes = automator.multiElement(With.NAME,"delete_tags[]");
		checkboxes.getInstanceAtIndex(0).uncheck();
		checkboxes.getInstanceAtIndex(0).check();
		checkboxes.getInstanceAtIndex(0).check();
		checkboxes.getInstanceAtIndex(1).check();
		
		automator.goToUrl("http://192.168.56.103/wp-login.php?action=logout");
		automator.quit();
	}

}
