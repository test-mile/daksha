package setuexp;

public class MainWindowAction extends AbstractGuiAutoAction {
	
	public MainWindowAction(ChildWindow element, WindowActionType action) throws Exception {
		super();		
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", element.getAutomator().getSetuId());
		this.getActionRequest().addArg("elementSetuId", element.getSetuId());
	}

}
