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
package daksha.tpi.uiauto.gui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import daksha.Daksha;
import daksha.core.batteries.config.TestContext;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.uiauto.element.proxy.BaseGuiElementProxy;
import daksha.core.uiauto.element.proxy.GuiElementProxy;
import daksha.core.uiauto.element.proxy.GuiMultiElementProxy;
import daksha.core.uiauto.enums.GuiDriverEngine;
import daksha.core.uiauto.enums.GuiElementLoaderType;
import daksha.core.uiauto.namestore.GuiNamespace;
import daksha.core.uiauto.namestore.GuiNameStore;
import daksha.tpi.sysauto.utils.DataUtils;
import daksha.tpi.uiauto.enums.GuiAutomationContext;
import daksha.tpi.uiauto.enums.GuiElementType;
import daksha.tpi.uiauto.maker.GuiNamespaceLoaderFactory;

public class DefaultGui implements Gui{
	private Logger logger = Daksha.getLogger();
	private TestContext testContext = null;
	private GuiAutomatorProxy automator = null;
	private GuiNamespace ns = null;
	private GuiAutomationContext context = null;
	private String label;
	private Gui parent = null;
	private GuiElementLoaderType loaderType = null;
	private String imagesDirectory =  null;
	private String name = null;
	private String defPath = null;
	private Map<String, Gui> children = new HashMap<String, Gui>();
	
	public DefaultGui(
			String label,
			GuiAutomatorProxy automator,
			String defPath) throws Exception{
		populateGuiInfo(label, automator);
		this.defPath = defPath;
		
		loadNamespace(defPath);
		goToGui();
	}
	
	public DefaultGui(GuiAutomatorProxy automator) throws Exception{
		populateGuiInfo(this.getClass().getSimpleName(), automator);
		this.defPath = this.getDefPath();
		loadNamespace(defPath);
		goToGui();
	}
	
	public DefaultGui(String label, GuiAutomatorProxy automator) throws Exception{
		populateGuiInfo(label, automator);
		this.defPath = this.getDefPath();
		
		loadNamespace(defPath);
		goToGui();
	}
	
