package daksha.tpi.uiauto.maker.selenium;

import java.io.File;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import daksha.core.batteries.config.TestContext;
import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.uiauto.automator.selenium.SeleniumWebGuiDriver;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.uiauto.enums.GuiAutomationContext;
import daksha.tpi.uiauto.maker.GuiAutomatorBuilder;

public class SeleniumBuilder extends GuiAutomatorBuilder{
	private MutableCapabilities browserCaps = new MutableCapabilities();
	private MutableCapabilities otherCaps = new MutableCapabilities();
	private GuiAutomationContext context = GuiAutomationContext.PC_WEB;
	private String appTitle = null;
	private Browser browser = null;
	
	public SeleniumBuilder(TestContext testContext) throws Exception{
		super(testContext);
		this.browser = this.getTestContext().getConfig().value(DakshaOption.GUIAUTO_DEFAULT_BROWSER).asEnum(Browser.class);
	}


	public void browser(Browser browser){
		this.browser = browser;
	}
	
	
	public void capabilities(DesiredCapabilities caps){
		otherCaps.merge(caps);
	}
	
	
	public GuiAutomatorProxy build() throws Exception{
		SeleniumWebGuiDriver selenium = new SeleniumWebGuiDriver(this.getTestContext());
		selenium.setBrowser(browser);
		selenium.init();
		switch (this.browser){
		case FIREFOX:
			setFirefoxCaps();
			break;
		case CHROME:
			
			setChromeCaps();
			break;
		case SAFARI:
			setSafariCaps();
			break;	
		}
		
		browserCaps.merge(otherCaps);
		selenium.setCapabilities(browserCaps.asMap());
		selenium.load();
		return new GuiAutomatorProxy(selenium);
	}


	private MutableCapabilities getFireFoxCapabilitiesSkeleton() { 
		return DesiredCapabilities.firefox();
	}

	private MutableCapabilities getChromeCapabilitiesSkeleton() {
		return DesiredCapabilities.chrome();
	}

	private MutableCapabilities getSafariCapabilitiesSkeleton() {
		return DesiredCapabilities.safari();
	}
	
	private void setFirefoxCaps() throws Exception {
		this.appTitle = this.getTestContext().getConfig().value(DakshaOption.FIREFOX_WINDOWNAME).asString();
		String os = this.getTestContext().getConfig().value(DakshaOption.OSTYPE).asString().toLowerCase();
		String binaryName = null;
		if (os.equals("windows")){
			binaryName = "geckodriver.exe";
		} else {
			binaryName = "geckodriver";
		}

		String driversDir = this.getTestContext().getConfig().value(DakshaOption.GUIAUTO_DRIVERS_DIR).asString();
		System.setProperty("webdriver.gecko.driver",  driversDir + File.separator + os + File.separator + binaryName);

		browserCaps = getFireFoxCapabilitiesSkeleton();
		//driver = new FirefoxDriver(capabilities);
		FirefoxProfile profile = new FirefoxProfile();
		//profile..setEnableNativeEvents(true);
		browserCaps.setCapability(FirefoxDriver.PROFILE, profile);
	}

	private void setChromeCaps() throws Exception {
		this.appTitle = this.getTestContext().getConfig().value(DakshaOption.CHROME_WINDOWNAME).asString();
		String os = this.getTestContext().getConfig().value(DakshaOption.OSTYPE).asString().toLowerCase();
		String binaryName = null;
		if (os.equals("windows")){
			binaryName = "chromedriver.exe";
		} else {
			binaryName = "chromedriver";
		}

		String driversDir = this.getTestContext().getConfig().value(DakshaOption.GUIAUTO_DRIVERS_DIR).asString();
		System.setProperty("webdriver.chrome.driver",  driversDir + File.separator + os + File.separator + binaryName);
		browserCaps = getChromeCapabilitiesSkeleton();
	}

	private void setSafariCaps() throws Exception {
		this.appTitle = this.getTestContext().getConfig().value(DakshaOption.SAFARI_WINDOWNAME).asString();
		browserCaps = getSafariCapabilitiesSkeleton();
	}

}
