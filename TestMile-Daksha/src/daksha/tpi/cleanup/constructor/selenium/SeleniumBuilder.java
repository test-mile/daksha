package daksha.tpi.cleanup.constructor.selenium;

import java.io.File;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import daksha.core.batteries.config.TestContext;
import daksha.core.cleanup.automator.proxy.UiAutomatorProxy;
import daksha.core.cleanup.automator.selenium.SeleniumWebUiDriver;
import daksha.tpi.cleanup.constructor.UiAutomatorBuilder;
import daksha.tpi.cleanup.enums.UiAutomationContext;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;

public class SeleniumBuilder extends UiAutomatorBuilder{
	private MutableCapabilities browserCaps = new MutableCapabilities();
	private MutableCapabilities otherCaps = new MutableCapabilities();
	private UiAutomationContext context = UiAutomationContext.PC_WEB;
	private String appTitle = null;
	private Browser browser = null;
	
	public SeleniumBuilder(TestContext testContext) throws Exception{
		super(testContext);
		this.browser = this.getTestContext().getConfig().value(DakshaOption.UIAUTO_DEFAULT_BROWSER).asEnum(Browser.class);
	}


	public void browser(Browser browser){
		this.browser = browser;
	}
	
	
	public void capabilities(DesiredCapabilities caps){
		otherCaps.merge(caps);
	}
	
	
	public UiAutomatorProxy build() throws Exception{
		SeleniumWebUiDriver selenium = new SeleniumWebUiDriver(this.getTestContext());
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
		return new UiAutomatorProxy(selenium);
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

		String driversDir = this.getTestContext().getConfig().value(DakshaOption.UIAUTO_DRIVERS_DIR).asString();
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

		String driversDir = this.getTestContext().getConfig().value(DakshaOption.UIAUTO_DRIVERS_DIR).asString();
		System.setProperty("webdriver.chrome.driver",  driversDir + File.separator + os + File.separator + binaryName);
		browserCaps = getChromeCapabilitiesSkeleton();
	}

	private void setSafariCaps() throws Exception {
		this.appTitle = this.getTestContext().getConfig().value(DakshaOption.SAFARI_WINDOWNAME).asString();
		browserCaps = getSafariCapabilitiesSkeleton();
	}

}
