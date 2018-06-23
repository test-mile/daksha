/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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
package daksha.core.cleanup.automator;

import java.io.File;
import java.util.Map;

import daksh.core.batteries.image.ImageComprator;
import daksha.core.batteries.config.TestContext;
import daksha.core.cleanup.automator.proxy.GuiAutomatorProxy;
import daksha.core.cleanup.element.proxy.GuiElementProxy;
import daksha.core.cleanup.element.proxy.MultiGuiElementProxy;
import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.enums.OSType;
import daksha.core.cleanup.enums.UiDriverEngine;
import daksha.core.cleanup.picker.GuiElementPicker;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.cleanup.enums.GuiAutomationContext;
import daksha.tpi.cleanup.enums.GuiElementType;
import daksha.tpi.enums.DakshaOption;

public abstract class AbstractGuiAutomator<D,E> implements ConcreteGuiAutomator<D,E>{
	private TestContext testContext = null;
	private OSType os = null;
	UiDriverEngine uiAutomatorEngineName = null;
	private GuiAutomationContext automatorContext = null;
	String appTitle = null;
	private ElementLoaderType loaderType = ElementLoaderType.AUTOMATOR;
	private GuiElementPicker<D,E> picker = null;
	private int waitTime = 10;
	private GuiAutomatorProxy proxy = null;
	private ImageComprator imageComprator = null;
	
	public AbstractGuiAutomator(TestContext testContext) throws Exception {
		this.setUiTestEngineName(UiDriverEngine.DEFAULT);
		this.testContext = testContext;
		this.imageComprator = new ImageComprator(testContext.getConfig().value(DakshaOption.IMAGE_COMPARISON_MIN_SCORE).asDouble());
	}
	
	public AbstractGuiAutomator(TestContext testContext, UiDriverEngine name, GuiAutomationContext automatorContext) throws Exception{
		this(testContext);
		this.setUiTestEngineName(name);
		this.setAutomatorContext(automatorContext);
	}
	
	public AbstractGuiAutomator(TestContext testContext, UiDriverEngine name, GuiAutomationContext automatorContext, ElementLoaderType loaderType) throws Exception{
		this(testContext, name, automatorContext);
		this.setElementLoaderType(loaderType);
	}
	
	@Override
	public void setProxy(GuiAutomatorProxy proxy) {
		this.proxy = proxy;
	}

	@Override
	public GuiAutomatorProxy getProxy() {
		return this.proxy;
	}
	
	public String getName() {
		return uiAutomatorEngineName.toString();
	}
	
	protected void setPicker(GuiElementPicker<D,E> picker) {
		this.picker = picker;
	}
	
	public GuiElementPicker<D,E> getPicker(){
		return this.picker;
	}
	
	@Override
	public TestContext getTestContext() {
		return this.testContext;
	}
	
	protected OSType getOSType() {
		return os;
	}

	public void setOSType(OSType os) {
		this.os = os;
	}
	
	public int getWaitTime() {
		return this.waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	/*
	 * Exceptions
	 */
	
	protected void throwGenericUiAutomatorException(
			String automatorName,
			String action,
			String code,
			String message
			) throws Exception{
				throw new Problem("Automator",
				automatorName,
				action,
				code,
				message
				);		
	}
	
	public UiDriverEngine getUiDriverEngineName(){
		return this.uiAutomatorEngineName;
	}
	
	public void setUiTestEngineName(UiDriverEngine name){
		this.uiAutomatorEngineName = name;
	}
	
	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}

	public String getAppTitle() {
		return this.appTitle;
	}

	public GuiAutomationContext getAutomatorContext() {
		return automatorContext;
	}

	public void setAutomatorContext(GuiAutomationContext context) {
		this.automatorContext = context;
	}

	public Object throwUnsupportedActionException(String action) throws Exception {
		throw new Problem(
				"Automator",
				"Default Automator",
				action,
				ErrorType.AUTOMATOR_UNSUPPORTED_ACTION,
				"Unsupported action for " + this.getClass().getSimpleName()
			);		
	}

	@Override
	public GuiElementProxy elementWithId(String id) throws Exception {
		return this.picker.elementWithId(id);
	}

	@Override
	public GuiElementProxy elementWithName(String name) throws Exception {
		return this.picker.elementWithName(name);
	}

