package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.RadioButtonHandler;

public class SeleniumRadioButtonHandler extends AbstractWDElementHandler implements RadioButtonHandler {

	public SeleniumRadioButtonHandler(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.RadioButtonHandler#selectRadioButton()
	 */
	@Override
	public void selectRadioButton() throws Exception {
		if(!isRadioButtonSelected()) {
			this.getWebElement().click();
		}
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.RadioButtonHandler#isRadioButtonSelected()
	 */
	@Override
	public boolean isRadioButtonSelected() throws Exception {
		return this.getWebElement().isSelected();
	}

}
