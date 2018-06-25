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

package daksha.core.cleanup.automator.proxy;

import java.io.File;
import java.util.Map;

import daksha.core.batteries.config.TestContext;
import daksha.core.cleanup.automator.ConcreteUiAutomator;
import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.element.proxy.UiMultiElementProxy;
import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.enums.OSType;
import daksha.core.cleanup.enums.UiDriverEngine;
import daksha.tpi.cleanup.automator.UiAutomator;
import daksha.tpi.cleanup.enums.UiAutomationContext;
import daksha.tpi.cleanup.enums.UiElementType;

public class UiAutomatorProxy implements UiAutomator{
	private ConcreteUiAutomator<?,?> concreteAutomator = null;

	public UiAutomatorProxy(ConcreteUiAutomator<?,?> concreteAutomator) {
		this.concreteAutomator = concreteAutomator;
		this.concreteAutomator.setProxy(this);
	}

	@Override
	public UiDriverEngine getUiDriverEngineName() throws Exception {
		return this.concreteAutomator.getUiDriverEngineName();
	}

	@Override
	public File takeScreenshot() throws Exception {
		return this.concreteAutomator.takeScreenshot();
	}

	@Override
	public UiAutomationContext getAutomatorContext() throws Exception {
		return this.concreteAutomator.getAutomatorContext();
	}

	@Override
	public void setAppTitle(String text) throws Exception {
		this.concreteAutomator.setAppTitle(text);
	}

	@Override
	public String getAppTitle() throws Exception {
		return this.concreteAutomator.getAppTitle();
	}

	@Override
	public void focusOnApp() throws Exception {
		this.concreteAutomator.focusOnApp();
	}

	@Override
	public void confirmAlertIfPresent() throws Exception {
		this.concreteAutomator.confirmAlertIfPresent();
	}

	@Override
	public void close() throws Exception {
		this.concreteAutomator.close();
	}

	@Override
	public String getCurrentWindow() throws Exception {
		return this.concreteAutomator.getCurrentWindow();
	}

	@Override
	public void switchToNewWindow() throws Exception {
		this.concreteAutomator.switchToNewWindow();
	}

	@Override
	public void switchToWindow(String windowHandle) throws Exception {
		this.concreteAutomator.switchToWindow(windowHandle);
	}

	@Override
	public void closeCurrentWindow() throws Exception {
		this.concreteAutomator.closeCurrentWindow();
	}

	@Override
	public void sendKeysToScreen(String text) throws Exception {
		this.concreteAutomator.sendKeysToScreen(text);
	}

	@Override
	public UiElementProxy elementWithId(String id) throws Exception {
		return this.concreteAutomator.elementWithId(id);
	}

	@Override
	public UiElementProxy elementWithName(String name) throws Exception {
		return this.concreteAutomator.elementWithName(name);
	}

	@Override
	public UiElementProxy elementWithClass(String klass) throws Exception {
		return this.concreteAutomator.elementWithClass(klass);
	}

	@Override
	public UiElementProxy elementWithCss(String selector) throws Exception {
		return this.concreteAutomator.elementWithCss(selector);
	}

	@Override
	public UiElementProxy elementWithTagName(String tag) throws Exception {
		return this.concreteAutomator.elementWithTagName(tag);
	}

	@Override
	public UiElementProxy elementWithLinkText(String text) throws Exception {
		return this.concreteAutomator.elementWithLinkText(text);
	}

	@Override
	public UiElementProxy elementWithPartialLinkText(String textContent) throws Exception {
		return this.concreteAutomator.elementWithPartialLinkText(textContent);
	}

	@Override
	public UiElementProxy elementWithXPath(String xpath) throws Exception {
		return this.concreteAutomator.elementWithXPath(xpath);
	}

	@Override
	public UiElementProxy elementWithXText(String text) throws Exception {
		return this.concreteAutomator.elementWithXText(text);
	}

