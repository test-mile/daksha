package setuexp;

public interface BasicWindow {
	String getTitle() throws Exception;
	void jump() throws Exception;
	GuiAutomator getAutomator();
	String getSetuId();
}
