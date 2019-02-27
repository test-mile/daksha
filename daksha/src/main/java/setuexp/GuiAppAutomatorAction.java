package setuexp;

public class GuiAppAutomatorAction extends AbstractGuiAutoAction {
	
	public GuiAppAutomatorAction(GuiAppAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
	}

	public GuiAppAutomatorAction(AppAutomator automator, GuiAppAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", automator.getSetuId());
	}

}
