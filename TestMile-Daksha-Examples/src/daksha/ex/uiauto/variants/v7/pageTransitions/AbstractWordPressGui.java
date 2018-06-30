package daksha.ex.uiauto.variants.v7.pageTransitions;

import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.uiauto.gui.DefaultGui;

public class AbstractWordPressGui extends DefaultGui{

	public AbstractWordPressGui(GuiAutomatorProxy automator) throws Exception {
		super(automator);
	}
	
	protected String getDefPath() throws Exception {
		return String.format("wordpress/%s.gns", this.getClass().getSimpleName());
	}

}
