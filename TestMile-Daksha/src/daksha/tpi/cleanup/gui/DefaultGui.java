package daksha.tpi.cleanup.gui;

import daksha.core.cleanup.automator.proxy.GuiAutomatorProxy;

public class DefaultGui extends BaseGui{
	
	public DefaultGui(
			String name,
			GuiAutomatorProxy automator,
			String defFilePath) throws Exception{
		super(name, automator, defFilePath);
	}
	
	public DefaultGui(
			Gui parent,
			String uiLabel, 
			GuiAutomatorProxy automator, 
			String defFilePath) throws Exception {
		super(parent, uiLabel, automator, defFilePath);
	}

	@Override
	protected void validateLoaded() throws Throwable {
		// Do nothing. It is supposed to be used only for bulk gui creation.
	}

}
