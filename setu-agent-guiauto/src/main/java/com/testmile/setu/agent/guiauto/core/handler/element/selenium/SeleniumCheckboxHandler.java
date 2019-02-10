package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.CheckboxHandler;

public class SeleniumCheckboxHandler extends AbstractWDElementHandler implements CheckboxHandler {

	public SeleniumCheckboxHandler(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.ChecboxHandler#check()
	 */
	@Override
	public void check() throws Exception {
		if (!isChecked()){
			this.getWebElement().click();
		}
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.ChecboxHandler#uncheck()
	 */
	@Override
	public void uncheck() throws Exception {
		if (isChecked()){
			this.getWebElement().click();
		}
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.ChecboxHandler#toggleCheckbox()
	 */
	@Override
	public void toggleCheckbox() throws Exception {
		this.getWebElement().click();
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.ChecboxHandler#isChecked()
	 */
	@Override
	public boolean isChecked() throws Exception {
		return this.getWebElement().isSelected();
	}
}