	@Override
	public UiElementProxy elementWithXPartialText(String textContent) throws Exception {
		return this.concreteAutomator.elementWithXPartialText(textContent);
	}

	@Override
	public UiElementProxy elementWithXValue(String value) throws Exception {
		return this.concreteAutomator.elementWithXValue(value);
	}

	@Override
	public UiElementProxy elementWithXTitle(String value) throws Exception {
		return this.concreteAutomator.elementWithXTitle(value);
	}

	@Override
	public UiElementProxy elementWithXImageSource(String path) throws Exception {
		return this.concreteAutomator.elementWithXImageSource(path);
	}

	@Override
	public UiElementProxy elementOfXType(UiElementType type) throws Exception {
		
		return this.concreteAutomator.elementOfXType(type);
	}

	@Override
	public UiElementProxy elementBasedOnImage(String path) throws Exception {
		
		return this.concreteAutomator.elementBasedOnImage(path);
	}

	@Override
	public UiMultiElementProxy elementsWithId(String id) throws Exception {
		
		return this.concreteAutomator.elementsWithId(id);
	}

	@Override
	public UiMultiElementProxy elementsWithName(String name) throws Exception {
		
		return this.concreteAutomator.elementsWithName(name);
	}

	@Override
	public UiMultiElementProxy elementsWithClass(String klass) throws Exception {
		
		return this.concreteAutomator.elementsWithClass(klass);
	}

	@Override
	public UiMultiElementProxy elementsWithCss(String selector) throws Exception {
		
		return this.concreteAutomator.elementsWithCss(selector);
	}

	@Override
	public UiMultiElementProxy elementsWithTagName(String tag) throws Exception {
		
		return this.concreteAutomator.elementsWithTagName(tag);
	}

	@Override
	public UiMultiElementProxy elementsWithLinkText(String text) throws Exception {
		
		return this.concreteAutomator.elementsWithLinkText(text);
	}

	@Override
	public UiMultiElementProxy elementsWithPartialLinkText(String textContent) throws Exception {
		
		return this.concreteAutomator.elementsWithPartialLinkText(textContent);
	}

	@Override
	public UiMultiElementProxy elementsWithXPath(String xpath) throws Exception {
		
		return this.concreteAutomator.elementsWithXPath(xpath);
	}

	@Override
	public UiMultiElementProxy elementsWithXText(String text) throws Exception {
		
		return this.concreteAutomator.elementsWithXText(text);
	}

	@Override
	public UiMultiElementProxy elementsWithXPartialText(String textContent) throws Exception {
		return this.concreteAutomator.elementsWithXPartialText(textContent);
	}

	@Override
	public UiMultiElementProxy elementsWithXValue(String value) throws Exception {
		return this.concreteAutomator.elementsWithXValue(value);
	}
	

	@Override
	public UiMultiElementProxy elementsWithXTitle(String value) throws Exception {
		return this.concreteAutomator.elementsWithXTitle(value);
	}

	@Override
	public UiMultiElementProxy elementsWithXImageSource(String path) throws Exception {
		return this.concreteAutomator.elementsWithXImageSource(path);
	}

	@Override
	public UiMultiElementProxy elementsOfXType(UiElementType type) throws Exception {
		return this.concreteAutomator.elementsOfXType(type);
	}

