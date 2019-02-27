package setuexp;

public class DefaultRadioGroup extends DefaultSetuObject implements RadioGroup {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/radiogroup/action";

	public DefaultRadioGroup(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	@Override
	public boolean hasValueSelected(String value) throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.HAS_VALUE_SELECTED);
		action.addArg("value", value);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public boolean hasIndexSelected(int index) throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.HAS_INDEX_SELECTED);
		action.addArg("index", index);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public String getFirstSelectedOptionValue() throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.GET_FIRST_SELECTED_OPTION_VALUE);
		Response response = this.setuClient.post(baseActionUri, action);
		return (String) response.getData().get("value");
	}

	@Override
	public void selectByValue(String value) throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.SELECT_BY_VALUE);
		action.addArg("value", value);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void selectByIndex(int index) throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.SELECT_BY_INDEX);
		action.addArg("index", index);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public AppAutomator getAutomator() {
		return this.automator;
	}

}
