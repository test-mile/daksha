package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.BasicActionsHandler;

public class SeleniumBasicActionsHandler extends AbstractWDElementHandler implements BasicActionsHandler {

	public SeleniumBasicActionsHandler(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.BasicActionsHandler#focus()
	 */
	@Override
	public void focus() throws Exception {
		this.getWebElement().sendKeys("");
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.BasicActionsHandler#enterText(java.lang.String)
	 */
	@Override
	public void enterText(String text) throws Exception {
		this.getWebElement().click();
		this.getWebElement().sendKeys(text);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.BasicActionsHandler#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) throws Exception {
		clearText();
		this.getWebElement().sendKeys(text);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.BasicActionsHandler#clearText()
	 */
	@Override
	public void clearText() throws Exception {
		this.getWebElement().click();
		this.getWebElement().clear();
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.BasicActionsHandler#submit()
	 */
	@Override
	public void submit() throws Exception {
		this.getWebElement().submit();
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.BasicActionsHandler#click()
	 */
	@Override
	public void click() throws Exception {
		this.getWebElement().click();
	}

}
