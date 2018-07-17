package daksha.ex.guiauto.variants.v7.pageTransitions;

import daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.guiauto.gui.DefaultGui;

public class AbstractWordPressGui extends DefaultGui{

	public AbstractWordPressGui(GuiAutomatorProxy automator) throws Exception {
		super(automator);
	}
	
	protected String getDefPath() throws Exception {
		return String.format("wordpress/%s.gns", this.getClass().getSimpleName());
	}

}
