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
package com.testmile.trishanku.setu.tpi.guiautotpi.gui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.testmile.daksha.core.guiauto.namestore.GuiNameStore;
import com.testmile.daksha.core.guiauto.namestore.GuiNamespace;
import com.testmile.daksha.core.problem.ErrorType;
import com.testmile.daksha.core.problem.Problem;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.sysauto.utils.DataUtils;
import com.testmile.setu.guiauto.GuiNamespaceLoaderFactory;
import com.testmile.trishanku.Trishanku;
import com.testmile.trishanku.setu.guiauto.tpi.automator.SetuGuiAutomator;
import com.testmile.trishanku.setu.guiauto.tpi.element.SetuGuiElement;
import com.testmile.trishanku.setu.guiauto.tpi.element.SetuGuiMultiElement;
import com.testmile.trishanku.tpi.guiauto.enums.GuiAutomatorName;
import com.testmile.trishanku.tpi.guiauto.enums.GuiElementLoaderType;
import com.testmile.trishanku.tpi.guiauto.enums.LocatorType;

public class SetuDefaultGui implements SetuGui{
	private Logger logger = Trishanku.getLogger();
	private TestContext testContext = null;
	private SetuGuiAutomator automator = null;
	private GuiNamespace ns = null;
	private GuiAutomationContext context = null;
	private String label;
	private SetuGui parent = null;
	private GuiElementLoaderType loaderType = null;
	private String imagesDirectory =  null;
	private String name = null;
	private String defPath = null;
	private Map<String, SetuGui> children = new HashMap<String, SetuGui>();
	
	public SetuDefaultGui(
			String label,
			SetuGuiAutomator automator,
			String defPath) throws Exception{
		populateGuiInfo(label, automator);
		this.defPath = defPath;
		
		loadNamespace(defPath);
		goToGui();
	}
	
	public SetuDefaultGui(SetuGuiAutomator automator) throws Exception{
		populateGuiInfo(this.getClass().getSimpleName(), automator);
		this.defPath = this.getDefPath();
		loadNamespace(defPath);
		goToGui();
	}
	
	public SetuDefaultGui(String label, SetuGuiAutomator automator) throws Exception{
		populateGuiInfo(label, automator);
		this.defPath = this.getDefPath();
		
		loadNamespace(defPath);
		goToGui();
	}
	
	public SetuDefaultGui(
			SetuGui parent,
			String label, 
			SetuGuiAutomator automator, 
			String defPath) throws Exception {
		populateGuiInfo(label, automator);
		populateParentInfo(parent);
		this.defPath = defPath;
		
		loadNamespace(defPath);
		goToGui();
	}
	
	protected String getDefPath() throws Exception{
		return this.defPath;
	}
	
	protected void load() throws Exception{
		// Child classes can override this and write any necessary loading instructions.
	}
	
	protected void validateLoaded() throws Throwable{
		this.getAutomator().validatePageLoad();
	}
	
	protected void goToGui() throws Exception {
		try {
			this.load();
			this.validateLoaded();
		} catch (Throwable e) {
			throw new Exception(String.format("UI %s did not load as expected. Error: %s", this.getName(), e.getMessage()));
		}
	}
	
	private void populateGuiInfo(String name, SetuGuiAutomator automator) throws Exception {
		this.setName(name);
		this.setLabel(name);
		this.testContext = automator.getTestContext();
		this.setContext(automator.getAutomatorContext());
		this.setElementLoaderType(GuiElementLoaderType.GUI);
		this.setAutomator(automator);	
	}
	
	protected TestContext getTestContext() {
		return this.testContext;
	}
	
	private void populateParentInfo(SetuGui parent) {
		this.setParent(parent);
		this.setName(parent.getName() + "." + this.getLabel());
		this.setElementLoaderType(GuiElementLoaderType.COMPOSITE_GUI);		
	}
	
	private void loadNamespace(String defPath) throws Exception {
		if (!GuiNameStore.INSTANCE.hasNamespace(this.getLabel())) {
			this.ns = GuiNameStore.INSTANCE.loadNamespace(this.getLabel(), GuiNamespaceLoaderFactory.createNamespaceLoader(this.getTestContext(), defPath));
		} else {
			this.ns = GuiNameStore.INSTANCE.getNamespace(this.getLabel());
		}
		
	}	
	
	
	public GuiNamespace getDefinition() throws Exception {
		return this.ns;
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}


