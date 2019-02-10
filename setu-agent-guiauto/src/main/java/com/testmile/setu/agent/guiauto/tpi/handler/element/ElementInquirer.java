package com.testmile.setu.agent.guiauto.tpi.handler.element;

public interface ElementInquirer {

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.InquirableElement#getText()
	 */
	String getText() throws Exception;

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.InquirableElement#getEnteredText()
	 */
	String getEnteredText() throws Exception;

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.InquirableElement#getValue()
	 */
	String getValue() throws Exception;

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.InquirableElement#getAttribute(java.lang.String)
	 */
	String getAttribute(String attr) throws Exception;

}