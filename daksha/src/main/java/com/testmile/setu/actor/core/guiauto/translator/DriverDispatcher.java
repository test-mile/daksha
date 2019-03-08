package com.testmile.setu.actor.core.guiauto.translator;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.SetuActorSingleton;
import com.testmile.setu.actor.guiauto.core.launcher.appium.AppiumServer;
import com.testmile.trishanku.core.problem.ErrorType;
import com.testmile.trishanku.core.problem.Problem;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.OSType;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.AnyRefValue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public abstract class DriverDispatcher<T,E> {
	private T tool;
	private SetuActorConfig config;
	
	protected DriverDispatcher(T tool, SetuActorConfig config) throws Exception {
		this.tool = tool;
		this.config = config;
	}
	
	public T getTool() {
		return this.tool;
	}
	
	public abstract WebDriver asWebDriver() throws Exception;
	
	public abstract AppiumDriver<MobileElement> asAppiumDriver() throws Exception;
	
	protected SetuActorConfig getConfig() {
		return this.config;
	}
	
	public static SeleniumDispatcher seleniumDispatcher(SetuActorConfig config) throws Exception {
		SeleniumBuilder builder = new SeleniumBuilder(config);
		WebDriver driver = builder.build();
		return new SeleniumDispatcher(driver, config);
	}
	
	public static AppiumDispatcher appiumDispatcher(SetuActorConfig config) throws Exception {
		AppiumBuilder builder = new AppiumBuilder(config);
		AppiumDriver<MobileElement> driver = builder.build();
		return new AppiumDispatcher(driver, config);
	}
	
	public abstract DriverElementDispatcher<T,E> findElement(String by, String value) throws Exception;
	public abstract GuiMultiElement<T,E> findElements(String by, String value) throws Exception;
}

class SeleniumDispatcher extends DriverDispatcher<WebDriver,WebElement>{

	protected SeleniumDispatcher(WebDriver tool, SetuActorConfig config) throws Exception {
		super(tool, config);
	}
	
	public WebDriver asWebDriver() {
		return this.getTool();
	}
	
	public AppiumDriver<MobileElement> asAppiumDriver() throws Exception{
		throw new Exception("Selenium Webdriver can not be be cast as AppiumDriver");
	}

	@Override
	public GuiMultiElement<WebDriver, WebElement> findElements(String by, String value) throws Exception {
		 List<WebElement> elements = this.getTool().findElements(DriverElementDispatcher.getSeleniumByLocator(by, value));
		 return DriverElementDispatcher.seleniumMultiElement(this.getTool(), elements, this.getConfig());
	}
	
	@Override
	public DriverElementDispatcher<WebDriver, WebElement> findElement(String by, String value) throws Exception {
		 WebElement element = this.getTool().findElement(DriverElementDispatcher.getSeleniumByLocator(by, value));
		 return DriverElementDispatcher.seleniumElement(this.getTool(), element, this.getConfig());
	}
	
}

class AppiumDispatcher extends DriverDispatcher<AppiumDriver<MobileElement>,MobileElement>{

	protected AppiumDispatcher(AppiumDriver<MobileElement> tool, SetuActorConfig config) throws Exception {
		super(tool, config);
	}
	
	public WebDriver asWebDriver() {
		return this.getTool();
	}
	
	public AppiumDriver<MobileElement> asAppiumDriver() throws Exception{
		return this.getTool();
	}
	
	@Override
	public GuiMultiElement<AppiumDriver<MobileElement>, MobileElement> findElements(String by, String value) throws Exception {
		 List<MobileElement> elements = this.getTool().findElements(DriverElementDispatcher.getAppiumByLocator(by, value));
		 return DriverElementDispatcher.appiumMultiElement(this.getTool(), elements, this.getConfig());
	}
	
