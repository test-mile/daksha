package setuexp;

public class GuiAction extends AbstractGuiAutoAction {
	
	public GuiAction(GuiAppAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
	}

	public GuiAction(Gui parentGui, GuiActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("guiSetuId", parentGui.getSetuId());
	}

}
