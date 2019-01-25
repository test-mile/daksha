package com.testmile.daksha.core.guiauto.notifier.selenium;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.testmile.daksha.Daksha;

public class SeleniumListener implements WebDriverEventListener{
	private Logger logger = Daksha.getLogger();

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		logger.debug("Would now accert the alert");
	}
	
	@Override
	public void afterAlertAccept(WebDriver arg0) {
		logger.debug("Alert accepted");
	}


	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		logger.debug("Would now dismiss alert");
	}
	
	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		logger.debug("Alert dismissed");
	}
	
	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		logger.debug(String.format("Value of %s would be changed to %s", arg0, Arrays.toString(arg2)));
	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		logger.debug(String.format("Value of %s changed to %s", arg0, Arrays.toString(arg2)));
	}
	
	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		logger.debug(String.format("Would now click %s", arg0));
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		logger.debug(String.format("Clicked %s", arg0));
	}
	
	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		logger.debug(String.format("Would find element by: %s", arg0));
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		logger.debug(String.format("Found element by: %s", arg0));
	}
	
	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		logger.debug("Would now take screenshot");
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		logger.debug("Took screenshot");
	}
	
	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		logger.debug("Would navigate back");
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		logger.debug("Navigated back");
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		logger.debug("Would navigate forward");
	}
	
	@Override
	public void afterNavigateForward(WebDriver arg0) {
		logger.debug("Navigated forward");
	}
	
	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		logger.debug("Would  refresh");
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		logger.debug("Refreshed");
	}
	
	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		logger.debug("Would navigate to: " + arg0);
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		logger.debug("Navigated to: " + arg0);
	}
	
	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		logger.debug("Would run script: " + arg0);
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		logger.debug("Ran script: " + arg0);
	}

	
	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		logger.debug("Would now swtich to window: " + arg0);
	}
	
	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		logger.debug("Swtiched to window: " + arg0);
	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		logger.debug("Exception occured");
		logger.debug(arg0.toString());		
		arg0.printStackTrace();
	}

	@Override
	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

}
