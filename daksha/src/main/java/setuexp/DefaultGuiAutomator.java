package setuexp;

public class DefaultGuiAutomator extends AbstractAppAutomator implements GuiAutomator {
	
	public DefaultGuiAutomator() throws Exception {
		super("/automator/");
	}
	
	@Override
	public void launch() throws Exception {
		Response response = this.setuClient.post(baseUri + "launch", new GuiAutomatorAction(GuiAutomatorActionType.LAUNCH));
		System.out.println((String) response.getData().get("automatorSetuId"));
		this.setSetuId((String) response.getData().get("automatorSetuId"));
		GuiAppAutomatorAction action = new GuiAppAutomatorAction(this, GuiAppAutomatorActionType.GET_MAIN_WINDOW);
		mainWindow = new DefaultMainWindow (this, this.takeElementFindingAction(action));
	}
	
	@Override
	public void quit() throws Exception {
		this.setuClient.post(baseUri + "quit", new GuiAutomatorAction(this, GuiAutomatorActionType.QUIT));
	}
}
