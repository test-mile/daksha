package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import org.openqa.selenium.WebDriver;

import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Scroller;
import com.testmile.trishanku.tpi.guiauto.enums.Direction;

public class SeleniumScroller extends SeleniumJSExecutor implements Scroller{
	private int scrollPixelCount;
	
	public SeleniumScroller(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		scrollPixelCount = getConfig().value(DakshaOption.GUIAUTO_SCROLL_PIXELS).asInt();
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
