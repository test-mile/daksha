package setuexp;

public class GuiMgrAction extends AbstractGuiAutoAction {
	
	public GuiMgrAction(GuiAppAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
	}

	public GuiMgrAction(Gui gui, GuiMgrActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("guiName", gui.getClass().getSimpleName());
		this.getActionRequest().addArg("guiQualifiedName", gui.getClass().getName());
	}

}
