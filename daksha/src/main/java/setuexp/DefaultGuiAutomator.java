package setuexp;

public class DefaultGuiAutomator extends DefaultSetuObject implements GuiAutomator {
	private SetuGuiAutoSvcClient setuClient;
	private MainWindow mainWindow;
	
	public DefaultGuiAutomator() throws Exception {
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
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.GET_MAIN_WINDOW);
		response = this.setuClient.post("/action", action);
		mainWindow = new DefaultMainWindow (this, (String) response.getData().get("elementSetuId"));
	}
	
	@Override
	public void quit() throws Exception {
		this.setuClient.post("/quit", new GuiAutomatorAction(this, GuiAutomatorActionType.QUIT));
	}
	
	private String createGenericElement(GuiAutomatorActionType actionType, With with, String value) throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, actionType);
		action.addArg("withType", with);
		action.addArg("withValue", value);
		Response response = this.setuClient.post("/action", action);
		return (String) response.getData().get("elementSetuId");		
	}
	
	@Override
	public GuiElement element(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAutomatorActionType.CREATE_ELEMENT, with, value);
		return new DefaultGuiElement(this, elemSetuId);
	}
	
	@Override
	public GuiMultiElement multiElement(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAutomatorActionType.CREATE_MULTIELEMENT, with, value);
		return new DefaultGuiMultiElement(this, elemSetuId);
	}
	
	@Override
	public DropDown dropdown(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAutomatorActionType.CREATE_DROPDOWN, with, value);
		return new DefaultDropDown(this, elemSetuId);
	}

	@Override
	public RadioGroup radioGroup(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAutomatorActionType.CREATE_RADIOGROUP, with, value);
		return new DefaultRadioGroup(this, elemSetuId);
	}
	
	@Override
	public Frame frame(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAutomatorActionType.CREATE_FRAME, with, value);
		return new DefaultFrame(this, elemSetuId);
	}
	
	@Override
	public ChildWindow childWindow(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAutomatorActionType.CREATE_CHILD_WINDOW, with, value);
		return new DefaultChildWindow(this, elemSetuId);
	}
	
	@Override
	public MainWindow mainWindow() throws Exception {
		return this.mainWindow;
	}
	
	@Override
	public ChildWindow newChildWindow() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.CREATE_NEW_CHILD_WINDOW);
		Response response = this.setuClient.post("/action", action);
		return new DefaultChildWindow (this, (String) response.getData().get("elementSetuId"));
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
	public void closeAllChildWindows() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this, GuiAutomatorActionType.CLOSE_ALL_CHILD_WINDOWS);
		this.setuClient.post("/action", action);
	}

}
