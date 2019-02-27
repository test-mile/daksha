package setuexp;

public class AbstractBasicWindow extends DefaultSetuObject implements BasicWindow {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/window/action";

	public AbstractBasicWindow(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}
	
	protected SetuGuiAutoSvcClient getSetuClient() {
		return this.setuClient;
	}
	
	protected String getBaseActionUri() {
		return this.baseActionUri;
	}
	
	@Override
	public String getTitle() throws Exception {
		WindowAction action = new WindowAction(this, WindowActionType.GET_TITLE);
		Response response = this.getSetuClient().post(this.getBaseActionUri(), action);
		return (String) response.getData().get("title");
	}
	
	@Override
	public void jump() throws Exception {
		WindowAction action = new WindowAction(this, WindowActionType.JUMP);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public AppAutomator getAutomator() {
		return this.automator;
	}
}
