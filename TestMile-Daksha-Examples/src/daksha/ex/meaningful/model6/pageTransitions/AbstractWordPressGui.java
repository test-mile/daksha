package daksha.ex.meaningful.model6.pageTransitions;

import daksha.core.meaningful.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.meaningful.gui.DefaultGui;

public class AbstractWordPressGui extends DefaultGui{

	public AbstractWordPressGui(GuiAutomatorProxy automator) throws Exception {
		super(automator);
	}
	
	protected String getDefPath() throws Exception {
		return String.format("wordpress/%s.ini", this.getClass().getSimpleName());
	}

}
