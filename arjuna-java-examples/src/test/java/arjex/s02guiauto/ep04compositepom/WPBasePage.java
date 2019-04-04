package arjex.s02guiauto.ep04compositepom;

import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.SimpleBaseGui;

public class WPBasePage extends SimpleBaseGui{

	public WPBasePage(GuiAutomator automator) throws Exception {
		super(automator, "wordpress");
	}

}
