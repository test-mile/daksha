package setuexp;

public class GuiAutomatorAction extends AbstractGuiAction {
	
	public GuiAutomatorAction(GuiAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
	}

	public GuiAutomatorAction(GuiAutomator automator, GuiAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", automator.getSetuId());
	}

}
