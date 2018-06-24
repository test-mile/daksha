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
package daksha.tpi.cleanup.ui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import daksha.Daksha;
import daksha.core.batteries.config.TestContext;
import daksha.core.cleanup.automator.proxy.UiAutomatorProxy;
import daksha.core.cleanup.element.proxy.BaseUiElementProxy;
import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.element.proxy.UiMultiElementProxy;
import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.enums.UiDriverEngine;
import daksha.core.cleanup.loader.UiDefRepository;
import daksha.core.cleanup.loader.UiDefinition;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.cleanup.constructor.UiDefLoaderFactory;
import daksha.tpi.cleanup.enums.UiAutomationContext;
import daksha.tpi.cleanup.enums.UiElementType;
import daksha.tpi.sysauto.utils.DataUtils;

public class DefaultUI implements UI{
	private Logger logger = Daksha.getLogger();
	private TestContext testContext = null;
	private UiAutomatorProxy automator = null;
	private UiDefinition uiDef = null;
	private UiAutomationContext context = null;
	private String label;
	private UI parent = null;
	private ElementLoaderType loaderType = null;
	private String imagesDirectory =  null;
	private String name = null;
	private String defPath = null;
	private Map<String, UI> children = new HashMap<String, UI>();
	
	public DefaultUI(
			String label,
			UiAutomatorProxy automator,
			String defPath) throws Exception{
		populateUiInfo(label, automator);
		this.defPath = defPath;
		
		loadDefinition(defPath);
		goToUi();
	}
	
	public DefaultUI(UiAutomatorProxy automator) throws Exception{
		populateUiInfo(this.getClass().getSimpleName(), automator);
		this.defPath = this.getDefPath();
		loadDefinition(defPath);
		goToUi();
	}
	
	public DefaultUI(String label, UiAutomatorProxy automator) throws Exception{
		populateUiInfo(label, automator);
		this.defPath = this.getDefPath();
		
		loadDefinition(defPath);
		goToUi();
	}
	
