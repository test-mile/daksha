package setuexp;

public class DefaultChildWindow extends AbstractBasicWindow implements ChildWindow {

	public DefaultChildWindow(DefaultGuiAutomator automator, String elemSetuId) {
		super(automator, elemSetuId);
	}

	@Override
	public void close() throws Exception {
		WindowAction action = new WindowAction(this, WindowActionType.CLOSE);
		this.getSetuClient().post(this.getBaseActionUri(), action);
	}
}