	@Override
	public DriverElementDispatcher<AppiumDriver<MobileElement>, MobileElement> findElement(String by, String value) throws Exception {
		MobileElement element = this.getTool().findElement(DriverElementDispatcher.getAppiumByLocator(by, value));
		 return DriverElementDispatcher.appiumElement(this.getTool(), element, this.getConfig());
	}

}

class GuiAutomatorBuilder {
	private SetuActorConfig config;
	
	public GuiAutomatorBuilder(SetuActorConfig config) {
		this.config = config;
	}
	
	protected SetuActorConfig getConfig() {
		return this.config;
	}
	
	protected GuiAutomationContext getAutomationContext() throws Exception {
		return config.value(SetuOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
	}
}

class SeleniumBuilder extends GuiAutomatorBuilder{
	private Capabilities capabilities = null;
	private Browser browser;
	private WebDriver driver = null;
	
	public SeleniumBuilder(SetuActorConfig config) throws Exception{
		super(config);
		createCapabilities();
		load();
	}
	
	private void createCapabilities() throws Exception {
		MutableCapabilities browserCaps = new MutableCapabilities();
		MutableCapabilities otherCaps = new MutableCapabilities();
//		for(String cap: caps.keySet()) {
//			otherCaps.setCapability(cap, caps.get(cap));
//		}
		
		browser = getConfig().getBrowser();
		
		switch (browser){
		case FIREFOX:
			setFirefoxCaps(browserCaps);
			break;
		case CHROME:
			setChromeCaps(browserCaps);
			break;
		case SAFARI:
			setSafariCaps(browserCaps);
			break;
		}
		
		browserCaps.merge(otherCaps);
		capabilities = browserCaps;
	}
	
	public void load() throws Exception{
		setDriverPath();
		driver = null;
		String browserBinPath = getConfig().value(SetuOption.BROWSER_BIN_PATH).asString();
		switch (this.browser){
		case FIREFOX:
			FirefoxOptions fOptions = new FirefoxOptions(capabilities);
			if (AnyRefValue.isSet(browserBinPath)) {
				fOptions.setBinary(browserBinPath);
			}
			driver = new FirefoxDriver();
			break;
		case CHROME:
			ChromeOptions coptions = new ChromeOptions();
			if (AnyRefValue.isSet(browserBinPath)) {
				coptions.setBinary(browserBinPath);
			}
			coptions.merge(capabilities);
			driver = new ChromeDriver(coptions);
			break;
		case SAFARI:
			SafariOptions sOptions = new SafariOptions();
			sOptions.merge(capabilities);
			driver = new SafariDriver(sOptions);
			break;
		}

	}

	public WebDriver build() throws Exception{
		return driver;
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
	
	private void setBrowserVersion(MutableCapabilities browserCaps) throws Exception{
		browserCaps.setCapability(CapabilityType.BROWSER_VERSION, getConfig().value(SetuOption.BROWSER_VERSION).asString());
	}
	
	private void setFirefoxCaps(MutableCapabilities browserCaps) throws Exception {
		browserCaps = getFireFoxCapabilitiesSkeleton();
		setBrowserVersion(browserCaps);
		FirefoxProfile profile = new FirefoxProfile();
		//profile..setEnableNativeEvents(true);
		browserCaps.setCapability(FirefoxDriver.PROFILE, profile);
	}

	private void setDriverPath() throws Exception {
		String driverPath = getConfig().value(SetuOption.SELENIUM_DRIVER_PATH).asString();
		System.setProperty(getConfig().value(SetuOption.SELENIUM_DRIVER_PROP).asString(), driverPath);
	}
	
	private void setChromeCaps(MutableCapabilities browserCaps) throws Exception {
		browserCaps = getChromeCapabilitiesSkeleton();
		setBrowserVersion(browserCaps);
	}

	private void setSafariCaps(MutableCapabilities browserCaps) throws Exception {
		browserCaps = getSafariCapabilitiesSkeleton();
		setBrowserVersion(browserCaps);
	}
}

class AppiumBuilder extends GuiAutomatorBuilder{
	private Capabilities capabilities = null;
	private OSType platformType = null;
	private AppiumDriver<MobileElement> driver;
	
