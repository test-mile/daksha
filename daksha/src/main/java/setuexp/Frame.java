package setuexp;

public interface Frame {
	void jump() throws Exception;
	void jumpToParent() throws Exception;
	void jumpToRoot() throws Exception;
	GuiAutomator getAutomator();
	String getSetuId();
}