	public DefaultGui(
			Gui parent,
			String label, 
			GuiAutomatorProxy automator, 
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
	
	private void populateGuiInfo(String name, GuiAutomatorProxy automator) throws Exception {
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
	
	private void populateParentInfo(Gui parent) {
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
	
	@Override
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
	public GuiAutomatorProxy getAutomator() throws Exception {
		return this.automator;
	}

	private void setAutomator(GuiAutomatorProxy automator) throws Exception {
		if (automator != null){
			this.automator = automator;
		} else {
			throwNullAutomatorException("setAutomator");
		}
	}

	public GuiElementProxy getElement(String elementName) throws Exception {
		if (elementName == null){
			return (GuiElementProxy) throwNullElementException("element", elementName);
		} else if (!this.ns.has(elementName)) {
			return (GuiElementProxy) throwUndefinedElementException("element", elementName);		
		} else {
			GuiElementProxy proxy = this.getAutomator().getConcreteAutomator().getIdentifier().createProxy(this, this.ns.getMetaData(elementName, this.getAutomatorContext()));
			return proxy;
		}
	}
	
	public GuiMultiElementProxy getElements(String elementName) throws Exception {
		if (elementName == null){
			return (GuiMultiElementProxy) throwNullElementException("element", elementName);
		} else if (!this.ns.has(elementName)) {
			return (GuiMultiElementProxy) throwUndefinedElementException("element", elementName);		
		} else {
			GuiMultiElementProxy proxy = this.getAutomator().getConcreteAutomator().getIdentifier().createMultiProxy(this, this.ns.getMetaData(elementName, this.getAutomatorContext()));
			return proxy;
		}
	}
	
	public GuiElementProxy element(String name) throws Exception {
		return getElement(name);
	}


	@Override
	public GuiMultiElementProxy elements(String name) throws Exception {
		return getElements(name);
	}
	
	public void addChild(String label, String defPath) throws Exception {
		Gui gui = new DefaultGui(this, label, this.getAutomator(), defPath);
		this.addChild(label, gui);
	}
	
	public void addChild(String label, Gui gui) {
		getChildMap().put(label.toLowerCase(), gui);
	}
	
	protected Gui throwUndefinedUiException(String method, String label) throws Exception{
		throw new Problem(
			"gui",
			this.getName(),
			method,
			ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL, label, this.getName())
		);
	}
	
	protected Gui throwNullUiException(String method) throws Exception{
		throw new Problem(
			"gui",
			this.getName(),
			method,
			ErrorType.COMPOSITE_GUI_NULL_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NULL_LABEL, this.getName() )
		);
	}
	
	public Gui gui(String name) throws Exception {
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

	protected Map<String, Gui> getChildMap() {
		return children;
	}

	protected void setChildMap(Map<String, Gui> guiMap) {
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
	
	private GuiMultiElementProxy updateUiElementProxy(GuiMultiElementProxy proxy) {
		wrapProxy(proxy);
		return proxy;
	}
	
	@Override
	public GuiElementProxy elementWithId(String id) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithId(id));
	}

	@Override
	public GuiElementProxy elementWithName(String name) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithName(name));
	}

	@Override
	public GuiElementProxy elementWithClass(String klass) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithClass(klass));
	}

	@Override
	public GuiElementProxy elementWithCss(String cssSelector) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithCss(cssSelector));
	}

	@Override
	public GuiElementProxy elementWithTagName(String tag) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithTagName(tag));
	}
	
	@Override
	public GuiElementProxy elementWithLinkText(String text) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithLinkText(text));
	}

	@Override
	public GuiElementProxy elementWithPartialLinkText(String textContent) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithPartialLinkText(textContent));
	}

	@Override
	public GuiElementProxy elementWithXPath(String xpath) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithXPath(xpath));
	}

	@Override
	public GuiElementProxy elementWithXText(String text) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithXText(text));
	}

	@Override
	public GuiElementProxy elementWithXPartialText(String textContent) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithXPartialText(textContent));
	}

	@Override
	public GuiElementProxy elementWithXValue(String value) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithXValue(value));
	}
	
	@Override
	public GuiElementProxy elementWithXTitle(String value) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithXTitle(value));
	}

	@Override
	public GuiElementProxy elementWithXImageSource(String path) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementWithXImageSource(path));
	}

	@Override
	public GuiElementProxy elementOfXType(GuiElementType type) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementOfXType(type));
	}

	@Override
	public GuiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		return updateGuiElementProxy(this.getAutomator().elementBasedOnImage(imagePath));
	}
	
	@Override
	public GuiMultiElementProxy elementsWithId(String id) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithId(id));
	}

	@Override
	public GuiMultiElementProxy elementsWithName(String name) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithName(name));
	}

	@Override
	public GuiMultiElementProxy elementsWithClass(String klass) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithClass(klass));
	}

	@Override
	public GuiMultiElementProxy elementsWithCss(String cssSelector) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithCss(cssSelector));
	}

	@Override
	public GuiMultiElementProxy elementsWithTagName(String tag) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithTagName(tag));
	}
	
	@Override
	public GuiMultiElementProxy elementsWithLinkText(String text) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithLinkText(text));
	}

	@Override
	public GuiMultiElementProxy elementsWithPartialLinkText(String textContent) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithPartialLinkText(textContent));
	}

	@Override
	public GuiMultiElementProxy elementsWithXPath(String xpath) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXPath(xpath));
	}

	@Override
	public GuiMultiElementProxy elementsWithXText(String text) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXText(text));
	}

	@Override
	public GuiMultiElementProxy elementsWithXPartialText(String textContent) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXPartialText(textContent));
	}

	@Override
	public GuiMultiElementProxy elementsWithXValue(String value) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXValue(value));
	}
	
	@Override
	public GuiMultiElementProxy elementsWithXTitle(String value) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXTitle(value));
	}

	@Override
	public GuiMultiElementProxy elementsWithXImageSource(String path) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsWithXImageSource(path));
	}

	@Override
	public GuiMultiElementProxy elementsOfXType(GuiElementType type) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsOfXType(type));
	}

	@Override
	public GuiMultiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		return updateUiElementProxy(this.getAutomator().elementsBasedOnImage(imagePath));
	}

	@Override
	public GuiDriverEngine getGuiDriverEngineName() throws Exception {
		return this.getAutomator().getGuiDriverEngineName();
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
