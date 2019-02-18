package setuexp;

public interface DropDown {

	boolean hasVisibleTextSelected(String text) throws Exception;
	boolean hasValueSelected(String value) throws Exception;
	boolean hasIndexSelected(int index) throws Exception;
	String getFirstSelectedOptionText() throws Exception;
	void selectByValue(String value) throws Exception;
	void selectByVisibleText(String text) throws Exception;
	void selectByIndex(int index) throws Exception;
	GuiAutomator getAutomator();
	String getSetuId();

}
