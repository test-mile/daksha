/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.testmile.setu.actor.guiauto.commander.driver;

import java.awt.Toolkit;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DriverCommandUtils {

	public static void goTo(WebDriver driver, String url) throws Exception {
		System.out.println("here");
		driver.get(url);
	}

	public static void refresh(WebDriver driver) throws Exception {
		driver.navigate().refresh();
	}
	

	public static void back(WebDriver driver) throws Exception {
		driver.navigate().back();
	}
	
	public static void forward(WebDriver driver) throws Exception {
		driver.navigate().forward();
	}


	public static void quit(WebDriver driver) throws Exception{
		driver.quit();
	}
	
	public static String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static boolean isWebAlertPresent(WebDriver driver) throws Exception {
		try {
			WebDriverWait waiter = new WebDriverWait(driver, 1);
			waiter.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public static void confirmWebAlert(WebDriver driver) throws Exception {
		driver.switchTo().alert().accept();
	}


	public static void dismissWebAlert(WebDriver driver) throws Exception {
		driver.switchTo().alert().dismiss();
	}


	public static void sendTextToWebAlert(WebDriver driver, String text) throws Exception {
		driver.switchTo().alert().sendKeys(text);
	}


	public static String getTextFromWebAlert(WebDriver driver) throws Exception {
		return driver.switchTo().alert().getText();
	}
	
	public static void focusOnFrame(WebDriver driver, WebElement element) throws Exception {
		driver.switchTo().frame(element);
	}
	
	public static void focusOnDomRoot(WebDriver driver) throws Exception {
		driver.switchTo().defaultContent();
	}
	
	public static void focusOnParentFrame(WebDriver driver) throws Exception {
		driver.switchTo().parentFrame();
	}
	
	public static void executeJavaScript(WebDriver driver, String script, Object... args) throws Exception {
		((JavascriptExecutor) driver).executeScript(script, args);
	}
	
	public static String takeScreenshot(WebDriver driver) throws Exception {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
	
//	private static void scroll(WebDriver driver, SetuDriverConfig config, Direction direction, int count) throws Exception {		 
//		int spc = 0;
//		if (direction == Direction.DOWN) {
//			spc = getScrollPixelCount(config);
//		} else if (direction == Direction.UP) {
//			spc = -getScrollPixelCount(config);
//		} else {
//			throw new Exception("Scroll left/right is not yet supported.");
//		}
//		for (int i = 0; i < count; i++) {
//			executeJavaScript(driver, String.format("window.scrollBy(0, %d)", spc));
//		}
//	}
//	
//	private static int getScrollPixelCount(SetuDriverConfig config) throws Exception {
//		return config.value(SetuOption.GUIAUTO_SCROLL_PIXELS).asInt();
//	}
//
//	public static void scrollDown(WebDriver driver, SetuDriverConfig config, int count) throws Exception {
//		scroll(driver, config, Direction.DOWN, count);
//	}
//
//	public static void scrollUp(WebDriver driver, SetuDriverConfig config, int count) throws Exception {
//		scroll(driver, config, Direction.UP, count);
//	}
	
	public static void setWindowSize(WebDriver driver, int width, int height) throws Exception{
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public static void maximizeWindow(WebDriver driver){
		try{
			driver.manage().window().maximize();
		} catch (WebDriverException e){
			java.awt.Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			// This dimension is webdriver Dimension
			driver.manage().window().setSize(new Dimension((int)d.getWidth(), (int) d.getHeight()));
		}
	}

	public static String getCurrentWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public static void focusOnWindow(WebDriver driver, String windowHandle){
		driver.switchTo().window(windowHandle); 		
	}

	public static void closeCurrentWindow(WebDriver driver){
		driver.close();
	}

	public static String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static int[] getCurrentWindowSize(WebDriver driver) throws Exception {
		Dimension dim = driver.manage().window().getSize();
		return new int[] {dim.getWidth(), dim.getHeight()};
	}

	public static Set<String> getAllWindowHandles(WebDriver driver) throws Exception {
		return driver.getWindowHandles();
	}
	

	
	/*
	 * Appium Specific
	 */	
	public static void focusOnMobileViewContext(AppiumDriver<MobileElement> driver,String view) throws Exception {
		driver.context(view);
	}

	public static String getCurrentMobileViewContext(AppiumDriver<MobileElement> driver) throws Exception {
		return driver.getContext();
	}
	
	public static Set<String> getAllMobileViewContexts(AppiumDriver<MobileElement> driver) throws Exception {
		return driver.getContextHandles();
	}

}
