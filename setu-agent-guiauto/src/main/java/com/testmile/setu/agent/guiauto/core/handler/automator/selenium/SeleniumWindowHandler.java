package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import java.awt.Toolkit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.WindowHandler;

public class SeleniumWindowHandler extends AbstractWDHandler implements WindowHandler {
	
	public SeleniumWindowHandler(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.WindowHandler#resizeWindowAsConfigured()
	 */
	@Override
	public void resizeWindowAsConfigured() throws Exception {
		// Resize window
		Value browserWidth = getConfig().value(DakshaOption.BROWSER_DIM_WIDTH);
		Value browserHeight = getConfig().value(DakshaOption.BROWSER_DIM_HEIGHT);
		boolean maxWindow = getConfig().value(DakshaOption.BROWSER_MAXIMIZE).asBoolean();
		
		if (browserWidth.isNotSet() && browserHeight.isNotSet()) {
			if (maxWindow) {
				maximizeWindow();
			}
		} else {
			int width;
			int height;
			
			Dimension currentSize = this.getWebDriver().manage().window().getSize();
			
			if (!browserWidth.isNotSet()) {
				width = browserWidth.asInt();
			} else {
				width = currentSize.getWidth();
			}
			
			if (!browserHeight.isNotSet()) {
				height = browserHeight.asInt();
			} else {
				height = currentSize.getHeight();
			}
			
			this.getWebDriver().manage().window().setSize(new Dimension(width, height));
		}
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.WindowHandler#maximizeWindow()
	 */
	@Override
	public void maximizeWindow(){
		// Check for some property here. To override this default.
		try{
			getWebDriver().manage().window().maximize();
		} catch (WebDriverException e){
			java.awt.Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			// This dimension is webdriver Dimension
			getWebDriver().manage().window().setSize(new Dimension((int)d.getWidth(), (int) d.getHeight()));
		}
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.WindowHandler#getCurrentWindow()
	 */
	@Override
	public String getCurrentWindow() {
		return getWebDriver().getWindowHandle();
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.WindowHandler#switchToWindow(java.lang.String)
	 */
	@Override
	public void switchToWindow(String windowHandle){
		getWebDriver().switchTo().window(windowHandle); 		
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.WindowHandler#switchToNewWindow()
	 */
	@Override
	public void switchToNewWindow() {
		WebDriver driver = getWebDriver();
		String parentHandle = getCurrentWindow();
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentHandle)) {
				switchToWindow(winHandle); // switch focus of WebDriver to the next found window handle
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.WindowHandler#closeCurrentWindow()
	 */
	@Override
	public void closeCurrentWindow(){
		getWebDriver().close();
	}

	@Override
	public String getTitle() {
		return getWebDriver().getTitle();
	}
}
