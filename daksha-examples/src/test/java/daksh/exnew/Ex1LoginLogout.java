package daksh.exnew;

import com.testmile.daksha.core.guiauto.automator.DefaultGuiAutomator;
import com.testmile.daksha.tpi.guiauto.GuiAutomator;

public class Ex1LoginLogout {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		WPLoginLogout.login(automator);
		WPLoginLogout.logout(automator);
	}

}
