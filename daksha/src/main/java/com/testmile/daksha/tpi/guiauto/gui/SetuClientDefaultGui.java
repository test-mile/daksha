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
package com.testmile.daksha.tpi.guiauto.gui;

import java.io.File;

import com.testmile.daksha.core.setu.client.guiauto.proxy.page.SetuGui;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiElement;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiMultiElement;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.guiauto.enums.GuiElementType;
import com.testmile.trishanku.tpi.guiauto.enums.GuiAutomatorName;
import com.testmile.trishanku.tpi.guiauto.enums.GuiElementLoaderType;

public class SetuClientDefaultGui implements SetuClientGui{
	private SetuGui setuGui;

	
	public SetuClientDefaultGui(
			String label,
			SetuClientGuiAutomator automator,
			String defPath) throws Exception{
		setuGui = new SetuGui(label, automator, defPath);
		goToGui();
	}
	
	public SetuClientDefaultGui(SetuClientGuiAutomator automator) throws Exception{
		setuGui = new SetuGui(automator);
		goToGui();
	}
	
	public SetuClientDefaultGui(String label, SetuClientGuiAutomator automator) throws Exception{
		setuGui = new SetuGui(label, automator);
		goToGui();
	}
	
	public SetuClientDefaultGui(
			SetuClientGui parent,
			String label, 
			SetuClientGuiAutomator automator, 
			String defPath) throws Exception {
		setuGui = new SetuGui(parent, label, automator, defPath);
		goToGui();
	}
	
	private SetuGui getSetuGui() {
		return this.setuGui;
	}
	
	protected String getDefPath() throws Exception{
		return this.setuGui.getDefPath();
	}
	
	protected void load() throws Exception{
		// Child classes can override this and write any necessary loading instructions.
	}
	
	protected void validateLoaded() throws Throwable{
		this.setuGui.validateLoaded();
	}
	
	protected void goToGui() throws Exception {
		try {
			this.load();
			this.validateLoaded();
		} catch (Throwable e) {
			throw new Exception(String.format("Gui %s did not load as expected. Error: %s", this.getName(), e.getMessage()));
		}
	}
	
	protected TestContext getTestContext() {
		return this.setuGui.getTestContext();
	}
	
	public String getName() {
		return this.setuGui.getName();
	}

	public GuiElementLoaderType getElementLoaderType() {
		return this.setuGui.getElementLoaderType();
	}
	
	public String getLabel() {
		return this.setuGui.getLabel();
	}
	
	public GuiAutomationContext getAutomatorContext() throws Exception {
		return this.setuGui.getAutomatorContext();
	}
	
	public SetuClientGui getParent() {
		return this.setuGui.getParent();
	}
	
	public SetuClientGuiAutomator getAutomator() throws Exception {
		return this.setuGui.getAutomator();
	}
	
	public SetuClientGuiElement element(String name) throws Exception {
		return this.getSetuGui().element(name);
	}
	
	public SetuClientGuiMultiElement elements(String name) throws Exception {
		return this.getSetuGui().elements(name);
	}
	
	public void addChild(String label, String defPath) throws Exception {
		SetuClientGui child = new SetuClientDefaultGui(this, label, this.getAutomator(), defPath);
		this.setuGui.addChild(label, child);
	}
	
	public SetuClientGui gui(String name) throws Exception {
		return this.setuGui.gui(name);
	}
	
