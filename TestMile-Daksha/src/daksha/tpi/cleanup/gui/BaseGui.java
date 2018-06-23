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
package daksha.tpi.cleanup.gui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import daksha.Daksha;
import daksha.core.batteries.config.TestContext;
import daksha.core.cleanup.automator.proxy.GuiAutomatorProxy;
import daksha.core.cleanup.element.proxy.BaseGuiElementProxy;
import daksha.core.cleanup.element.proxy.GuiElementProxy;
import daksha.core.cleanup.element.proxy.MultiGuiElementProxy;
import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.enums.UiDriverEngine;
import daksha.core.cleanup.loader.GuiDefRepository;
import daksha.core.cleanup.loader.GuiDefinition;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.cleanup.constructor.GuiDefLoaderFactory;
import daksha.tpi.cleanup.enums.GuiAutomationContext;
import daksha.tpi.cleanup.enums.GuiElementType;
import daksha.tpi.sysauto.utils.DataUtils;

public abstract class BaseGui implements Gui{
	private Logger logger = Daksha.getLogger();
	private TestContext testContext = null;
	private GuiAutomatorProxy automator = null;
	private GuiDefinition guiDef = null;
	private GuiAutomationContext context = null;
	private String label;
	private Gui parent = null;
	private ElementLoaderType loaderType = null;
	private String imagesDirectory =  null;
	private String name = null;
	private String defPath = null;
	private Map<String, Gui> children = new HashMap<String, Gui>();
	
	public BaseGui(
			String label,
			GuiAutomatorProxy automator,
			String defPath) throws Exception{
		populateGuiInfo(label, automator);
		this.defPath = defPath;
		loadDefinition(defPath);
	}
	
	public BaseGui(GuiAutomatorProxy automator) throws Exception{
		populateGuiInfo(this.getClass().getSimpleName(), automator);
		this.defPath = this.getDefPath();
		loadDefinition(defPath);
	}
	
	public BaseGui(String label, GuiAutomatorProxy automator) throws Exception{
		populateGuiInfo(label, automator);
		this.defPath = this.getDefPath();
		loadDefinition(defPath);
	}
	
	public BaseGui(
			Gui parent,
			String label, 
			GuiAutomatorProxy automator, 
			String defPath) throws Exception {
		populateGuiInfo(label, automator);
		populateParentInfo(parent);
		this.defPath = defPath;
		
		loadDefinition(defPath);
	}
	
	protected String getDefPath() throws Exception{
		return this.defPath;
	}
	
	protected abstract void validateLoaded() throws Throwable;
	
	protected void goToGui() throws Exception {
		try {
			this.validateLoaded();
		} catch (Throwable e) {
			throw new Exception(String.format("GUI %s did not load as expected. Error: %s", this.getName(), e.getMessage()));
		}
	}
	
	
	private void populateGuiInfo(String name, GuiAutomatorProxy automator) throws Exception {
		this.setName(name);
		this.setLabel(name);
		this.testContext = automator.getTestContext();
		this.setContext(automator.getAutomatorContext());
		this.setElementLoaderType(ElementLoaderType.GUI);
		this.setGuiAutomator(automator);	
	}
	
	protected TestContext getTestContext() {
		return this.testContext;
	}
	
	private void populateParentInfo(Gui parent) {
		this.setParent(parent);
		this.setName(parent.getName() + "." + this.getLabel());
		this.setElementLoaderType(ElementLoaderType.COMPOSITE_GUI);		
	}
	
	private void loadDefinition(String defPath) throws Exception {
		if (!GuiDefRepository.INSTANCE.hasGuiDef(this.getLabel())) {
			this.guiDef = GuiDefRepository.INSTANCE.loadGuiDef(this.getLabel(), GuiDefLoaderFactory.createGuiDefLoader(this.getTestContext(), defPath));
		} else {
			this.guiDef = GuiDefRepository.INSTANCE.getGuiDef(this.getLabel());
		}
		
	}	
	
