package daksh.exnew;

import com.testmile.daksha.core.guiauto.automator.DefaultGuiAutomator;
import com.testmile.daksha.tpi.guiauto.GuiAutomator;
import com.testmile.daksha.tpi.guiauto.GuiMultiElement;
import com.testmile.daksha.tpi.guiauto.With;

public class Ex2MultiElement {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		WPLoginLogout.login(automator);
		
		automator.element(With.LINK_TEXT,"Posts").click();
		automator.element(With.LINK_TEXT,"Categories").click();
		
		GuiMultiElement checkboxes = automator.multiElement(With.NAME,"delete_tags[]");
		checkboxes.getInstanceAtIndex(0).uncheck();
		checkboxes.getInstanceAtIndex(0).check();
		checkboxes.getInstanceAtIndex(0).check();
		checkboxes.getInstanceAtIndex(1).check();		
		
		WPLoginLogout.logout(automator);
	}

}
