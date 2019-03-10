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

import org.openqa.selenium.WebElement;

public class DriverElementCommandUtils {

	public static void sendText(WebElement element, String text) throws Exception {
		element.sendKeys(text);
	}

	public static void clearText(WebElement element) throws Exception {
		element.clear();
	}

	public static void submit(WebElement element) throws Exception {
		element.submit();
	}

	public static void click(WebElement element) throws Exception {
		element.click();
	}

	public static String getTextContent(WebElement element) throws Exception {
		return element.getText();
	}

	public static String getAttribute(WebElement element, String attr) throws Exception {
		return element.getAttribute(attr);
	}

	public static String getTagName(WebElement element) throws Exception {
		return element.getTagName();
	}
	
	public static boolean isVisible(WebElement element) {
		return element.isDisplayed();
	}
	
	public static boolean isClickable(WebElement element) {
		return element.isEnabled();		
	}
	
	public static boolean isSelected(WebElement element) throws Exception{
		return element.isSelected();		
	}

}
