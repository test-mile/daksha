package setuexp;

public class DefaultGuiElement extends DefaultSetuObject implements GuiElement {
	private GuiAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/element/action";
	private boolean partial = false;
	private int index = 0;

	public DefaultGuiElement(GuiAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}
	
	public DefaultGuiElement(GuiAutomator automator, String elemSetuId, int index, String baseUri) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
		this.partial = true;
		this.index = index;
		this.baseActionUri = baseUri;
	}

	public GuiAutomator getAutomator() {
		return this.automator;
	}
	
	@Override
	public void setText(String text) throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.SET_TEXT);
		action.addArg("text", text);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void click() throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.CLICK);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void waitUntilClickable() throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.WAIT_UNTIL_CLICKABLE);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void check() throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.CHECK);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void uncheck() throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.UNCHECK);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public boolean isPartial() throws Exception {
		return this.partial;
	}

}
