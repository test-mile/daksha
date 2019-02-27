package setuexp;

public class DefaultDropDown extends DefaultSetuObject implements DropDown {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/dropdown/action";

	public DefaultDropDown(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	@Override
	public boolean hasVisibleTextSelected(String text) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.HAS_VISIBLE_TEXT_SELECTED);
		action.addArg("text", text);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public boolean hasValueSelected(String value) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.HAS_VALUE_SELECTED);
		action.addArg("value", value);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public boolean hasIndexSelected(int index) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.HAS_INDEX_SELECTED);
		action.addArg("index", index);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public String getFirstSelectedOptionText() throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.GET_FIRST_SELECTED_OPTION_TEXT);
		Response response = this.setuClient.post(baseActionUri, action);
		return (String) response.getData().get("text");
	}

	@Override
	public void selectByValue(String value) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.SELECT_BY_VALUE);
		action.addArg("value", value);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void selectByVisibleText(String text) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.SELECT_BY_VISIBLE_TEXT);
		action.addArg("text", text);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void selectByIndex(int index) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.SELECT_BY_INDEX);
		action.addArg("index", index);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public AppAutomator getAutomator() {
		return this.automator;
	}

}
