package setuexp;

public class DefaultFrame extends DefaultSetuObject implements Frame {
	private GuiAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/frame/action";

	public DefaultFrame(DefaultGuiAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	@Override
	public void jump() throws Exception {
		FrameAction action = new FrameAction(this, FrameActionType.JUMP);
		this.setuClient.post(baseActionUri, action);
	}
	
	@Override
	public void jumpToParent() throws Exception {
		FrameAction action = new FrameAction(this, FrameActionType.JUMP_TO_PARENT);
		this.setuClient.post(baseActionUri, action);
	}
	
	@Override
	public void jumpToRoot() throws Exception {
		FrameAction action = new FrameAction(this, FrameActionType.JUMP_TO_ROOT);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public GuiAutomator getAutomator() {
		return this.automator;
	}

}
