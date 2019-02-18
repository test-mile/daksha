package setuexp;

public class DefaultGuiMultiElement extends DefaultSetuObject implements GuiMultiElement {
	private GuiAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private final static String baseActionUri = "/multielement/action";

	public DefaultGuiMultiElement(DefaultGuiAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	public GuiAutomator getAutomator() {
		return this.automator;
	}

	@Override
	public GuiElement getInstanceAtIndex(int index) {
		return new DefaultGuiElement(this.automator, this.getSetuId(), index, baseActionUri);
	}

	@Override
	public RadioGroup asRadioGroup() throws Exception {
		return this.getAutomator().convertToRadioGroup(this);
	}

}
