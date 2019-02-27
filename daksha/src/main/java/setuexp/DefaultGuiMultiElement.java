package setuexp;

public class DefaultGuiMultiElement extends DefaultSetuObject implements GuiMultiElement {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private final static String baseActionUri = "/multielement/action";

	public DefaultGuiMultiElement(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	public AppAutomator getAutomator() {
		return this.automator;
	}

	@Override
	public GuiElement getInstanceAtIndex(int index) {
		return new DefaultGuiElement(this.automator, this.getSetuId(), index, baseActionUri);
	}

}