	@Override
	public GuiDefinition getGuiDef() throws Exception {
		return this.guiDef;
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
	public GuiAutomationContext getAutomatorContext() throws Exception {
		return context;
	}

	private void setContext(GuiAutomationContext context) throws Exception {
		this.context = context;
	}

	@Override
	public Gui getParent() {
		return parent;
	}

	private void setParent(Gui parent) {
		this.parent = parent;
	}

	@Override
	public GuiAutomatorProxy getGuiAutomator() throws Exception {
		return this.automator;
	}

	private void setGuiAutomator(GuiAutomatorProxy automator) throws Exception {
		if (automator != null){
			this.automator = automator;
		} else {
			throwNullAutomatorException("setAutomator");
		}
	}

	public GuiElementProxy getElement(String elementName) throws Exception {
		if (elementName == null){
			return (GuiElementProxy) throwNullElementException("element", elementName);
		} else if (!this.guiDef.has(elementName)) {
			return (GuiElementProxy) throwUndefinedElementException("element", elementName);		
		} else {
			GuiElementProxy proxy = this.getGuiAutomator().getConcreteAutomator().getPicker().createProxy(this, this.guiDef.getMetaData(elementName));
			return proxy;
		}
	}
	
	public MultiGuiElementProxy getElements(String elementName) throws Exception {
		if (elementName == null){
			return (MultiGuiElementProxy) throwNullElementException("element", elementName);
		} else if (!this.guiDef.has(elementName)) {
			return (MultiGuiElementProxy) throwUndefinedElementException("element", elementName);		
		} else {
			MultiGuiElementProxy proxy = this.getGuiAutomator().getConcreteAutomator().getPicker().createMultiProxy(this, this.guiDef.getMetaData(elementName));
			return proxy;
		}
	}
	
	public GuiElementProxy element(String name) throws Exception {
		return getElement(name);
	}


	@Override
	public MultiGuiElementProxy elements(String name) throws Exception {
		return getElements(name);
	}
	
	public void addChild(String label, String defPath) throws Exception {
		Gui gui = new DefaultGui(this, label, this.getGuiAutomator(), defPath);
		getGuiMap().put(label.toLowerCase(), gui);
	}
	
	protected Gui throwUndefinedUiException(String method, String uiLabel) throws Exception{
		throw new Problem(
			"GUI",
			this.getName(),
			method,
			ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL, uiLabel, this.getName())
		);
	}
	