	@Override
	public GuiElementProxy elementWithClass(String klass) throws Exception {
		return this.picker.elementWithClass(klass);
	}

	@Override
	public GuiElementProxy elementWithCss(String cssSelector) throws Exception {
		return this.picker.elementWithCss(cssSelector);
	}
	
	@Override
	public GuiElementProxy elementWithTagName(String tag) throws Exception {
		return this.picker.elementWithTagName(tag);
	}

	@Override
	public GuiElementProxy elementWithLinkText(String text) throws Exception {
		return this.picker.elementWithLinkText(text);
	}

	@Override
	public GuiElementProxy elementWithPartialLinkText(String textContent) throws Exception {
		return this.picker.elementWithPartialLinkText(textContent);
	}

	@Override
	public GuiElementProxy elementWithXPath(String xpath) throws Exception {
		return this.picker.elementWithXPath(xpath);
	}

	@Override
	public GuiElementProxy elementWithXText(String text) throws Exception {
		return this.picker.elementWithXText(text);
	}

	@Override
	public GuiElementProxy elementWithXPartialText(String textContent) throws Exception {
		return this.picker.elementWithXPartialText(textContent);
	}

	@Override
	public GuiElementProxy elementWithXValue(String value) throws Exception {
		return this.picker.elementWithXValue(value);
	}
	
	@Override
	public GuiElementProxy elementWithXTitle(String value) throws Exception {
		return this.picker.elementWithXTitle(value);
	}

	@Override
	public GuiElementProxy elementWithXImageSource(String path) throws Exception {
		return this.picker.elementWithXImageSource(path);
	}

	@Override
	public GuiElementProxy elementOfXType(GuiElementType type) throws Exception {
		return this.picker.elementOfXType(type);
	}

