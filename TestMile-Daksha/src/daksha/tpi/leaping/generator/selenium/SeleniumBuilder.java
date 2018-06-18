package daksha.tpi.leaping.generator.selenium;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import daksha.core.batteries.config.TestContext;
import daksha.core.leaping.automator.selenium.SeleniumWebUiDriver;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.enums.GuiAutomationContext;
import daksha.tpi.leaping.generator.AutomatorBuilder;
import daksha.tpi.sysauto.utils.SystemUtils;

public class SeleniumBuilder extends AutomatorBuilder{
	private MutableCapabilities browserCaps = new MutableCapabilities();
	private MutableCapabilities otherCaps = new MutableCapabilities();
	private GuiAutomationContext context = GuiAutomationContext.PC_WEB;
	private String appTitle = null;
	private Browser browser = null;
	
	public SeleniumBuilder(TestContext testContext) throws Exception{
		super(testContext);
		this.browser = this.getTestContext().getConfig().value(DakshaOption.BROWSER_PC_DEFAULT).asEnum(Browser.class);
	}
	
	
	public void browser(Browser browser){
		this.browser = browser;
	}
	
	
	public void capabilities(DesiredCapabilities caps){
		otherCaps.merge(caps);
	}
	
	
	public GuiAutomator<WebDriver, WebElement> build() throws Exception{
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
		return selenium;
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
		String os = SystemUtils.getOSName();
		String binaryName = null;
		if (os.startsWith("Window")){
			binaryName = "geckodriver.exe";
		} else if (os.startsWith("Mac")) {
			binaryName = "geckodriver";
		}
		System.setProperty("webdriver.gecko.driver", this.getTestContext().getConfig().value(DakshaOption.DIRECTORY_TOOLS_UIDRIVERS).asString() + "/" + binaryName);

		browserCaps = getFireFoxCapabilitiesSkeleton();
		//driver = new FirefoxDriver(capabilities);
		FirefoxProfile profile = new FirefoxProfile();
		//profile..setEnableNativeEvents(true);
		browserCaps.setCapability(FirefoxDriver.PROFILE, profile);
	}

	private void setChromeCaps() throws Exception {
		this.appTitle = this.getTestContext().getConfig().value(DakshaOption.CHROME_WINDOWNAME).asString();
		String os = SystemUtils.getOSName();
		String chromeDriverBinaryName = null;
		if (os.startsWith("Window")){
			chromeDriverBinaryName = "chromedriver.exe";
		} else if (os.startsWith("Mac")) {
			chromeDriverBinaryName = "chromedriver";
		}

		System.setProperty("webdriver.chrome.driver", this.getTestContext().getConfig().value(DakshaOption.DIRECTORY_TOOLS_UIDRIVERS).asString() + "/" + chromeDriverBinaryName);
		browserCaps = getChromeCapabilitiesSkeleton();
	}

	private void setSafariCaps() throws Exception {
		this.appTitle = this.getTestContext().getConfig().value(DakshaOption.SAFARI_WINDOWNAME).asString();
		browserCaps = getSafariCapabilitiesSkeleton();
	}

}