	public GuiElementLoaderType getElementLoaderType() {
		return this.loaderType;
	}

	private void setElementLoaderType(GuiElementLoaderType type) {
		this.loaderType = type;
	}

	
	public String getLabel() {
		return this.label;
	}

	private void setLabel(String label) {
		this.label = label;
	}

	
	public GuiAutomationContext getAutomatorContext() throws Exception {
		return context;
	}

	private void setContext(GuiAutomationContext context) throws Exception {
		this.context = context;
	}

	
	public SetuGui getParent() {
		return parent;
	}

	private void setParent(SetuGui parent) {
		this.parent = parent;
	}

	
	public SetuGuiAutomator getAutomator() throws Exception {
		return this.automator;
	}

	private void setAutomator(SetuGuiAutomator automator) throws Exception {
		if (automator != null){
			this.automator = automator;
		} else {
			throwNullAutomatorException("setAutomator");
		}
	}
	
	public SetuGuiElement element(String name) throws Exception {
		// Add logic for davanced identification
		return null;
	}
	
	public SetuGuiMultiElement elements(String name) throws Exception {
		// Add logic for davanced identification
		return null;
	}
	
	public void addChild(String label, String defPath) throws Exception {
		SetuGui gui = new SetuDefaultGui(this, label, this.getAutomator(), defPath);
		this.addChild(label, gui);
	}
	
	public void addChild(String label, SetuGui gui) {
		getChildMap().put(label.toLowerCase(), gui);
	}
	