	public AppiumBuilder(SetuActorConfig config) throws Exception{
		super(config);
		createCapabilities();
		load();
	}
		
	private void createCapabilities() throws Exception {
		MutableCapabilities appiumCaps = new MutableCapabilities();
		populateCommonCaps(appiumCaps);
		populateContextSpecificCaps(appiumCaps);
		this.capabilities = appiumCaps;
	}
	
	private void populateCommonCaps(MutableCapabilities caps) throws Exception {
		setProxy(caps);
		String platform = getConfig().value(SetuOption.TESTRUN_TARGET_PLATFORM).asString();
		if (!SetuActorSingleton.INSTANCE.isAllowedAppiumPlatform(platform)){
			throwUnsupportedPlatformException("constructor", platform);
		}
		this.platformType = OSType.valueOf(platform.toUpperCase());	
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, SetuActorSingleton.INSTANCE.getAppiumPlatformString(this.platformType));
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,  getConfig().value(SetuOption.TESTRUN_TARGET_PLATFORM_VERSION).asString());
		caps.setCapability("newCommandTimeout", "60000");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, getConfig().value(SetuOption.MOBILE_DEVICE_NAME).asString());
		if (!getConfig().value(SetuOption.MOBILE_DEVICE_UDID).isNull()){
			caps.setCapability(MobileCapabilityType.UDID, getConfig().value(SetuOption.MOBILE_DEVICE_UDID).asString());
		}
	}
	
	private OSType getPlatform() {
		return this.platformType;
	}
		
	private void setHttpProxy(Proxy proxy, String proxyString) {
		proxy.setHttpProxy(proxyString);
	}

	private void setSslProxy(Proxy proxy, String proxyString) {
		proxy.setSslProxy(proxyString);
	}
	
	private void setProxy(MutableCapabilities capabilities) throws Exception {
		if (getConfig().value(SetuOption.BROWSER_PROXY_ON).asBoolean()){
			Proxy proxy = new Proxy();
			String p = getConfig().value(SetuOption.BROWSER_PROXY_HOST).asString() + ":" + getConfig().value(SetuOption.BROWSER_PROXY_PORT).asString();
			setHttpProxy(proxy, p);
			setSslProxy(proxy, p);
			capabilities.setCapability("proxy", proxy);
		}		
	}
	
	private void populateContextSpecificCaps(MutableCapabilities capabilities) throws Exception {
		OSType platform = getPlatform();
		if (GuiAutomationContext.isMobileNativeContext(getAutomationContext())) {
			setMobileNativeCapabilities(platform, capabilities);
		} else if (GuiAutomationContext.isMobileWebContext(getAutomationContext())) {
			setMobileWebCapabilities(platform, capabilities);
		} else {
			throwUnsupportedAutomationContextException(getAutomationContext());
		}
	}
	
	public void load() throws Exception{
		AppiumDriver<MobileElement> driver = null;
		AppiumServer server = null;
		if (getConfig().value(SetuOption.APPIUM_AUTO_LAUNCH).asBoolean() == true) {
			server = SetuActorSingleton.INSTANCE.getDriverServerLauncher().startServer();
		} else {
			server = new AppiumServer(getConfig().value(SetuOption.APPIUM_HUB_URL).asString());
		}
		URL hubUrl = server.getURL();
		try{
			switch(this.getPlatform()){
			case ANDROID: driver = new AndroidDriver<MobileElement>(hubUrl, capabilities); break;
			case IOS: driver = new IOSDriver<MobileElement>(hubUrl, capabilities); break;
		}
	
		}catch (UnreachableBrowserException e){
			throwUnreachableBrowserException(this.getPlatform(), e);
		}
	}
		
	public AppiumDriver<MobileElement> build() throws Exception{
		return driver;
	}
	
	public void throwUnsupportedAutomationContextException(GuiAutomationContext context) throws Exception{
		throw new Problem(
				"UI Auto:Generator:Appium",
				"Appium Builder",
				"build",
				ErrorType.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
				String.format(
						ErrorType.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
						context.toString())
			);		
	}
	
	private void setMobileNativeCapabilities(OSType platform, MutableCapabilities capabilities) throws Exception {		
		capabilities.setCapability(MobileCapabilityType.APP, getConfig().value(SetuOption.MOBILE_APP_FILE_PATH).asString());
		capabilities.setCapability("appPackage", getConfig().value(SetuOption.MOBILE_APP_PACKAGE).asString());
		capabilities.setCapability("appActivity", getConfig().value(SetuOption.MOBILE_APP_ACTIVITY).asString());
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		
		if (platform == OSType.ANDROID) {
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		} else {
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		}
	}

	private void setMobileWebCapabilities(OSType platform, MutableCapabilities capabilities) throws Exception {
		String browser = getConfig().value(SetuOption.BROWSER_NAME).asString();
		if (!SetuActorSingleton.INSTANCE.isAllowedAppiumBrowser(platform, browser)){
			throwUnsupportedBrowserException("setMobileCapabilities", platform, browser);
		}
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, SetuActorSingleton.INSTANCE.getAppiumBrowserString(browser));
		
		if (platform == OSType.ANDROID) {
			capabilities.setCapability("unicodeKeyboard", true);
			capabilities.setCapability("resetKeyboard", true);
		}
	}
	
	/**********************************************************************************
	**					EXCEPTIONS											
	**********************************************************************************/
	
	protected void throwAppiumAutomatorException(String action, String code, String message) throws Exception {
		throw new Problem(
				"UI Auto:Generator:Appium",
				this.getClass().getSimpleName(),
				action,
				code,
				message
				);		
	}
	
	protected void throwUnsupportedPlatformException(String methodName, String platform) throws Exception {
		throwAppiumAutomatorException(
				methodName,
				ErrorType.APPIUM_UNSUPPORTED_PLATFORM,
				String.format(
						ErrorType.APPIUM_UNSUPPORTED_PLATFORM,
						platform
						)
				);
	}

	protected void throwUnsupportedBrowserException(String methodName, OSType platform, String browser) throws Exception {
		throwAppiumAutomatorException(
				methodName,
				ErrorType.APPIUM_UNSUPPORTED_BROWSER,
				String.format(
						ErrorType.APPIUM_UNSUPPORTED_BROWSER,
						browser,
						SetuActorSingleton.INSTANCE.getAppiumPlatformString(platform)
						)
				);
	}
	
	private void throwUnreachableBrowserException(OSType platformType, Throwable e) throws Exception {
		throw new Problem(
				"Automator",
				"Appium Builder",
				"Constructor",
				ErrorType.APPIUM_UNREACHABLE_BROWSER,
				"Unreachable Appium Browser for " + SetuActorSingleton.INSTANCE.getAppiumPlatformString(platformType),
				e
				);
	}

}

class ProxyHandler {
	private SetuActorConfig config;
	
	public ProxyHandler(SetuActorConfig config) throws Exception {
		this.config = config;
	}
	
	public void configureProxy(MutableCapabilities caps) throws Exception {
		if (config.value(SetuOption.BROWSER_PROXY_ON).asBoolean()){
			Proxy proxy = new Proxy();
			String p = config.value(SetuOption.BROWSER_PROXY_HOST).asString() + ":" + config.value(SetuOption.BROWSER_PROXY_PORT).asString();
			setHttpProxy(proxy, p);
			setSslProxy(proxy, p);
			caps.setCapability("proxy", proxy);
		}
	}

	public void setHttpProxy(Proxy proxy, String proxyString) {
		proxy.setHttpProxy(proxyString);
	}
	
	public void setSslProxy(Proxy proxy, String proxyString) {
		proxy.setSslProxy(proxyString);
	}

}


