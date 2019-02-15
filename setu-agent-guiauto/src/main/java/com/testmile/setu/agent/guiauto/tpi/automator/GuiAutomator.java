package com.testmile.setu.agent.guiauto.tpi.automator;

import com.testmile.setu.agent.guiauto.tpi.handler.automator.AlertHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.BrowserHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.FrameHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.HybridViewHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.JSExecutor;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Screenshoter;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Scroller;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Swiper;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.WindowHandler;

public interface GuiAutomator {

	String getName();

	void compareImagesLocatedAt(String leftImagePath, String rightImagePath) throws Exception;

	void compareImagesLocatedAt(String leftImagePath, String rightImagePath, Double minScore) throws Exception;

	AlertHandler getAlertHandler() throws Exception;

	BrowserHandler getBrowserHandler() throws Exception;

	FrameHandler getFrameHandler() throws Exception;

	HybridViewHandler getViewHandler() throws Exception;

	JSExecutor getJsExecutor() throws Exception;

	Screenshoter getScreenshoter() throws Exception;

	Scroller getScroller() throws Exception;

	Swiper getSwiper() throws Exception;

	WindowHandler getWindowHandler() throws Exception;

	void quit() throws Exception;

	ElementFinder getElementFinder() throws Exception;

}