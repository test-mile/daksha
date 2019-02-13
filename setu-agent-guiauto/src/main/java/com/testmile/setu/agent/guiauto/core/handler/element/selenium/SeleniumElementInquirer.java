package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementInquirer;

public class SeleniumElementInquirer extends AbstractWDElementHandler implements ElementInquirer {

	public SeleniumElementInquirer(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.InquirableElement#getText()
	 */
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.ElementInquirer#getText()
	 */
	@Override
	public String getText() throws Exception {
		return this.getWebElement().getText();
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.InquirableElement#getEnteredText()
	 */
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.ElementInquirer#getEnteredText()
	 */
	@Override
	public String getEnteredText() throws Exception {
		return this.getValue();
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.InquirableElement#getValue()
	 */
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.ElementInquirer#getValue()
	 */
	@Override
	public String getValue() throws Exception {
		return this.getAttribute("value");
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.InquirableElement#getAttribute(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.ElementInquirer#getAttribute(java.lang.String)
	 */
	@Override
	public String getAttribute(String attr) throws Exception {
		return this.getWebElement().getAttribute("attr");
	}
	
	@Override
	public String getTagName() throws Exception {
		return this.getWebElement().getTagName();
	}

}
