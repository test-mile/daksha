package setuexp;

public class DefaultGuiAutomator extends DefaultSetuObject implements GuiAutomator {
	private SetuGuiAutoSvcClient setuClient;
	
	public DefaultGuiAutomator() {
		this.setuClient = new SetuGuiAutoSvcClient();
	}
	
	public SetuGuiAutoSvcClient getSetuClient() {
		return this.setuClient;
	}

	@Override
	public void launch() throws Exception {
		Response response = this.setuClient.post("/launch", new GuiAutomatorAction(GuiAutomatorActionType.LAUNCH));
		System.out.println((String) response.getData().get("automatorSetuId"));
		this.setSetuId((String) response.getData().get("automatorSetuId"));
	}
	
	@Override
	public void quit() throws Exception {
		this.setuClient.post("/quit", new GuiAutomatorAction(this, GuiAutomatorActionType.QUIT));
	}
	
	@Override
	public GuiElement element(With with, String value) throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.CREATE_ELEMENT);
		action.addArg("withType", with);
		action.addArg("withValue", value);
		Response response = this.setuClient.post("/action", action);
		String elemSetuId = (String) response.getData().get("elementSetuId");
		return new DefaultGuiElement(this, elemSetuId);
	}
	
	@Override
	public GuiMultiElement multiElement(With with, String value) throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.CREATE_MULTIELEMENT);
		action.addArg("withType", with);
		action.addArg("withValue", value);
		Response response = this.setuClient.post("/action", action);
		String elemSetuId = (String) response.getData().get("multiElementSetuId");
		return new DefaultGuiMultiElement(this, elemSetuId);
	}
	
	@Override
	public DropDown dropdown(With with, String value) throws Exception {
		GuiElement element = this.element(with, value);
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.CONVERT_ELEMENT_TO_DROPDOWN);
		action.addArg("elementSetuId", element.getSetuId());
		Response response = this.setuClient.post("/action", action);
		return new DefaultDropDown(this, (String) response.getData().get("dropdownSetuId"));
	}

	@Override
	public RadioGroup radioGroup(With with, String value) throws Exception {
		GuiMultiElement mElement = this.multiElement(with, value);
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.CONVERT_ELEMENT_TO_RADIOGROUP);
		action.addArg("elementSetuId", mElement.getSetuId());
		Response response = this.setuClient.post("/action", action);
		return new DefaultRadioGroup(this, (String) response.getData().get("radiogroupSetuId"));
	}

	@Override
	public void goToUrl(String url) throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.NAVIGATE_BROWSER);
		action.addArg("navType", BrowserNavigationType.TO.toString());
		action.addArg("url", url);
		this.setuClient.post("/action", action);
	}

	@Override
	public void executeJavaScript(String script) throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.EXECUTE_JAVASCRIPT);
		action.addArg("script", script);
		this.setuClient.post("/action", action);
	}

	@Override
	public void confirmAlert() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.HANDLE_ALERT);
		action.addArg("handleType", AlertHandlingType.CONFIRM_ALERT.toString());
		this.setuClient.post("/action", action);
	}

	@Override
	public void dismissAlert() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.HANDLE_ALERT);
		action.addArg("handleType", AlertHandlingType.DISMISS_ALERT.toString());
		this.setuClient.post("/action", action);
	}

	@Override
	public String getTextFromAlert() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.HANDLE_ALERT);
		action.addArg("handleType", AlertHandlingType.GET_TEXT_FROM_ALERT.toString());
		Response response = this.setuClient.post("/action", action);
		return (String) response.getData().get("text");
	}

	@Override
	public void sendTextToAlert(String text) throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.HANDLE_ALERT);
		action.addArg("handleType", AlertHandlingType.GET_TEXT_FROM_ALERT.toString());
		action.addArg("text", text);
		this.setuClient.post("/action", action);
	}

	@Override
	public void maximizeWindow() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.HANDLE_WINDOWS);
		action.addArg("handleType", WindowActionType.MAXIMIZE_WINDOW.toString());
		this.setuClient.post("/action", action);
	}

	@Override
	public String getWindowTitle() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.HANDLE_WINDOWS);
		action.addArg("handleType", WindowActionType.GET_WINDOW_TITLE.toString());
		Response response = this.setuClient.post("/action", action);
		return (String) response.getData().get("title");
	}

	@Override
	public void switchToNewWindow() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.HANDLE_WINDOWS);
		action.addArg("handleType", WindowActionType.SWITCH_TO_NEW_WINDOW.toString());
		this.setuClient.post("/action", action);
	}

	@Override
	public void closeCurrentWindow() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.HANDLE_WINDOWS);
		action.addArg("handleType", WindowActionType.CLOSE_CURRENT_WINDOW.toString());
		this.setuClient.post("/action", action);
	}

	@Override
	public void closeAllChildWindows() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.HANDLE_WINDOWS);
		action.addArg("handleType", WindowActionType.CLOSE_ALL_CHILD_WINDOWS.toString());
		this.setuClient.post("/action", action);
	}

}
