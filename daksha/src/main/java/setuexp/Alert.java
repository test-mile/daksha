package setuexp;

public interface Alert {
	
	void confirm() throws Exception;
	void dismiss() throws Exception;
	String getText() throws Exception;
	void sendText(String text) throws Exception;
	GuiAutomator getAutomator();
	String getSetuId();
	
}