	public DefaultUI(
			UI parent,
			String label, 
			UiAutomatorProxy automator, 
			String defPath) throws Exception {
		populateUiInfo(label, automator);
		populateParentInfo(parent);
		this.defPath = defPath;
		
		loadDefinition(defPath);
		goToUi();
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
	
	protected void goToUi() throws Exception {
		try {
			this.load();
			this.validateLoaded();
		} catch (Throwable e) {
			throw new Exception(String.format("UI %s did not load as expected. Error: %s", this.getName(), e.getMessage()));
		}
	}
	
	private void populateUiInfo(String name, UiAutomatorProxy automator) throws Exception {
		this.setName(name);
		this.setLabel(name);
		this.testContext = automator.getTestContext();
		this.setContext(automator.getAutomatorContext());
		this.setElementLoaderType(ElementLoaderType.UI);
		this.setAutomator(automator);	
	}
	
	protected TestContext getTestContext() {
		return this.testContext;
	}
	
	private void populateParentInfo(UI parent) {
		this.setParent(parent);
		this.setName(parent.getName() + "." + this.getLabel());
		this.setElementLoaderType(ElementLoaderType.COMPOSITE_UI);		
	}
	
	private void loadDefinition(String defPath) throws Exception {
		if (!UiDefRepository.INSTANCE.hasUiDef(this.getLabel())) {
			this.uiDef = UiDefRepository.INSTANCE.loadUiDef(this.getLabel(), UiDefLoaderFactory.createUiDefLoader(this.getTestContext(), defPath));
		} else {
			this.uiDef = UiDefRepository.INSTANCE.getUiDef(this.getLabel());
		}
		
	}	
	
	@Override
	public UiDefinition getDefinition() throws Exception {
		return this.uiDef;
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}


	public ElementLoaderType getElementLoaderType() {
		return this.loaderType;
	}

	private void setElementLoaderType(ElementLoaderType type) {
		this.loaderType = type;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

	private void setLabel(String label) {
		this.label = label;
	}

	@Override
	public UiAutomationContext getAutomatorContext() throws Exception {
		return context;
	}

	private void setContext(UiAutomationContext context) throws Exception {
		this.context = context;
	}

	@Override
	public UI getParent() {
		return parent;
	}

	private void setParent(UI parent) {
		this.parent = parent;
	}

	@Override
	public UiAutomatorProxy getAutomator() throws Exception {
		return this.automator;
	}

	private void setAutomator(UiAutomatorProxy automator) throws Exception {
		if (automator != null){
			this.automator = automator;
		} else {
			throwNullAutomatorException("setAutomator");
		}
	}

	public UiElementProxy getElement(String elementName) throws Exception {
		if (elementName == null){
			return (UiElementProxy) throwNullElementException("element", elementName);
		} else if (!this.uiDef.has(elementName)) {
			return (UiElementProxy) throwUndefinedElementException("element", elementName);		
		} else {
			UiElementProxy proxy = this.getAutomator().getConcreteAutomator().getPicker().createProxy(this, this.uiDef.getMetaData(elementName));
			return proxy;
		}
	}
	
	public UiMultiElementProxy getElements(String elementName) throws Exception {
		if (elementName == null){
			return (UiMultiElementProxy) throwNullElementException("element", elementName);
		} else if (!this.uiDef.has(elementName)) {
			return (UiMultiElementProxy) throwUndefinedElementException("element", elementName);		
		} else {
			UiMultiElementProxy proxy = this.getAutomator().getConcreteAutomator().getPicker().createMultiProxy(this, this.uiDef.getMetaData(elementName));
			return proxy;
		}
	}
	
	public UiElementProxy element(String name) throws Exception {
		return getElement(name);
	}


	@Override
	public UiMultiElementProxy elements(String name) throws Exception {
		return getElements(name);
	}
	
	public void addChild(String label, String defPath) throws Exception {
		UI ui = new DefaultUI(this, label, this.getAutomator(), defPath);
		getChildMap().put(label.toLowerCase(), ui);
	}
	
	protected UI throwUndefinedUiException(String method, String uiLabel) throws Exception{
		throw new Problem(
			"UI",
			this.getName(),
			method,
			ErrorType.COMPOSITE_UI_NONEXISTING_LABEL,
			String.format(ErrorType.COMPOSITE_UI_NONEXISTING_LABEL, uiLabel, this.getName())
		);
	}
	
	protected UI throwNullUiException(String method) throws Exception{
		throw new Problem(
			"UI",
			this.getName(),
			method,
			ErrorType.COMPOSITE_UI_NULL_LABEL,
			String.format(ErrorType.COMPOSITE_UI_NULL_LABEL, this.getName() )
		);
	}
	
	public UI ui(String uiName) throws Exception {
		if (uiName != null){
			if (getChildMap().containsKey(uiName.toLowerCase())){
				return getChildMap().get(uiName.toLowerCase());
			} else{
				return throwUndefinedUiException("ui", uiName);
			}
		} else {
			return throwNullUiException("ui");
		}
	}

	protected Map<String, UI> getChildMap() {
		return children;
	}

	protected void setChildMap(Map<String, UI> uiMap) {
		this.children = uiMap;
	}

	protected Object throwDefaultUiException(String action, String code, String message) throws Exception {
		throw new Problem(
				Daksha.getComponentName(),
				this.getName(),
				action,
				code,
				message
				);		
	}

	public Object throwNullAutomatorException(String methodName) throws Exception {
		return throwDefaultUiException(
				methodName,
				ErrorType.UI_NULL_AUTOMATOR,
				String.format(
						ErrorType.UI_NULL_AUTOMATOR,
						Daksha.getAutomationContextName(this.getAutomatorContext())
						)
				);
	}

	public Object throwUndefinedElementException(String methodName, String elementName) throws Exception {
		return throwDefaultUiException(
				methodName,
				ErrorType.UI_UNDEFINED_ELEMENT,
				String.format(
						ErrorType.UI_UNDEFINED_ELEMENT,
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
				ErrorType.UI_NULL_ELEMENT,
				String.format(
						ErrorType.UI_NULL_ELEMENT,
						DataUtils.toTitleCase(this.getAutomatorContext().toString())
						//						Batteries.toTitleCase(getDeviceType().toString()),
						//						Batteries.toTitleCase(getAutomationType().toString())
						)
				);
	}

	private void wrapProxy(BaseUiElementProxy proxy) {
		proxy.setUI(this);
		proxy.setLoaderType(this.getElementLoaderType());		
	}
	
	private UiElementProxy updateUiElementProxy(UiElementProxy proxy) {
		wrapProxy(proxy);
		return proxy;
	}
	
	private UiMultiElementProxy updateUiElementProxy(UiMultiElementProxy proxy) {
		wrapProxy(proxy);
		return proxy;
	}
	
	@Override
	public UiElementProxy elementWithId(String id) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithId(id));
	}

	@Override
	public UiElementProxy elementWithName(String name) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithName(name));
	}

	@Override
	public UiElementProxy elementWithClass(String klass) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithClass(klass));
	}

	@Override
	public UiElementProxy elementWithCss(String cssSelector) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithCss(cssSelector));
	}

	@Override
	public UiElementProxy elementWithTagName(String tag) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithTagName(tag));
	}
	
	@Override
	public UiElementProxy elementWithLinkText(String text) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithLinkText(text));
	}

	@Override
	public UiElementProxy elementWithPartialLinkText(String textContent) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithPartialLinkText(textContent));
	}

	@Override
	public UiElementProxy elementWithXPath(String xpath) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithXPath(xpath));
	}

	@Override
	public UiElementProxy elementWithXText(String text) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithXText(text));
	}

	@Override
	public UiElementProxy elementWithXPartialText(String textContent) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithXPartialText(textContent));
	}

	@Override
	public UiElementProxy elementWithXValue(String value) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithXValue(value));
	}
	
	@Override
	public UiElementProxy elementWithXTitle(String value) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithXTitle(value));
	}

	@Override
	public UiElementProxy elementWithXImageSource(String path) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementWithXImageSource(path));
	}

	@Override
	public UiElementProxy elementOfXType(UiElementType type) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementOfXType(type));
	}

	@Override
	public UiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementBasedOnImage(imagePath));
	}
	
	@Override
	public UiMultiElementProxy elementsWithId(String id) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithId(id));
	}

	@Override
	public UiMultiElementProxy elementsWithName(String name) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithName(name));
	}

	@Override
	public UiMultiElementProxy elementsWithClass(String klass) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithClass(klass));
	}

	@Override
	public UiMultiElementProxy elementsWithCss(String cssSelector) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithCss(cssSelector));
	}

	@Override
	public UiMultiElementProxy elementsWithTagName(String tag) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithTagName(tag));
	}
	
	@Override
	public UiMultiElementProxy elementsWithLinkText(String text) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithLinkText(text));
	}

	@Override
	public UiMultiElementProxy elementsWithPartialLinkText(String textContent) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithPartialLinkText(textContent));
	}

	@Override
	public UiMultiElementProxy elementsWithXPath(String xpath) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXPath(xpath));
	}

	@Override
	public UiMultiElementProxy elementsWithXText(String text) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXText(text));
	}

	@Override
	public UiMultiElementProxy elementsWithXPartialText(String textContent) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXPartialText(textContent));
	}

	@Override
	public UiMultiElementProxy elementsWithXValue(String value) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXValue(value));
	}
	
	@Override
	public UiMultiElementProxy elementsWithXTitle(String value) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXTitle(value));
	}

	@Override
	public UiMultiElementProxy elementsWithXImageSource(String path) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXImageSource(path));
	}

	@Override
	public UiMultiElementProxy elementsOfXType(UiElementType type) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsOfXType(type));
	}

	@Override
	public UiMultiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsBasedOnImage(imagePath));
	}

	@Override
	public UiDriverEngine getUiDriverEngineName() throws Exception {
		return this.getAutomator().getUiDriverEngineName();
	}

	//
	@Override
	public File takeScreenshot() throws Exception {
		return this.getAutomator().takeScreenshot();
	}
	//
	@Override
	public void setAppTitle(String appTitle) throws Exception {
		this.getAutomator().setAppTitle(appTitle);
	}
	//
	@Override
	public String getAppTitle() throws Exception {
		return this.getAutomator().getAppTitle();
	}
	//
	@Override
	public void focusOnApp() throws Exception {
		this.getAutomator().focusOnApp();
	}
	//
	
	//
	@Override
	public void confirmAlertIfPresent() throws Exception {
		this.getAutomator().confirmAlertIfPresent();
	}
	//
	@Override
	public void close() throws Exception {
		this.getAutomator().close();
	}
	//
	@Override
	public String getCurrentWindow() throws Exception {
		return this.getAutomator().getCurrentWindow();
	}
	//
	@Override
	public void switchToNewWindow() throws Exception {
		this.getAutomator().switchToNewWindow();
	}
	//
	@Override
	public void switchToWindow(String windowHandle) throws Exception {
		this.getAutomator().switchToWindow(windowHandle);
	}
	//
	@Override
	public void closeCurrentWindow() throws Exception {
		this.getAutomator().closeCurrentWindow();
	}
	//
	@Override
	public void goTo(String url) throws Exception {
		this.getAutomator().goTo(url);
	}
	//
	@Override
	public void refresh() throws Exception {
		this.getAutomator().refresh();
	}
	//
	@Override
	public void back() throws Exception {
		this.getAutomator().back();
	}
	//
	@Override
	public void forward() throws Exception {
		this.getAutomator().forward();
	}
	//
	@Override
	public void waitForBody() throws Exception {
		this.getAutomator().waitForBody();
	}
	//
	@Override
	public void switchToFrame(int index) throws Exception {
		this.getAutomator().switchToFrame(index);
	}
	//
	@Override
	public void switchToFrame(String name) throws Exception {
		this.getAutomator().switchToFrame(name);
	}
	//
	@Override
	public void switchToDefaultFrame() throws Exception {
		this.getAutomator().switchToDefaultFrame();
	}
	//
	@Override
	public boolean areImagesSimilar(File leftImagePath, File rightImage, Double minScore) throws Exception {
		return this.getAutomator().areImagesSimilar(leftImagePath, rightImage, minScore);
	}
	//
	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage) throws Exception {
		return this.getAutomator().areImagesSimilar(leftImagePath, rightImage);
	}
	//
	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage, Double minScore) throws Exception {
		return this.getAutomator().areImagesSimilar(leftImagePath, rightImage, minScore);
	}
	//
	@Override
	public boolean areImagesSimilar(String leftImagePath, String rightImagePath) throws Exception {
		return this.getAutomator().areImagesSimilar(leftImagePath, rightImagePath);
	}
	//
	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage) throws Exception {
		return this.getAutomator().areImagesSimilar(leftImage, rightImage);
	}
	
	@Override
	public void sendKeysToScreen(String text) throws Exception {
		this.getAutomator().sendKeysToScreen(text);
	}

	@Override
	public void scrollDown(int count) throws Exception {
		this.getAutomator().scrollDown(count);
	}

	@Override
	public void scrollDown() throws Exception {
		this.getAutomator().scrollDown();
	}

	@Override
	public void scrollUp(int count) throws Exception {
		this.getAutomator().scrollUp(count);
	}

	@Override
	public void scrollUp() throws Exception {
		this.getAutomator().scrollUp();
	}

	@Override
	public void swipeDown(int count) throws Exception {
		this.getAutomator().swipeDown(count);
	}

	@Override
	public void swipeDown() throws Exception {
		this.getAutomator().swipeDown();
	}

	@Override
	public void swipeUp(int count) throws Exception {
		this.getAutomator().swipeUp(count);
	}

	@Override
	public void swipeUp() throws Exception {
		this.getAutomator().swipeUp();
	}

	@Override
	public void switchToNativeView() throws Exception {
		this.getAutomator().switchToNativeView();
	}

	@Override
	public void switchToWebView() throws Exception {
		this.getAutomator().switchToWebView();
	}

	@Override
	public void switchToWebView(String pkg) throws Exception {
		this.getAutomator().switchToWebView(pkg);
	}
}
