package setuexp;

public interface BasicWindow {
	String getTitle() throws Exception;
	void jump() throws Exception;
	AppAutomator getAutomator();
	String getSetuId();
}