	protected SetuGui throwUndefinedUiException(String method, String label) throws Exception{
		throw new Problem(
			"gui",
			this.getName(),
			method,
			ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL, label, this.getName())
		);
	}
	
	protected SetuGui throwNullUiException(String method) throws Exception{
		throw new Problem(
			"gui",
			this.getName(),
			method,
			ErrorType.COMPOSITE_GUI_NULL_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NULL_LABEL, this.getName() )
		);
	}
	
	public SetuGui gui(String name) throws Exception {
		if (name != null){
			if (getChildMap().containsKey(name.toLowerCase())){
				return getChildMap().get(name.toLowerCase());
			} else{
				return throwUndefinedUiException("gui", name);
			}
		} else {
			return throwNullUiException("gui");
		}
	}

	protected Map<String, SetuGui> getChildMap() {
		return children;
	}

	protected void setChildMap(Map<String, SetuGui> guiMap) {
		this.children = guiMap;
	}

	protected Object throwDefaultUiException(String action, String code, String message) throws Exception {
		throw new Problem(
				"GUI Automator",
				this.getName(),
				action,
				code,
				message
				);		
	}

	public Object throwNullAutomatorException(String methodName) throws Exception {
		return throwDefaultUiException(
				methodName,
				ErrorType.GUI_NULL_AUTOMATOR,
				String.format(
						ErrorType.GUI_NULL_AUTOMATOR,
						Trishanku.getAutomationContextName(this.getAutomatorContext())
						)
				);
	}

	public Object throwUndefinedElementException(String methodName, String elementName) throws Exception {
		return throwDefaultUiException(
				methodName,
				ErrorType.GUI_UNDEFINED_ELEMENT,
				String.format(
						ErrorType.GUI_UNDEFINED_ELEMENT,
						elementName,
						DataUtils.toTitleCase(this.getAutomatorContext().toString())
						//						Batteries.toTitleCase(getDeviceType().toString()),
						//						Batteries.toTitleCase(getAutomationType().toString())
						)
				);
	}

	public Object throwNullElementException(String methodName, String elementName) throws Exception {
		return throwDefaultUiException(
				methodName,
				ErrorType.GUI_NULL_ELEMENT,
				String.format(
						ErrorType.GUI_NULL_ELEMENT,
						DataUtils.toTitleCase(this.getAutomatorContext().toString())
						//						Batteries.toTitleCase(getDeviceType().toString()),
						//						Batteries.toTitleCase(getAutomationType().toString())
						)
				);
	}
	
	private SetuGuiElement updateElement(SetuGuiElement element) {
		return element;
	}
	
	private SetuGuiMultiElement updateMultiElement(SetuGuiMultiElement mutliElement) {
		return mutliElement;
	}
	
	public SetuGuiElement identify(LocatorType by, String value) throws Exception {
		return updateElement(this.getAutomator().identify(by, value));
	}

	public SetuGuiMultiElement identifyAll(LocatorType by, String value) throws Exception {
		return updateMultiElement(this.getAutomator().identifyAll(by, value));
	}

	
	public GuiAutomatorName getGuiDriverEngineName() throws Exception {
		return this.getAutomator().getGuiDriverEngineName();
	}

	//
	
	public File takeScreenshot() throws Exception {
		return this.getAutomator().takeScreenshot();
	}
	//
	
	public void setAppTitle(String appTitle) throws Exception {
		this.getAutomator().setAppTitle(appTitle);
	}
	//
	
	public String getAppTitle() throws Exception {
		return this.getAutomator().getAppTitle();
	}
	//
	
	public void focusOnApp() throws Exception {
		this.getAutomator().focusOnApp();
	}
	//
	
	//
	
	public void confirmAlertIfPresent() throws Exception {
		this.getAutomator().confirmAlertIfPresent();
	}
	//
	
	public void close() throws Exception {
		this.getAutomator().close();
	}
	//
	
	public String getCurrentWindow() throws Exception {
		return this.getAutomator().getCurrentWindow();
	}
	//
	
	public void switchToNewWindow() throws Exception {
		this.getAutomator().switchToNewWindow();
	}
	//
	
	public void switchToWindow(String windowHandle) throws Exception {
		this.getAutomator().switchToWindow(windowHandle);
	}
	//
	
	public void closeCurrentWindow() throws Exception {
		this.getAutomator().closeCurrentWindow();
	}
	//
	
	public void goTo(String url) throws Exception {
		this.getAutomator().goTo(url);
	}
	//
	
	public void refresh() throws Exception {
		this.getAutomator().refresh();
	}
	//
	
	public void back() throws Exception {
		this.getAutomator().back();
	}
	//
	
	public void forward() throws Exception {
		this.getAutomator().forward();
	}
	//
	
	public void waitForBody() throws Exception {
		this.getAutomator().waitForBody();
	}
	//
	
	public void switchToFrameByIndex(int index) throws Exception {
		this.getAutomator().switchToFrameByIndex(index);
	}
	//
	
	public void switchToFrameByName(String name) throws Exception {
		this.getAutomator().switchToFrameByName(name);
	}
	//
	
	public void switchToDefaultFrame() throws Exception {
		this.getAutomator().switchToDefaultFrame();
	}
	//
	
	public void compareImagesLocatedAt(String leftImagePath, String rightImagePath, Double minScore) throws Exception {
		this.getAutomator().compareImagesLocatedAt(leftImagePath, rightImagePath, minScore);
	}
	//
	
	public void compareImagesLocatedAt(String leftImagePath, String rightImagePath) throws Exception {
		this.getAutomator().compareImagesLocatedAt(leftImagePath, rightImagePath);
	}
	
	
	public void sendKeysToScreen(String text) throws Exception {
		this.getAutomator().sendKeysToScreen(text);
	}

	
	public void scrollDown(int count) throws Exception {
		this.getAutomator().scrollDown(count);
	}

	
	public void scrollDown() throws Exception {
		this.getAutomator().scrollDown();
	}

	
	public void scrollUp(int count) throws Exception {
		this.getAutomator().scrollUp(count);
	}

	
	public void scrollUp() throws Exception {
		this.getAutomator().scrollUp();
	}

	
	public void swipeDown(int count) throws Exception {
		this.getAutomator().swipeDown(count);
	}

	
	public void swipeDown() throws Exception {
		this.getAutomator().swipeDown();
	}

	
	public void swipeUp(int count) throws Exception {
		this.getAutomator().swipeUp(count);
	}

	
	public void swipeUp() throws Exception {
		this.getAutomator().swipeUp();
	}

	
	public void switchToNativeView() throws Exception {
		this.getAutomator().switchToNativeView();
	}

	
	public void switchToWebView() throws Exception {
		this.getAutomator().switchToWebView();
	}

	
	public void switchToWebView(String pkg) throws Exception {
		this.getAutomator().switchToWebView(pkg);
	}
}
