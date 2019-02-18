package setuexp;

public interface GuiAutomator extends SetuManagedObject{

	void launch() throws Exception;

	GuiElement element(With with, String value) throws Exception;
	
	GuiMultiElement multiElement(With with, String value) throws Exception;
	
	DropDown dropdown(With with, String value) throws Exception;

	RadioGroup radioGroup(With with, String value) throws Exception;

	void goToUrl(String url) throws Exception;

	void quit() throws Exception;

	SetuGuiAutoSvcClient getSetuClient();

	void executeJavaScript(String string) throws Exception;

	void confirmAlert() throws Exception;

	void dismissAlert() throws Exception;

	String getTextFromAlert() throws Exception;

	void sendTextToAlert(String string) throws Exception;

	void maximizeWindow() throws Exception;

	String getWindowTitle() throws Exception;

	void switchToNewWindow() throws Exception;

	void closeCurrentWindow() throws Exception;

	void closeAllChildWindows() throws Exception;

}