	public SetuClientGuiElement elementWithId(String id) throws Exception {
		return this.setuGui.elementWithId(id);
	}

	
	public SetuClientGuiElement elementWithName(String name) throws Exception {
		return this.setuGui.elementWithName(name);
	}

	
	public SetuClientGuiElement elementWithClass(String klass) throws Exception {
		return this.setuGui.elementWithClass(klass);
	}

	
	public SetuClientGuiElement elementWithCss(String cssSelector) throws Exception {
		return this.setuGui.elementWithCss(cssSelector);
	}

	
	public SetuClientGuiElement elementWithTagName(String tag) throws Exception {
		return this.setuGui.elementWithTagName(tag);
	}
	
	
	public SetuClientGuiElement elementWithLinkText(String text) throws Exception {
		return this.setuGui.elementWithLinkText(text);
	}

	
	public SetuClientGuiElement elementWithPartialLinkText(String textContent) throws Exception {
		return this.setuGui.elementWithPartialLinkText(textContent);
	}

	
	public SetuClientGuiElement elementWithXPath(String xpath) throws Exception {
		return this.setuGui.elementWithXPath(xpath);
	}

	
	public SetuClientGuiElement elementWithXText(String text) throws Exception {
		return this.setuGui.elementWithXText(text);
	}

	
	public SetuClientGuiElement elementWithXPartialText(String textContent) throws Exception {
		return this.setuGui.elementWithXPartialText(textContent);
	}

	
	public SetuClientGuiElement elementWithXValue(String value) throws Exception {
		return this.setuGui.elementWithXValue(value);
	}
	
	
	public SetuClientGuiElement elementWithXTitle(String value) throws Exception {
		return this.setuGui.elementWithXTitle(value);
	}

	
	public SetuClientGuiElement elementWithXImageSource(String path) throws Exception {
		return this.setuGui.elementWithXImageSource(path);
	}

	
	public SetuClientGuiElement elementOfXType(GuiElementType type) throws Exception {
		return this.setuGui.elementOfXType(type);
	}

	
	public SetuClientGuiElement elementBasedOnImage(String imagePath) throws Exception {
		return this.setuGui.elementBasedOnImage(imagePath);
	}
	
	
	public SetuClientGuiMultiElement elementsWithId(String id) throws Exception {
		return this.setuGui.elementsWithId(id);
	}

	
	public SetuClientGuiMultiElement elementsWithName(String name) throws Exception {
		return this.setuGui.elementsWithName(name);
	}

	
	public SetuClientGuiMultiElement elementsWithClass(String klass) throws Exception {
		return this.setuGui.elementsWithClass(klass);
	}

	
	public SetuClientGuiMultiElement elementsWithCss(String cssSelector) throws Exception {
		return this.setuGui.elementsWithCss(cssSelector);
	}

	
	public SetuClientGuiMultiElement elementsWithTagName(String tag) throws Exception {
		return this.setuGui.elementsWithTagName(tag);
	}
	
	
	public SetuClientGuiMultiElement elementsWithLinkText(String text) throws Exception {
		return this.setuGui.elementsWithLinkText(text);
	}

	
	public SetuClientGuiMultiElement elementsWithPartialLinkText(String textContent) throws Exception {
		return this.setuGui.elementsWithPartialLinkText(textContent);
	}

	
	public SetuClientGuiMultiElement elementsWithXPath(String xpath) throws Exception {
		return this.setuGui.elementsWithXPath(xpath);
	}

	
	public SetuClientGuiMultiElement elementsWithXText(String text) throws Exception {
		return this.setuGui.elementsWithXText(text);
	}

	
	public SetuClientGuiMultiElement elementsWithXPartialText(String textContent) throws Exception {
		return this.setuGui.elementsWithXPartialText(textContent);
	}

	
	public SetuClientGuiMultiElement elementsWithXValue(String value) throws Exception {
		return this.setuGui.elementsWithXValue(value);
	}
	
	
	public SetuClientGuiMultiElement elementsWithXTitle(String value) throws Exception {
		return this.setuGui.elementsWithXTitle(value);
	}

	
	public SetuClientGuiMultiElement elementsWithXImageSource(String path) throws Exception {
		return this.setuGui.elementsWithXImageSource(path);
	}

	
	public SetuClientGuiMultiElement elementsOfXType(GuiElementType type) throws Exception {
		return this.setuGui.elementsOfXType(type);
	}

	
	public SetuClientGuiMultiElement elementsBasedOnImage(String imagePath) throws Exception {
		return this.setuGui.elementsBasedOnImage(imagePath);
	}

	
	public GuiAutomatorName getGuiDriverEngineName() throws Exception {
		return this.setuGui.getGuiDriverEngineName();
	}

