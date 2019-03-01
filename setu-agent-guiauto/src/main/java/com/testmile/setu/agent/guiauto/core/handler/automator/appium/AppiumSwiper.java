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

package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumJSExecutor;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Swiper;
import com.testmile.trishanku.tpi.enums.Direction;
import com.testmile.trishanku.tpi.enums.OSType;
import com.testmile.trishanku.tpi.enums.SetuOption;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AppiumSwiper extends SeleniumJSExecutor implements Swiper{
	private float swipeTop;
	private float swipeBottom;
	private int swipeMaxWait;
	private AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumSwiper(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		appiumDriver = driver;
		swipeTop = getConfig().value(SetuOption.GUIAUTO_SWIPE_TOP).asFloat();
		swipeBottom = getConfig().value(SetuOption.GUIAUTO_SWIPE_BOTTOM).asFloat();
		swipeMaxWait = getConfig().value(SetuOption.GUIAUTO_SWIPE_MAX_WAIT).asInt();
	}
	
	private AppiumDriver<MobileElement> getAppiumDriver(){
		return this.appiumDriver;
	}
	
	private void swipe(Direction direction, int count, float startFraction, float endFraction) throws Exception {
		Dimension size = this.getAppiumDriver().manage().window().getSize();
		int x1 = (int) (0.5 * size.width);
		int y1 = (int) (size.height * startFraction);
		int x2 = (int) (0.5 * size.width);
		int y2 = (int) (size.height * endFraction);

		if (this.getConfig().getOSType() == OSType.ANDROID) {
			new AndroidTouchAction((AndroidDriver<MobileElement>) this.getAppiumDriver())
			.press(PointOption.point(x1,  y1))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeMaxWait)))
			.moveTo(PointOption.point(x2, y2)).release().perform();
		} else {
			Map<String, Double> swipeElement = new HashMap<String, Double>();
			swipeElement.put("startX", (double) x1);
			swipeElement.put("startY", (double) y1);
			swipeElement.put("endX", (double) x2);
			swipeElement.put("endY", (double) y2);
			swipeElement.put("duration", (double) swipeMaxWait);
			this.executeScript("mobile: swipe", swipeElement);
		}
		
	}

	@Override
	public void swipeUp(int count) throws Exception {
		swipe(Direction.UP, count, swipeBottom, swipeTop);
	}

	@Override
	public void swipeDown(int count) throws Exception {
		swipe(Direction.DOWN, count, swipeTop, swipeBottom);
	}
	
}