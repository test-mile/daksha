package daksha.ex.guiauto.model5.pageWiseGuiWithInheritance;

import daksha.core.cleanup.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.cleanup.gui.DefaultGui;

public class AbstractWPGui extends DefaultGui{

	public AbstractWPGui(GuiAutomatorProxy automator) throws Exception {
		super(automator);
	}
	
	protected String getDefPath() throws Exception {
		return String.format("wordpress/%s.ini", this.getClass().getSimpleName());
	}

}
