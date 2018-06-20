package daksha.tpi.leaping.generator.appium;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;

import daksha.Daksha;
import daksha.core.batteries.config.TestContext;
import daksha.core.leaping.automator.ConcreteGuiAutomator;
import daksha.core.leaping.automator.appium.AppiumNativeUiDriver;
import daksha.core.leaping.automator.appium.AppiumWebUiDriver;
import daksha.core.leaping.automator.proxy.GuiAutomatorProxy;
import daksha.core.leaping.enums.OSType;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.leaping.enums.GuiAutomationContext;
import daksha.tpi.leaping.generator.AutomatorBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumBuilder extends AutomatorBuilder{
	private MutableCapabilities defaultCaps = new MutableCapabilities();
	private MutableCapabilities otherCaps = new MutableCapabilities();
	private int waitTime = 0;
	private OSType platformType = null;
	private String appPath = null;
	
	public AppiumBuilder(TestContext testContext) throws Exception{
		super(testContext);
		switch(this.getGuiAutomationContext()){
		case MOBILE_WEB: waitTime = this.getTestContext().getConfig().value(DakshaOption.BROWSER_MOBILE_MAXWAIT).asInt(); break;
		case MOBILE_NATIVE: waitTime = this.getTestContext().getConfig().value(DakshaOption.APP_MOBILE_MAXWAIT).asInt(); break;
		default: waitTime = this.getTestContext().getConfig().value(DakshaOption.APP_MOBILE_MAXWAIT).asInt();
		}
		String platform = this.getTestContext().getConfig().value(DakshaOption.MOBILE_PLATFORM_NAME).asString();
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
		switch (this.getGuiAutomationContext()){
		case MOBILE_NATIVE:
			appium = new AppiumNativeUiDriver(this.getTestContext());
			break;
		case MOBILE_WEB:
			appium = new AppiumWebUiDriver(this.getTestContext());
			break;
		default:
			throwUnsupportedAutomationContextException(this.getGuiAutomationContext());
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
				"Leaping:Generator:Appium",
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
		switch(this.getGuiAutomationContext()){
		case MOBILE_WEB: setMobileWebCapabilities(platform, capabilities) ; break;
		case MOBILE_NATIVE: setMobileNativeCapabilities(platform, capabilities); break;
		default: throw new Exception("Unsupported automation context for Appium. Allowed: MOBILE_WEB/MOBILE_NATIVE");
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
	}

	private void setMobileWebCapabilities(OSType platform, MutableCapabilities capabilities) throws Exception {
		String browser = this.getTestContext().getConfig().value(DakshaOption.BROWSER_MOBILE_DEFAULT).asString();
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
	}
	
	/**********************************************************************************
	**					EXCEPTIONS											
	**********************************************************************************/
	
	protected void throwAppiumAutomatorException(String action, String code, String message) throws Exception {
		throw new Problem(
				"Leaping:Generator:Appium",
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
