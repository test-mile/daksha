package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import java.util.Set;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.HybridViewHandler;
import com.testmile.trishanku.tpi.guiauto.enums.MobileView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumViewHandler extends AbstractWDHandler implements HybridViewHandler{
	private AppiumDriver<MobileElement> appiumDriver;

	public AppiumViewHandler(AppiumDriver<MobileElement>  driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		appiumDriver = driver;
	}

	private AppiumDriver<MobileElement>  getAppiumDriver(){
		return this.appiumDriver;
	}
	
	private void switchToView(String view) throws Exception {
		this.getAppiumDriver().context(view);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.appium.HybridViewHandler#switchToNativeView()
	 */
	@Override
	public void switchToNativeView() throws Exception {
		switchToView(MobileView.NATIVE_APP.toString());
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.appium.HybridViewHandler#switchToWebView()
	 */
	@Override
	public void switchToWebView() throws Exception {
		Set<String> contextNames = this.getAppiumDriver().getContextHandles();
		for(String context: contextNames) {
			if (context.contains(MobileView.WEBVIEW.toString())) {
				switchToView(context);
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.appium.HybridViewHandler#switchToWebView(java.lang.String)
	 */
	@Override
	public void switchToWebView(String pkg) throws Exception {
		switchToView(String.format("%s_%s", MobileView.WEBVIEW.toString(), pkg));
	}
}
