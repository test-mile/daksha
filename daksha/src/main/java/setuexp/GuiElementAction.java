package setuexp;

public class GuiElementAction extends AbstractGuiAction {
	
	public GuiElementAction(GuiElement element, GuiElementActionType action) throws Exception {
		super();		
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", element.getAutomator().getSetuId());
		if (element.isPartial()) {
			this.getActionRequest().addArg("multiElementSetuId", element.getSetuId());
			this.getActionRequest().addArg("isInstanceAction", true);
			this.getActionRequest().addArg("instanceIndex", true);
		} else {
			this.getActionRequest().addArg("elementSetuId", element.getSetuId());
		}
	}

}