	protected Gui throwNullUiException(String method) throws Exception{
		throw new Problem(
			"GUI",
			this.getName(),
			method,
			ErrorType.COMPOSITE_GUI_NULL_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NULL_LABEL, this.getName() )
		);
	}
	
	public Gui gui(String uiName) throws Exception {
		if (uiName != null){
			if (getGuiMap().containsKey(uiName.toLowerCase())){
				return getGuiMap().get(uiName.toLowerCase());
			} else{
				return throwUndefinedUiException("gui", uiName);
			}
		} else {
			return throwNullUiException("gui");
		}
	}

	protected Map<String, Gui> getGuiMap() {
		return children;
	}

	protected void setGuiMap(Map<String, Gui> guiMap) {
		this.children = guiMap;
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
				ErrorType.GUI_NULL_AUTOMATOR,
				String.format(
						ErrorType.GUI_NULL_AUTOMATOR,
						Daksha.getAutomationContextName(this.getAutomatorContext())
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

	private void wrapProxy(BaseGuiElementProxy proxy) {
		proxy.setGui(this);
		proxy.setLoaderType(this.getElementLoaderType());		
	}
	
	private GuiElementProxy updateGuiElementProxy(GuiElementProxy proxy) {
		wrapProxy(proxy);
		return proxy;
	}
	
	private MultiGuiElementProxy updateGuiElementProxy(MultiGuiElementProxy proxy) {
		wrapProxy(proxy);
		return proxy;
	}
	
	@Override
	public GuiElementProxy elementWithId(String id) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithId(id));
	}

	@Override
	public GuiElementProxy elementWithName(String name) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithName(name));
	}

	@Override
	public GuiElementProxy elementWithClass(String klass) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithClass(klass));
	}

	@Override
	public GuiElementProxy elementWithCss(String cssSelector) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithCss(cssSelector));
	}

	@Override
	public GuiElementProxy elementWithTagName(String tag) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithTagName(tag));
	}
	
	@Override
	public GuiElementProxy elementWithLinkText(String text) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithLinkText(text));
	}

	@Override
	public GuiElementProxy elementWithPartialLinkText(String textContent) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithPartialLinkText(textContent));
	}

	@Override
	public GuiElementProxy elementWithXPath(String xpath) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithXPath(xpath));
	}

	@Override
	public GuiElementProxy elementWithXText(String text) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithXText(text));
	}

	@Override
	public GuiElementProxy elementWithXPartialText(String textContent) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithXPartialText(textContent));
	}

	@Override
	public GuiElementProxy elementWithXValue(String value) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithXValue(value));
	}
	
	@Override
	public GuiElementProxy elementWithXTitle(String value) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithXTitle(value));
	}

	@Override
	public GuiElementProxy elementWithXImageSource(String path) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementWithXImageSource(path));
	}

	@Override
	public GuiElementProxy elementOfXType(GuiElementType type) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementOfXType(type));
	}

	@Override
	public GuiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementBasedOnImage(imagePath));
	}
	
	@Override
	public MultiGuiElementProxy elementsWithId(String id) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithId(id));
	}

	@Override
	public MultiGuiElementProxy elementsWithName(String name) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithName(name));
	}

	@Override
	public MultiGuiElementProxy elementsWithClass(String klass) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithClass(klass));
	}

	@Override
	public MultiGuiElementProxy elementsWithCss(String cssSelector) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithCss(cssSelector));
	}

	@Override
	public MultiGuiElementProxy elementsWithTagName(String tag) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithTagName(tag));
	}
	
	@Override
	public MultiGuiElementProxy elementsWithLinkText(String text) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithLinkText(text));
	}

	@Override
	public MultiGuiElementProxy elementsWithPartialLinkText(String textContent) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithPartialLinkText(textContent));
	}

	@Override
	public MultiGuiElementProxy elementsWithXPath(String xpath) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithXPath(xpath));
	}

	@Override
	public MultiGuiElementProxy elementsWithXText(String text) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithXText(text));
	}

	@Override
	public MultiGuiElementProxy elementsWithXPartialText(String textContent) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithXPartialText(textContent));
	}

	@Override
	public MultiGuiElementProxy elementsWithXValue(String value) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithXValue(value));
	}
	
	@Override
	public MultiGuiElementProxy elementsWithXTitle(String value) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithXTitle(value));
	}

	@Override
	public MultiGuiElementProxy elementsWithXImageSource(String path) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsWithXImageSource(path));
	}

	@Override
	public MultiGuiElementProxy elementsOfXType(GuiElementType type) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsOfXType(type));
	}

	@Override
	public MultiGuiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		return updateGuiElementProxy(this.getGuiAutomator().elementsBasedOnImage(imagePath));
	}

	@Override
	public UiDriverEngine getUiDriverEngineName() throws Exception {
		return this.getGuiAutomator().getUiDriverEngineName();
	}

	//
	@Override
	public File takeScreenshot() throws Exception {
		return this.getGuiAutomator().takeScreenshot();
	}
	//
	@Override
	public void setAppTitle(String appTitle) throws Exception {
		this.getGuiAutomator().setAppTitle(appTitle);
	}
	//
	@Override
	public String getAppTitle() throws Exception {
		return this.getGuiAutomator().getAppTitle();
	}
	//
	@Override
	public void focusOnApp() throws Exception {
		this.getGuiAutomator().focusOnApp();
	}
	//
	
	//
	@Override
	public void confirmAlertIfPresent() throws Exception {
		this.getGuiAutomator().confirmAlertIfPresent();
	}
	//
	@Override
	public void close() throws Exception {
		this.getGuiAutomator().close();
	}
	//
	@Override
	public String getCurrentWindow() throws Exception {
		return this.getGuiAutomator().getCurrentWindow();
	}
	//
	@Override
	public void switchToNewWindow() throws Exception {
		this.getGuiAutomator().switchToNewWindow();
	}
	//
	@Override
	public void switchToWindow(String windowHandle) throws Exception {
		this.getGuiAutomator().switchToWindow(windowHandle);
	}
	//
	@Override
	public void closeCurrentWindow() throws Exception {
		this.getGuiAutomator().closeCurrentWindow();
	}
	//
	@Override
	public void goTo(String url) throws Exception {
		this.getGuiAutomator().goTo(url);
	}
	//
	@Override
	public void refresh() throws Exception {
		this.getGuiAutomator().refresh();
	}
	//
	@Override
	public void back() throws Exception {
		this.getGuiAutomator().back();
	}
	//
	@Override
	public void forward() throws Exception {
		this.getGuiAutomator().forward();
	}
	//
	@Override
	public void waitForBody() throws Exception {
		this.getGuiAutomator().waitForBody();
	}
	//
	@Override
	public void switchToFrame(int index) throws Exception {
		this.getGuiAutomator().switchToFrame(index);
	}
	//
	@Override
	public void switchToFrame(String name) throws Exception {
		this.getGuiAutomator().switchToFrame(name);
	}
	//
	@Override
	public void switchToDefaultFrame() throws Exception {
		this.getGuiAutomator().switchToDefaultFrame();
	}
	//
	@Override
	public boolean areImagesSimilar(File leftImagePath, File rightImage, Double minScore) throws Exception {
		return this.getGuiAutomator().areImagesSimilar(leftImagePath, rightImage, minScore);
	}
	//
	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage) throws Exception {
		return this.getGuiAutomator().areImagesSimilar(leftImagePath, rightImage);
	}
	//
	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage, Double minScore) throws Exception {
		return this.getGuiAutomator().areImagesSimilar(leftImagePath, rightImage, minScore);
	}
	//
	@Override
	public boolean areImagesSimilar(String leftImagePath, String rightImagePath) throws Exception {
		return this.getGuiAutomator().areImagesSimilar(leftImagePath, rightImagePath);
	}
	//
	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage) throws Exception {
		return this.getGuiAutomator().areImagesSimilar(leftImage, rightImage);
	}
	
	@Override
	public void sendKeysToScreen(String text) throws Exception {
		this.getGuiAutomator().sendKeysToScreen(text);
	}

	@Override
	public void scrollDown(int count) throws Exception {
		this.getGuiAutomator().scrollDown(count);
	}

	@Override
	public void scrollDown() throws Exception {
		this.getGuiAutomator().scrollDown();
	}

	@Override
	public void scrollUp(int count) throws Exception {
		this.getGuiAutomator().scrollUp(count);
	}

	@Override
	public void scrollUp() throws Exception {
		this.getGuiAutomator().scrollUp();
	}

	@Override
	public void swipeDown(int count) throws Exception {
		this.getGuiAutomator().swipeDown(count);
	}

	@Override
	public void swipeDown() throws Exception {
		this.getGuiAutomator().swipeDown();
	}

	@Override
	public void swipeUp(int count) throws Exception {
		this.getGuiAutomator().swipeUp(count);
	}

	@Override
	public void swipeUp() throws Exception {
		this.getGuiAutomator().swipeUp();
	}

	@Override
	public void switchToNativeView() throws Exception {
		this.getGuiAutomator().switchToNativeView();
	}

	@Override
	public void switchToWebView() throws Exception {
		this.getGuiAutomator().switchToWebView();
	}

	@Override
	public void switchToWebView(String pkg) throws Exception {
		this.getGuiAutomator().switchToWebView(pkg);
	}
}