	public File takeScreenshot() throws Exception {
		return this.setuGui.takeScreenshot();
	}

	public void setAppTitle(String appTitle) throws Exception {
		this.setuGui.setAppTitle(appTitle);
	}

	public String getAppTitle() throws Exception {
		return this.setuGui.getAppTitle();
	}

	public void focusOnApp() throws Exception {
		this.setuGui.focusOnApp();
	}
	
	public void confirmAlertIfPresent() throws Exception {
		this.setuGui.confirmAlertIfPresent();
	}

	public void close() throws Exception {
		this.setuGui.close();
	}
	
	public String getCurrentWindow() throws Exception {
		return this.setuGui.getCurrentWindow();
	}
	
	public void switchToNewWindow() throws Exception {
		this.setuGui.switchToNewWindow();
	}
	
	public void switchToWindow(String windowHandle) throws Exception {
		this.setuGui.switchToWindow(windowHandle);
	}
	
	public void closeCurrentWindow() throws Exception {
		this.setuGui.closeCurrentWindow();
	}
	
	public void goTo(String url) throws Exception {
		this.setuGui.goTo(url);
	}
	
	public void refresh() throws Exception {
		this.setuGui.refresh();
	}
	
	public void back() throws Exception {
		this.setuGui.back();
	}
	
	public void forward() throws Exception {
		this.setuGui.forward();
	}
	
	public void waitForBody() throws Exception {
		this.setuGui.waitForBody();
	}
	
	public void switchToFrameByIndex(int index) throws Exception {
		this.setuGui.switchToFrameByIndex(index);
	}
	
	public void switchToFrameByName(String name) throws Exception {
		this.setuGui.switchToFrameByName(name);
	}
	
	public void switchToDefaultFrame() throws Exception {
		this.setuGui.switchToDefaultFrame();
	}
	
	public void sendKeysToScreen(String text) throws Exception {
		this.setuGui.sendKeysToScreen(text);
	}
	
	public void scrollDown(int count) throws Exception {
		this.setuGui.scrollDown(count);
	}
	
	public void scrollDown() throws Exception {
		this.setuGui.scrollDown();
	}

	public void scrollUp(int count) throws Exception {
		this.setuGui.scrollUp(count);
	}

	public void scrollUp() throws Exception {
		this.setuGui.scrollUp();
	}

	public void swipeDown(int count) throws Exception {
		this.setuGui.swipeDown(count);
	}
	
	public void swipeDown() throws Exception {
		this.setuGui.swipeDown();
	}
	
	public void swipeUp(int count) throws Exception {
		this.setuGui.swipeUp(count);
	}
	
	public void swipeUp() throws Exception {
		this.setuGui.swipeUp();
	}
	
	public void switchToNativeView() throws Exception {
		this.setuGui.switchToNativeView();
	}
	
	public void switchToWebView() throws Exception {
		this.setuGui.switchToWebView();
	}
	
	public void switchToWebView(String pkg) throws Exception {
		this.setuGui.switchToWebView(pkg);
	}

	public void compareImageFiles(File leftImage, File rightImage, Double minScore) throws Exception {
		this.setuGui.compareImageFiles(leftImage.getAbsolutePath(), rightImage.getAbsolutePath(), minScore);
	}
	
	public void compareImageFiles(File leftImage, File rightImage) throws Exception {
		this.setuGui.compareImageFiles(leftImage.getAbsolutePath(), rightImage.getAbsolutePath());
	}

	public void compareImagesLocatedAt(String leftImagePath, String rightImagePath, Double minScore) throws Exception {
		this.setuGui.compareImageFiles(leftImagePath, rightImagePath, minScore);
	}
	
	public void compareImagesLocatedAt(String leftImagePath, String rightImagePath) throws Exception {
		this.setuGui.compareImageFiles(leftImagePath, rightImagePath);
	}
}
