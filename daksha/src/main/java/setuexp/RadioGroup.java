package setuexp;

public interface RadioGroup {

	boolean hasValueSelected(String value) throws Exception;
	boolean hasIndexSelected(int index) throws Exception;
	String getFirstSelectedOptionValue() throws Exception;
	void selectByValue(String value) throws Exception;
	void selectByIndex(int index) throws Exception;
	AppAutomator getAutomator();
	String getSetuId();

}
