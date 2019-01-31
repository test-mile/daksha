package com.testmile.daksha_examples;

import com.testmile.daksha.Daksha;
import com.testmile.daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.enums.Browser;
import com.testmile.daksha.tpi.guiauto.maker.GuiAutomatorFactory;
import com.testmile.daksha.tpi.guiauto.maker.selenium.SeleniumBuilder;

public class Basic1WithCentralTestContext{
	
	public static void main (String args[]) throws Exception {
		// Initialize and set central configuration
		TestContext centralContext = Daksha.init();
		centralContext.setGuiAutoMaxWaitTime(30);
		centralContext.setBrowserType(Browser.CHROME);
		centralContext.freeze();
		// Central Context can not be modified after this point
		
		// Create Selenium automator with central context options
		SeleniumBuilder builder = GuiAutomatorFactory.getSeleniumBuilder(centralContext);
		GuiAutomatorProxy automator = builder.build();

		automator.goTo("https://www.google.com");
		System.out.println(automator.getPageTitle());
		automator.close();
	}

}
