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

package com.testmile.setu.actor.guiauto.core.handler.automator.selenium;

import org.openqa.selenium.WebDriver;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.Scroller;
import com.testmile.trishanku.tpi.enums.Direction;
import com.testmile.trishanku.tpi.enums.SetuOption;

public class SeleniumScroller extends SeleniumJSExecutor implements Scroller{
	private int scrollPixelCount;
	
	public SeleniumScroller(WebDriver driver, SetuActorConfig config) throws Exception {
		super(driver, config);
		scrollPixelCount = getConfig().value(SetuOption.GUIAUTO_SCROLL_PIXELS).asInt();
	}
	
	protected void validateScrollSupport() throws Exception{
		// Web apps support scrolling
		return;
	}

	private void scroll(Direction direction, int count) throws Exception {
		validateScrollSupport();
		 
		int spc = 0;
		if (direction == Direction.DOWN) {
			spc = getScrollPixelCount();
		} else if (direction == Direction.UP) {
			spc = -getScrollPixelCount();
		} else {
			throw new Exception("Scroll left/right is not yet supported.");
		}
		for (int i = 0; i < count; i++) {
			this.executeScript(String.format("window.scrollBy(0, %d)", spc));
		}
	}
	
	private int getScrollPixelCount() {
		return this.scrollPixelCount;
	}


	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.Scroller#scrollDown(int)
	 */
	@Override
	public void scrollDown(int count) throws Exception {
		scroll(Direction.DOWN, count);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.Scroller#scrollUp(int)
	 */
	@Override
	public void scrollUp(int count) throws Exception {
		scroll(Direction.UP, count);
	}
}
