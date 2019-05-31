package com.testmile.daksha.tpi.guiauto.maker.selenium;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.testmile.daksha.Daksha;
import com.testmile.daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import com.testmile.daksha.core.guiauto.automator.selenium.SeleniumWebGuiDriver;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.enums.Browser;
import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.guiauto.maker.GuiAutomatorBuilder;

public class SeleniumBuilder extends GuiAutomatorBuilder{
	private MutableCapabilities browserCaps = new MutableCapabilities();
	private MutableCapabilities otherCaps = new MutableCapabilities();
	private GuiAutomationContext context = GuiAutomationContext.WEB;
	private String appTitle = null;
	
	public SeleniumBuilder(TestContext testContext) throws Exception{
		super(testContext);
	}
	
	public void capabilities(DesiredCapabilities caps){
		otherCaps.merge(caps);
	}

	public GuiAutomatorProxy build() throws Exception{
		SeleniumWebGuiDriver selenium = new SeleniumWebGuiDriver(this.getTestContext());
		Browser browser = this.getTestContext().getConfig().value(DakshaOption.BROWSER_NAME).asEnum(Browser.class);
		selenium.setBrowser(browser);
		selenium.init();
		setDriverPath();
		
		switch (browser){
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
		browserCaps = getFireFoxCapabilitiesSkeleton();
		browserCaps.setCapability(CapabilityType.BROWSER_VERSION, this.getTestContext().getBrowerVersion());
		FirefoxProfile profile = new FirefoxProfile();
		//profile..setEnableNativeEvents(true);
		browserCaps.setCapability(FirefoxDriver.PROFILE, profile);
	}

	private void setDriverPath() throws Exception {
		Browser browser = this.getTestContext().getBrowserType();
		if (Daksha.isDriverPathNeeded(browser)){
			String driverPath = this.getTestContext().getSeleniumDriverPath(browser);
			System.setProperty(Daksha.getSeleniumDriverPathSystemProperty(browser), driverPath);
		}
	}
	
	private void setChromeCaps() throws Exception {
		this.appTitle = this.getTestContext().getConfig().value(DakshaOption.CHROME_WINDOWNAME).asString();
		browserCaps = getChromeCapabilitiesSkeleton();
//		browserCaps.setCapability(CapabilityType.BROWSER_VERSION, this.getTestContext().getBrowerVersion());
	}

	private void setSafariCaps() throws Exception {
		this.appTitle = this.getTestContext().getConfig().value(DakshaOption.SAFARI_WINDOWNAME).asString();
		browserCaps = getSafariCapabilitiesSkeleton();
		browserCaps.setCapability(CapabilityType.BROWSER_VERSION, this.getTestContext().getBrowerVersion());
	}
}