	@Override
	public UiMultiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		return this.concreteAutomator.elementsBasedOnImage(imagePath);
	}

	@Override
	public void goTo(String url) throws Exception {
		this.concreteAutomator.goTo(url);
	}

	@Override
	public void refresh() throws Exception {
		this.concreteAutomator.refresh();
	}

	@Override
	public void back() throws Exception {
		this.concreteAutomator.back();
	}

	@Override
	public void forward() throws Exception {
		this.concreteAutomator.forward();
	}

	@Override
	public void waitForBody() throws Exception {
		this.concreteAutomator.waitForBody();
	}

	@Override
	public void switchToFrame(int index) throws Exception {
		this.concreteAutomator.switchToFrame(index);
	}

	@Override
	public void switchToFrame(String name) throws Exception {
		this.concreteAutomator.switchToFrame(name);
	}

	@Override
	public void switchToDefaultFrame() throws Exception {
		this.concreteAutomator.switchToDefaultFrame();
	}

	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage, Double minScore) throws Exception {
		return this.concreteAutomator.areImagesSimilar(leftImage, rightImage, minScore);
	}

	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage) throws Exception {
		return this.concreteAutomator.areImagesSimilar(leftImagePath, rightImage);
	}

	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage, Double minScore) throws Exception {
		return this.concreteAutomator.areImagesSimilar(leftImagePath, rightImage, minScore);
	}

	@Override
	public boolean areImagesSimilar(String leftImagePath, String rightImagePath) throws Exception {
		return this.concreteAutomator.areImagesSimilar(leftImagePath, rightImagePath);
	}

	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage) throws Exception {
		return this.concreteAutomator.areImagesSimilar(leftImage, rightImage);
	}

	@Override
	public void scrollDown(int downCount) throws Exception {
		this.concreteAutomator.scrollDown(downCount);
	}

	@Override
	public void scrollDown() throws Exception {
		this.concreteAutomator.scrollDown();
	}

	@Override
	public void scrollUp(int upCount) throws Exception {
		this.concreteAutomator.scrollUp(upCount);
	}

	@Override
	public void scrollUp() throws Exception {
		this.concreteAutomator.scrollUp();
	}

	@Override
	public void setAutomatorContext(UiAutomationContext automatorContext) {
		this.concreteAutomator.setAutomatorContext(automatorContext);
	}

	@Override
	public void switchToWebView() throws Exception {
		this.concreteAutomator.switchToWebView();
	}
	
	@Override
	public void switchToWebView(String pkg) throws Exception {
		this.concreteAutomator.switchToWebView(pkg);
	}

	@Override
	public void switchToNativeView() throws Exception {
		this.concreteAutomator.switchToNativeView();
	}

	@Override
	public ElementLoaderType getElementLoaderType() throws Exception {
		return this.concreteAutomator.getElementLoaderType();
	}

	@Override
	public void setElementLoaderType(ElementLoaderType loaderType) throws Exception {
		this.concreteAutomator.setElementLoaderType(loaderType);
	}

	@Override
	public void load() throws Exception {
		this.concreteAutomator.load();
	}

	@Override
	public void setCapabilities(Map<String, ?> caps) {
		this.concreteAutomator.setCapabilities(caps);	
	}

	@Override
	public void init() throws Exception {
		this.concreteAutomator.init();
	}

	@Override
	public String getName() {
		return this.concreteAutomator.getName();
	}

	@Override
	public void setWaitTime(int waitTime) {
		this.concreteAutomator.setWaitTime(waitTime);
	}

	@Override
	public void setOSType(OSType platformType) {
		this.concreteAutomator.setOSType(platformType);
	}

	@Override
	public TestContext getTestContext() {
		
		return this.concreteAutomator.getTestContext();
	}

	@Override
	public int getWaitTime() {
		return this.concreteAutomator.getWaitTime();
	}

	public ConcreteUiAutomator<?,?> getConcreteAutomator() {
		return this.concreteAutomator;
	}

	@Override
	public void swipeDown(int count) throws Exception {
		this.concreteAutomator.swipeDown(count);
	}

	@Override
	public void swipeDown() throws Exception {
		this.concreteAutomator.swipeDown();
	}

	@Override
	public void swipeUp(int count) throws Exception {
		this.concreteAutomator.swipeUp(count);
	}

	@Override
	public void swipeUp() throws Exception {
		this.concreteAutomator.swipeUp();
	}

	public void validatePageLoad() {
		this.concreteAutomator.validatePageLoad();
	}

}
