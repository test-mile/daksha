package setuexp;

public class DefaultAlert extends DefaultSetuObject implements Alert {
	private GuiAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/alert/action";

	public DefaultAlert(DefaultGuiAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	@Override
	public void confirm() throws Exception {
		AlertAction action = new AlertAction(this, AlertActionType.CONFIRM);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void dismiss() throws Exception {
		AlertAction action = new AlertAction(this, AlertActionType.DISMISS);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public String getText() throws Exception {
		AlertAction action = new AlertAction(this, AlertActionType.GET_TEXT);
		Response response = this.setuClient.post(baseActionUri, action);
		return (String) response.getData().get("text");
	}

	@Override
	public void sendText(String text) throws Exception {
		AlertAction action = new AlertAction(this, AlertActionType.SEND_TEXT);
		action.addArg("text", text);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public GuiAutomator getAutomator() {
		return this.automator;
	}

}
