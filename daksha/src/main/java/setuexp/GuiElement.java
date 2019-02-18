package setuexp;

public interface GuiElement extends SetuManagedObject{

	void setText(String text) throws Exception;

	void click() throws Exception;

	void waitUntilClickable() throws Exception;

	GuiAutomator getAutomator() throws Exception;

	void check() throws Exception;
	
	void uncheck() throws Exception;
	
	boolean isPartial() throws Exception;

	DropDown asDropDown() throws Exception;

}