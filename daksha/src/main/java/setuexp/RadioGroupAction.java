package setuexp;

public class RadioGroupAction extends AbstractGuiAction {
	
	public RadioGroupAction(RadioGroup element, RadioGroupActionType action) throws Exception {
		super();		
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", element.getAutomator().getSetuId());
		this.getActionRequest().addArg("radiogroupSetuId", element.getSetuId());
	}

}
