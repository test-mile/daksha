package setuexp;

import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;

public interface AppAutomator extends SetuManagedObject{

	GuiElement element(With with, String value) throws Exception;

	GuiMultiElement multiElement(With with, String value) throws Exception;

	DropDown dropdown(With with, String value) throws Exception;

	RadioGroup radioGroup(With with, String value) throws Exception;

	Frame frame(With with, String value) throws Exception;

	ChildWindow childWindow(With with, String value) throws Exception;

	ChildWindow newChildWindow() throws Exception;

	MainWindow mainWindow() throws Exception;

	Alert alert() throws Exception;

	void goToUrl(String url) throws Exception;

	SetuGuiAutoSvcClient getSetuClient();

	void executeJavaScript(String string) throws Exception;

	void closeAllChildWindows() throws Exception;

	GuiAutomationContext getAutomationContext();

}