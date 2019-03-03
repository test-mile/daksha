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

package com.testmile.setu.actor.guiauto.core.automator;

import com.testmile.setu.actor.SetuAgentConfig;
import com.testmile.setu.actor.core.tpi.image.ImageComprator;
import com.testmile.setu.actor.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.actor.guiauto.tpi.automator.ElementFinder;
import com.testmile.setu.actor.guiauto.tpi.automator.GuiAutomator;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.AlertHandler;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.BrowserHandler;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.FrameHandler;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.HybridViewHandler;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.JSExecutor;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.Screenshoter;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.Scroller;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.Swiper;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.WindowHandler;
import com.testmile.trishanku.tpi.enums.SetuOption;

public abstract class BaseGuiAutomator implements GuiAutomator {
	private SetuAgentConfig config;
	private ImageComprator imageComprator = null;
	
	private AlertHandler alertHandler;
	private BrowserHandler browserHandler;
	private FrameHandler frameHandler;
	private HybridViewHandler viewHandler;
	private JSExecutor jsExecutor;
	private Screenshoter screenshoter;
	private Scroller scroller;
	private Swiper swiper;
	private WindowHandler windowHandler;
	private ElementFinder elementFinder;
	
	public BaseGuiAutomator(SetuAgentConfig config) throws Exception{
		this.config = config;
		this.imageComprator = new ImageComprator(getConfig().value(SetuOption.IMAGE_COMPARISON_MIN_SCORE).asDouble());
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getName()
	 */
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public SetuAgentConfig getConfig() {
		return this.config;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#compareImagesLocatedAt(java.lang.String, java.lang.String)
	 */
	@Override
	public void compareImagesLocatedAt(String leftImagePath, String rightImagePath)  throws Exception{
		this.imageComprator.compareImagesLocatedAt(leftImagePath, rightImagePath);
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#compareImagesLocatedAt(java.lang.String, java.lang.String, java.lang.Double)
	 */
	@Override
	public void compareImagesLocatedAt(String leftImagePath, String rightImagePath, Double minScore)  throws Exception{
		this.imageComprator.compareImagesLocatedAt(leftImagePath, rightImagePath, minScore);
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getAlertHandler()
	 */
	@Override
	public AlertHandler getAlertHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(alertHandler, this.getClass().getSimpleName(), AlertHandler.class.getSimpleName());
		return alertHandler;
	}

	protected void setAlertHandler(AlertHandler alertHandler) {
		this.alertHandler = alertHandler;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getBrowserHandler()
	 */
	@Override
	public BrowserHandler getBrowserHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(browserHandler, this.getClass().getSimpleName(), BrowserHandler.class.getSimpleName());
		return browserHandler;
	}

	protected void setBrowserHandler(BrowserHandler browserHandler) {
		this.browserHandler = browserHandler;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getFrameHandler()
	 */
	@Override
	public FrameHandler getFrameHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(frameHandler, this.getClass().getSimpleName(), FrameHandler.class.getSimpleName());
		return frameHandler;
	}

	protected void setFrameHandler(FrameHandler frameHandler) {
		this.frameHandler = frameHandler;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getViewHandler()
	 */
	@Override
	public HybridViewHandler getViewHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(viewHandler, this.getClass().getSimpleName(), HybridViewHandler.class.getSimpleName());
		return viewHandler;
	}

	public void setViewHandler(HybridViewHandler viewHandler) {
		this.viewHandler = viewHandler;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getJsExecutor()
	 */
	@Override
	public JSExecutor getJsExecutor() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(jsExecutor, this.getClass().getSimpleName(), JSExecutor.class.getSimpleName());
		return jsExecutor;
	}

	protected void setJsExecutor(JSExecutor jsExecutor) {
		this.jsExecutor = jsExecutor;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getScreenshoter()
	 */
	@Override
	public Screenshoter getScreenshoter() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(screenshoter, this.getClass().getSimpleName(), Screenshoter.class.getSimpleName());
		return screenshoter;
	}

	protected void setScreenshoter(Screenshoter screenshoter) {
		this.screenshoter = screenshoter;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getScroller()
	 */
	@Override
	public Scroller getScroller() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(scroller, this.getClass().getSimpleName(), Scroller.class.getSimpleName());
		return scroller;
	}

	protected void setScroller(Scroller scroller) {
		this.scroller = scroller;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getSwiper()
	 */
	@Override
	public Swiper getSwiper() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(swiper, this.getClass().getSimpleName(), Swiper.class.getSimpleName());
		return swiper;
	}

	public void setSwiper(Swiper swiper) {
		this.swiper = swiper;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.automator.GuiAutomator#getWindowHandler()
	 */
	@Override
	public WindowHandler getWindowHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(windowHandler, this.getClass().getSimpleName(), WindowHandler.class.getSimpleName());
		return windowHandler;
	}

	protected void setWindowHandler(WindowHandler windowHandler) {
		this.windowHandler = windowHandler;
	}
	
	@Override
	public ElementFinder getElementFinder() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(elementFinder, this.getClass().getSimpleName(), ElementFinder.class.getSimpleName());
		return this.elementFinder;
	}
	
	protected void setElementFinder(ElementFinder elementFinder) {
		this.elementFinder = elementFinder;
	}

}
