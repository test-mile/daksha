package daksha.tpi.leaping.interfaces;

import org.openqa.selenium.remote.DesiredCapabilities;

import daksha.tpi.enums.Browser;

public interface SeleniumBuilder {

	void capabilities(DesiredCapabilities caps);

	GuiAutomator build() throws Exception;

	void browser(Browser browser);

}