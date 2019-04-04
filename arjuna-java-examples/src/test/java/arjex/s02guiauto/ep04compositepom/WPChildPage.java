package arjex.s02guiauto.ep04compositepom;

import arjuna.tpi.guiauto.Gui;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.SimpleBaseChildGui;

public abstract class WPChildPage extends SimpleBaseChildGui {
	
	public WPChildPage(GuiAutomator automator, Gui parent) throws Exception {
		super(automator, parent, "wordpress");
	}

}
