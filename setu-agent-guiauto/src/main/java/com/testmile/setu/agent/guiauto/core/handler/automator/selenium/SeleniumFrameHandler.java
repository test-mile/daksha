package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import org.openqa.selenium.WebDriver;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.FrameHandler;

public class SeleniumFrameHandler extends AbstractWDHandler implements FrameHandler{
	
	public SeleniumFrameHandler(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}
	
	protected void validateFrameSupport() throws Exception{
		return;
	}
	
	@Override
	public void switchToFrameByIndex(int index) throws Exception {
		validateFrameSupport();
		this.getWebDriver().switchTo().frame(index);
	}

	@Override
	public void switchToFrameByName(String name) throws Exception {
		validateFrameSupport();
		this.getWebDriver().switchTo().frame(name);
	}
	
	@Override
	public void switchToDefaultFrame() throws Exception {
		validateFrameSupport();
		this.getWebDriver().switchTo().defaultContent();
	}
	
	@Override
	public void switchToParentFrame() throws Exception {
		validateFrameSupport();
		this.getWebDriver().switchTo().parentFrame();
	}

	@Override
	public void switchToFrameOfElement(GuiElement element) throws Exception {
		validateFrameSupport();
		element.switchToFrame();
	}

}
