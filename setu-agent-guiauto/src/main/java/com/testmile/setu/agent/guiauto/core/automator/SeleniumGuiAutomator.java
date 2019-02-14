package com.testmile.setu.agent.guiauto.core.automator;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import com.testmile.daksha.tpi.enums.Browser;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumAlertHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumBrowserHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumElementFinder;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumFrameHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumJSExecutor;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumScreenshoter;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumScroller;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumWindowHandler;

public class SeleniumGuiAutomator extends BaseGuiAutomator {
	private WebDriver driver = null;
	protected Capabilities capabilities = null;
	private Browser browser = null;
	
	public SeleniumGuiAutomator(WebDriver driver, SetuAgentConfig config) throws Exception{
		super(config);
		this.driver = driver;
		initComponents();
	}
	
	private void initComponents() throws Exception{
		if(this.browser != Browser.SAFARI){
			getWebDriver().manage().timeouts().pageLoadTimeout(this.getConfig().getMaxWaitTime(), TimeUnit.SECONDS);
		}
		
		this.setAlertHandler(new SeleniumAlertHandler(this.driver, getConfig()));
		this.setBrowserHandler(new SeleniumBrowserHandler(this.driver, getConfig()));
		this.setFrameHandler(new SeleniumFrameHandler(this.driver, getConfig()));
		this.setJsExecutor(new SeleniumJSExecutor(this.driver, getConfig()));
		this.setScreenshoter(new SeleniumScreenshoter(this.driver, getConfig()));
		this.setScroller(new SeleniumScroller(this.driver, getConfig()));
		this.setWindowHandler(new SeleniumWindowHandler(driver, getConfig()));
		this.setElementFinder(new SeleniumElementFinder(driver, getConfig()));
	}

	private WebDriver getWebDriver() {
		return driver;
	}
	
	public void quit() throws Exception{
		this.getBrowserHandler().close();
	}

}
