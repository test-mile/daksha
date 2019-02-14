package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.WindowHandler;

public class SeleniumWindowHandler extends AbstractWDHandler implements WindowHandler {
	
	public SeleniumWindowHandler(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}

	@Override
	public void setWindowSize(int width, int height) throws Exception{
		this.getWebDriver().manage().window().setSize(new Dimension(width, height));
	}

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

	@Override
	public String getCurrentWindowHandle() {
		return getWebDriver().getWindowHandle();
	}

	@Override
	public void switchToWindow(String windowHandle){
		getWebDriver().switchTo().window(windowHandle); 		
	}
	
	@Override
	public void closeCurrentWindow(){
		getWebDriver().close();
	}

	@Override
	public String getTitle() {
		return getWebDriver().getTitle();
	}

	@Override
	public Map<String, Integer> getCurrentWindowSize() throws Exception {
		Dimension dim = this.getWebDriver().manage().window().getSize();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("width",dim.getWidth());
		map.put("height", dim.getHeight());
		return map;
	}

	@Override
	public Set<String> getAllWindowHandles() throws Exception {
		return getWebDriver().getWindowHandles();
	}
}
