package setuexp;

public class WindowAction extends AbstractGuiAutoAction {
	
	public WindowAction(BasicWindow element, WindowActionType action) throws Exception {
		super();		
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", element.getAutomator().getSetuId());
		this.getActionRequest().addArg("elementSetuId", element.getSetuId());
	}

}
