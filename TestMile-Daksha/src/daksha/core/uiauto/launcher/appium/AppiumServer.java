package daksha.core.uiauto.launcher.appium;

public class AppiumServer {
	private int port;
	
	public AppiumServer(int port) {
		this.port = port;
	}

	public int getPort() {
		return this.port;
	}
	
	public String getURL() {
		return String.format("http://127.0.0.1:%d/wd/hub", this.port);
	}

}
