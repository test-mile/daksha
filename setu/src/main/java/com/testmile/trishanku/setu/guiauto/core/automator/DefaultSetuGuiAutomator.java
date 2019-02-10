package com.testmile.trishanku.setu.guiauto.core.automator;

import java.io.File;
import java.util.Map;

import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.setu.guiauto.core.identifier.GuiElementMetaData;
import com.testmile.trishanku.setu.guiauto.tpi.automator.SetuGuiAutomator;
import com.testmile.trishanku.setu.guiauto.tpi.element.SetuGuiElement;
import com.testmile.trishanku.setu.guiauto.tpi.element.SetuGuiMultiElement;
import com.testmile.trishanku.tpi.guiauto.enums.GuiAutomatorName;
import com.testmile.trishanku.tpi.guiauto.enums.GuiElementLoaderType;
import com.testmile.trishanku.tpi.guiauto.enums.LocatorType;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

public class DefaultSetuGuiAutomator implements SetuGuiAutomator{

	public GuiAutomatorName getGuiDriverEngineName() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public File takeScreenshot() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public GuiAutomationContext getAutomatorContext() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAppTitle(String appTitle) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String getAppTitle() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void focusOnApp() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public SetuGuiElement identify(LocatorType by, String value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public SetuGuiMultiElement identifyAll(LocatorType by, String value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void goTo(String url) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void refresh() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void back() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void forward() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void waitForBody() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void switchToFrameByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void switchToFrameByName(String name) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void switchToDefaultFrame() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void compareImagesLocatedAt(String leftImagePath, String rightImagePath) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void compareImagesLocatedAt(String leftImagePath, String rightImagePath, Double minScore) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void swipeDown(int downCount) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void swipeDown() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void swipeUp(int upCount) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void swipeUp() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void switchToNativeView() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void switchToWebView() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void switchToWebView(String pkg) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void scrollDown(int downCount) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void scrollDown() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void scrollUp(int upCount) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void scrollUp() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void confirmAlertIfPresent() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String getCurrentWindow() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void switchToNewWindow() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void switchToWindow(String windowHandle) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void closeCurrentWindow() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void sendKeysToScreen(String text) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setAutomatorContext(GuiAutomationContext screen) {
		// TODO Auto-generated method stub
		
	}

	public GuiElementLoaderType getElementLoaderType() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void setElementLoaderType(GuiElementLoaderType loaderType) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void load() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setCapabilities(Map<String, ?> caps) {
		// TODO Auto-generated method stub
		
	}

	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setWaitTime(int waitTime) {
		// TODO Auto-generated method stub
		
	}

	public void setOSType(OSType platformType) {
		// TODO Auto-generated method stub
		
	}

	public TestContext getTestContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getWaitTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public SetuGuiElement element(GuiElementMetaData eData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public SetuGuiMultiElement elements(GuiElementMetaData eData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void validatePageLoad() {
		// TODO Auto-generated method stub
		
	}

}
