package daksha.tpi.uiauto.maker.appium;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;

import daksha.Daksha;
import daksha.core.batteries.config.TestContext;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.core.uiauto.automator.ConcreteGuiAutomator;
import daksha.core.uiauto.automator.appium.AppiumNativeGuiDriver;
import daksha.core.uiauto.automator.appium.AppiumWebGuiDriver;
import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.uiauto.enums.OSType;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.uiauto.enums.GuiAutomationContext;
import daksha.tpi.uiauto.maker.GuiAutomatorBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumBuilder extends GuiAutomatorBuilder{
	private MutableCapabilities defaultCaps = new MutableCapabilities();
	private MutableCapabilities otherCaps = new MutableCapabilities();
	private int waitTime = 0;
	private OSType platformType = null;
	private String appPath = null;
	
	public AppiumBuilder(TestContext testContext) throws Exception{
		super(testContext);
		waitTime = this.getTestContext().getConfig().value(DakshaOption.GUIAUTO_MAX_WAIT).asInt();
		String platform = this.getTestContext().getConfig().value(DakshaOption.TEST_MOBILE_PLATFORM).asString();
		if (!Daksha.isAllowedAppiumPlatform(platform)){
			throwUnsupportedPlatformException("constructor", platform);
		}
		platformType = OSType.valueOf(platform.toUpperCase());
		this.appPath = this.getTestContext().getConfig().value(DakshaOption.APP_MOBILE_PATH).asString();
	}
	
	private String getAppPath(){
		return this.appPath;
	}
	
	public void appPath(String path){
		otherCaps.setCapability(MobileCapabilityType.APP, path);
		otherCaps.setCapability(MobileCapabilityType.BROWSER_NAME, "");
	}
	
	
	public void platformName(String name){
		otherCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, name);
	}
	
	
	public void platformVersion(String version){
		otherCaps.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
	}
	
	
	public void deviceName(String name){
		otherCaps.setCapability(MobileCapabilityType.DEVICE_NAME, name);
	}
	
	
	public void udid(String id){
		otherCaps.setCapability(MobileCapabilityType.UDID, id);
	}
	
	
	public void capabilities(DesiredCapabilities caps){
		otherCaps.merge(caps);
	}
	
	
	public GuiAutomatorProxy build() throws Exception{
		populateDefaultCapabilities(platformType, defaultCaps);
		ConcreteGuiAutomator<AppiumDriver<MobileElement>, MobileElement> appium = null;
		if (GuiAutomationContext.isMobileNativeContext(this.getAutomationContext())) {
			appium = new AppiumNativeGuiDriver(this.getTestContext());			
		} else if (GuiAutomationContext.isMobileWebContext(this.getAutomationContext())) {
			appium = new AppiumWebGuiDriver(this.getTestContext());
		} else {
			throwUnsupportedAutomationContextException(this.getAutomationContext());
		}
		appium.init();
		appium.setWaitTime(this.waitTime);
		appium.setOSType(this.platformType);
		defaultCaps.merge(otherCaps);
		appium.setCapabilities(defaultCaps.asMap());
		appium.load();
		return new GuiAutomatorProxy(appium);
	}
	
	public GuiAutomatorProxy throwUnsupportedAutomationContextException(GuiAutomationContext context) throws Exception{
		throw new Problem(
				"UI Auto:Generator:Appium",
				"Appium Builder",
				"build",
				ErrorType.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
				String.format(
						ErrorType.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
						Daksha.getAutomationContextName(context))
			);		
	}
	
	private void setHttpProxy(Proxy proxy, String proxyString) {
		proxy.setHttpProxy(proxyString);
	}

	private void setSslProxy(Proxy proxy, String proxyString) {
		proxy.setSslProxy(proxyString);
	}
	
	private void populateDefaultCapabilities(OSType platform, MutableCapabilities capabilities) throws Exception {
		if (this.getTestContext().getConfig().value(DakshaOption.BROWSER_MOBILE_PROXY_ON).asBoolean()){
			Proxy proxy = new Proxy();
			String p = this.getTestContext().getConfig().value(DakshaOption.BROWSER_MOBILE_PROXY_HOST).asString() + ":" + this.getTestContext().getConfig().value(DakshaOption.BROWSER_MOBILE_PROXY_PORT).asString();
			setHttpProxy(proxy, p);
			setSslProxy(proxy, p);
			capabilities.setCapability("proxy", proxy);
		}
		
		capabilities.setCapability("newCommandTimeout", "60000");
		
		if (GuiAutomationContext.isMobileNativeContext(this.getAutomationContext())) {
			setMobileWebCapabilities(platform, capabilities);			
		} else if (GuiAutomationContext.isMobileWebContext(this.getAutomationContext())) {
			setMobileNativeCapabilities(platform, capabilities);
		} else {
			throwUnsupportedAutomationContextException(this.getAutomationContext());
		}
	}
	
	private void setMobileNativeCapabilities(OSType platform, MutableCapabilities capabilities) throws Exception {		
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Daksha.getAppiumPlatformString(platform));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, this.getTestContext().getConfig().value(DakshaOption.MOBILE_PLATFORM_VERSION).asString());
		capabilities.setCapability(MobileCapabilityType.APP, this.getAppPath());
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, this.getTestContext().getConfig().value(DakshaOption.MOBILE_DEVICE_NAME).asString());
		if (!this.getTestContext().getConfig().value(DakshaOption.MOBILE_DEVICE_UDID).isNull()){
			capabilities.setCapability(MobileCapabilityType.UDID, this.getTestContext().getConfig().value(DakshaOption.MOBILE_DEVICE_UDID).asString());
		}
		
		if (platform == OSType.ANDROID) {
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		} else {
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		}
	}
	
	public AppiumBuilder appPackage(String pkg) {
		otherCaps.setCapability("appPackage", pkg);
		return this;
	}
	
	public AppiumBuilder appActivity(String pkg) {
		otherCaps.setCapability("appActivity", pkg);
		return this;
	}

	private void setMobileWebCapabilities(OSType platform, MutableCapabilities capabilities) throws Exception {
		String browser = this.getTestContext().getConfig().value(DakshaOption.MOBILE_BROWSER).asString();
		if (!Daksha.isAllowedAppiumBrowser(platform, browser)){
			throwUnsupportedBrowserException("setMobileCapabilities", platform, browser);
		}
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Daksha.getAppiumPlatformString(platform));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,  this.getTestContext().getConfig().value(DakshaOption.MOBILE_PLATFORM_VERSION).asString());
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, Daksha.getAppiumBrowserString(browser));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, this.getTestContext().getConfig().value(DakshaOption.MOBILE_DEVICE_NAME).asString());
		if (!this.getTestContext().getConfig().value(DakshaOption.MOBILE_DEVICE_UDID).isNull()){
			capabilities.setCapability(MobileCapabilityType.UDID, this.getTestContext().getConfig().value(DakshaOption.MOBILE_DEVICE_UDID).asString());
		}
		
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
						Daksha.getAppiumPlatformString(platform)
						)
				);
	}

}