	@Override
	public GuiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		return this.picker.elementBasedOnImage(imagePath);
	}
	
	@Override
	public MultiGuiElementProxy elementsWithId(String id) throws Exception {
		return this.picker.elementsWithId(id);
	}

	@Override
	public MultiGuiElementProxy elementsWithName(String name) throws Exception {
		return this.picker.elementsWithName(name);
	}

	@Override
	public MultiGuiElementProxy elementsWithClass(String klass) throws Exception {
		return this.picker.elementsWithClass(klass);
	}

	@Override
	public MultiGuiElementProxy elementsWithCss(String cssSelector) throws Exception {
		return this.picker.elementsWithCss(cssSelector);
	}
	
	@Override
	public MultiGuiElementProxy elementsWithTagName(String tag) throws Exception {
		return this.picker.elementsWithTagName(tag);
	}

	@Override
	public MultiGuiElementProxy elementsWithLinkText(String text) throws Exception {
		return this.picker.elementsWithLinkText(text);
	}

	@Override
	public MultiGuiElementProxy elementsWithPartialLinkText(String textContent) throws Exception {
		return this.picker.elementsWithPartialLinkText(textContent);
	}

	@Override
	public MultiGuiElementProxy elementsWithXPath(String xpath) throws Exception {
		return this.picker.elementsWithXPath(xpath);
	}

	@Override
	public MultiGuiElementProxy elementsWithXText(String text) throws Exception {
		return this.picker.elementsWithXText(text);
	}

	@Override
	public MultiGuiElementProxy elementsWithXPartialText(String textContent) throws Exception {
		return this.picker.elementsWithXPartialText(textContent);
	}

	@Override
	public MultiGuiElementProxy elementsWithXValue(String value) throws Exception {
		return this.picker.elementsWithXValue(value);
	}
	
	@Override
	public MultiGuiElementProxy elementsWithXTitle(String value) throws Exception {
		return this.picker.elementsWithXTitle(value);
	}

	@Override
	public MultiGuiElementProxy elementsWithXImageSource(String path) throws Exception {
		return this.picker.elementsWithXImageSource(path);
	}

	@Override
	public MultiGuiElementProxy elementsOfXType(GuiElementType type) throws Exception {
		return this.picker.elementsOfXType(type);
	}

	@Override
	public MultiGuiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		return this.picker.elementsBasedOnImage(imagePath);
	}

	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage) throws Exception {
		return this.imageComprator.areImagesSimilar(leftImagePath, rightImage);
	}

	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage, Double minScore) throws Exception {
		return this.imageComprator.areImagesSimilar(leftImagePath, rightImage);
	}

	@Override
	public boolean areImagesSimilar(String leftImagePath, String rightImagePath) throws Exception {
		return this.imageComprator.areImagesSimilar(leftImagePath, rightImagePath);
	}

	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage) throws Exception {
		return this.imageComprator.areImagesSimilar(leftImage, rightImage);
	}
	
	@Override
	public D getUiDriverEngine() throws Exception {
		return (D) throwUnsupportedActionException("getUiTestEngine");
	}

	@Override
	public File takeScreenshot() throws Exception {
		return (File) throwUnsupportedActionException("takeScreenShot");
	}

	@Override
	public void focusOnApp() throws Exception {
		throwUnsupportedActionException("focusOnApp");}

	@Override
	public void confirmAlertIfPresent() throws Exception {
		throwUnsupportedActionException("confirmAlertIfPresent");}

	@Override
	public void close() throws Exception {
		throwUnsupportedActionException("close");}

	@Override
	public String getCurrentWindow() throws Exception {
		return (String) throwUnsupportedActionException("getCurrentWindow");}

	@Override
	public void switchToNewWindow() throws Exception {
		throwUnsupportedActionException("switchToNewWindow");}

	@Override
	public void switchToWindow(String windowHandle) throws Exception {
		throwUnsupportedActionException("switchToWindow");}

	@Override
	public void closeCurrentWindow() throws Exception {
		throwUnsupportedActionException("closeWindow");}

	@Override
	public void goTo(String url) throws Exception {
		throwUnsupportedActionException("goTo");}

	@Override
	public void refresh() throws Exception {
		throwUnsupportedActionException("refresh");}

	@Override
	public void back() throws Exception {
		throwUnsupportedActionException("back");}

	@Override
	public void forward() throws Exception {
		throwUnsupportedActionException("forward");}

	@Override
	public void waitForBody() throws Exception {
		throwUnsupportedActionException("waitForBody");}

	@Override
	public void switchToFrame(int index) throws Exception {
		throwUnsupportedActionException("switchToFrame");}

	@Override
	public void switchToFrame(String name) throws Exception {
		throwUnsupportedActionException("switchToFrame");}

	@Override
	public void switchToDefaultFrame() throws Exception {
		throwUnsupportedActionException("switchToDefaultFrame");}

	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage, Double minScore) throws Exception {
		return (boolean) throwUnsupportedActionException("areImagesSimilar");}

	@Override
	public void switchToWebView() throws Exception{
		throwUnsupportedActionException("switchToWebView");
	}
	
	@Override
	public void switchToWebView(String pkg) throws Exception{
		throwUnsupportedActionException("switchToWebView");
	}
	
	@Override
	public void switchToNativeView() throws Exception{
		throwUnsupportedActionException("switchToNativeView");
	}
	
	@Override
	public void sendKeysToScreen(String text) throws Exception {
		throwUnsupportedActionException("sendKeysToScreen");
	}

	@Override
	public void scrollDown(int downCount) throws Exception {
		throwUnsupportedActionException("scrollDown");
	}

	@Override
	public void scrollDown() throws Exception {
		throwUnsupportedActionException("scrollDown");
	}

	@Override
	public void scrollUp(int upCount) throws Exception {
		throwUnsupportedActionException("scrollUp");
	}

	@Override
	public void scrollUp() throws Exception {
		throwUnsupportedActionException("scrollUp");
	}

	
	@Override
	public void swipeDown(int downCount) throws Exception {
		throwUnsupportedActionException("scrollDown");
	}

	@Override
	public void swipeDown() throws Exception {
		throwUnsupportedActionException("scrollDown");
	}

	@Override
	public void swipeUp(int upCount) throws Exception {
		throwUnsupportedActionException("scrollUp");
	}

	@Override
	public void swipeUp() throws Exception {
		throwUnsupportedActionException("scrollUp");
	}
	
	public ElementLoaderType getElementLoaderType() {
		return loaderType;
	}

	public void setElementLoaderType(ElementLoaderType loaderType) {
		this.loaderType = loaderType;
	}

	@Override
	public void load() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCapabilities(Map<String, ?> caps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

}