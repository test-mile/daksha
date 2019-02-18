package setuexp;

public class DropDownAction extends AbstractGuiAction {
	
	public DropDownAction(DropDown element, DropDownActionType action) throws Exception {
		super();		
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", element.getAutomator().getSetuId());
		this.getActionRequest().addArg("dropdownSetuId", element.getSetuId());
	}

}
