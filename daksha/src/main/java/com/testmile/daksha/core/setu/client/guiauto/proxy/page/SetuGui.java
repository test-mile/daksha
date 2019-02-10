package com.testmile.daksha.core.setu.client.guiauto.proxy.page;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.testmile.daksha.core.problem.ErrorType;
import com.testmile.daksha.core.problem.Problem;
import com.testmile.daksha.core.setu.client.guiauto.SetuGuiAutoClient;
import com.testmile.daksha.core.setu.client.guiauto.enums.MatchType;
import com.testmile.daksha.core.setu.client.guiauto.proxy.common.GuiElementFindMetaData;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiElement;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiMultiElement;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.guiauto.enums.GuiElementType;
import com.testmile.daksha.tpi.guiauto.gui.SetuClientGui;
import com.testmile.daksha.tpi.sysauto.utils.DataUtils;
import com.testmile.trishanku.Trishanku;
import com.testmile.trishanku.tpi.guiauto.enums.GuiAutomatorName;
import com.testmile.trishanku.tpi.guiauto.enums.GuiElementLoaderType;
import com.testmile.trishanku.tpi.guiauto.enums.LocatorType;

public class SetuGui {
	private Logger logger = Trishanku.getLogger();
	private GuiMetaData metaData;
	private String defPath;
	private TestContext testContext = null;
	private SetuClientGuiAutomator automator = null;
	private SetuClientGui parent = null;
	private Map<String, SetuClientGui> children = new HashMap<String, SetuClientGui>();
	
	public SetuGui(
			String label,
			SetuClientGuiAutomator automator,
			String defPath) throws Exception{
		populateGuiInfo(label, automator);
		this.defPath = defPath;
		registerWithSetu();
	}
	
	public SetuGui(SetuClientGuiAutomator automator) throws Exception{
		populateGuiInfo(this.getClass().getSimpleName(), automator);
		registerWithSetu();
	}
	
	public SetuGui(String label, SetuClientGuiAutomator automator) throws Exception{
		populateGuiInfo(label, automator);
		registerWithSetu();
	}
	
	public SetuGui(
			SetuClientGui parent,
			String label, 
			SetuClientGuiAutomator automator, 
			String defPath) throws Exception {
		populateGuiInfo(label, automator);
		populateParentInfo(parent);
		this.defPath = defPath;
		registerWithSetu();
	}
	
	private GuiMetaData getGuiMetaData() {
		return this.metaData;
	}
	
	public void registerWithSetu() {
		metaData.setId(SetuGuiAutoClient.registerGui(this));
	}
	
	public String getDefPath() throws Exception{
		return this.defPath;
	}
	
	protected void load() throws Exception{
		// Child classes can override this and write any necessary loading instructions.
	}
	
	public void validateLoaded() throws Throwable{
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
	
	private void populateGuiInfo(String name, SetuClientGuiAutomator automator) throws Exception {
		metaData = new GuiMetaData();
		metaData.setName(name);
		metaData.setLabel(name);
		metaData.setTestContextId("TODO");
		this.testContext = automator.getTestContext();
		metaData.setAutomationContext(automator.getAutomatorContext());
		metaData.setElementLoaderType(GuiElementLoaderType.GUI);
		this.setAutomator(automator);	
	}
	
	private void populateParentInfo(SetuClientGui parent) {
		metaData.setParentContextId("TO DO");
		this.setParent(parent);
		metaData.setName(parent.getName() + "." + this.getLabel());
		metaData.setElementLoaderType(GuiElementLoaderType.COMPOSITE_GUI);		
	}
	
	public TestContext getTestContext() {
		return this.testContext;
	}

	
	public String getName() {
		return this.metaData.getName();
	}

	public GuiElementLoaderType getElementLoaderType() {
		return this.metaData.getElementLoaderType();
	}

	public String getLabel() {
		return this.metaData.getLabel();
	}
	
	public GuiAutomationContext getAutomatorContext() throws Exception {
		return this.metaData.getAutomationContext();
	}

	public SetuClientGui getParent() {
		return parent;
	}

	private void setParent(SetuClientGui parent) {
		this.parent = parent;
	}
	
	public SetuClientGuiAutomator getAutomator() throws Exception {
		return this.automator;
	}

	private void setAutomator(SetuClientGuiAutomator automator) throws Exception {
		if (automator != null){
			this.automator = automator;
		} else {
			throwNullAutomatorException("setAutomator");
		}
	}
	
	public void addChild(String label, SetuClientGui gui) {
		getChildMap().put(label.toLowerCase(), gui);
	}
	
	protected SetuClientGui throwUndefinedUiException(String method, String label) throws Exception{
		throw new Problem(
			"gui",
			this.getName(),
			method,
			ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL, label, this.getName())
		);
	}
	
	protected SetuClientGui throwNullUiException(String method) throws Exception{
		throw new Problem(
			"gui",
			this.getName(),
			method,
			ErrorType.COMPOSITE_GUI_NULL_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NULL_LABEL, this.getName() )
		);
	}
	
	public SetuClientGui gui(String name) throws Exception {
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

	protected Map<String, SetuClientGui> getChildMap() {
		return children;
	}

	protected void setChildMap(Map<String, SetuClientGui> guiMap) {
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

	private SetuClientGuiElement updateElement(SetuClientGuiElement element) {
		element.setGui(this);
		element.setLoaderType(this.getElementLoaderType());		
		return element;
	}
	
	private SetuClientGuiMultiElement updateElements(SetuClientGuiMultiElement element) throws Exception {
		for (SetuClientGuiElement instance: element.getAllInstances()) {
			this.updateElement(instance);
		}	
		return element;
	}
	
	private GuiElementFindMetaData buildElementFinderMetaData(LocatorType locator, String locatorValue) {
		GuiElementFindMetaData finderMetaData = new GuiElementFindMetaData();
		finderMetaData.setElementLoaderType(this.getElementLoaderType());
		finderMetaData.setFinderId(this.metaData.getId());
		finderMetaData.setLocatorType(locator);
		finderMetaData.setLocatorValue(locatorValue);
		return finderMetaData;
	}
	
	private SetuClientGuiElement element(LocatorType locator, String locatorValue) {
		GuiElementFindMetaData finderMetaData = this.buildElementFinderMetaData(locator, locatorValue);
		finderMetaData.setMatchType(MatchType.FIRST);
		return this.updateElement(SetuGuiAutoClient.identify(finderMetaData));
	}
	
	private SetuClientGuiMultiElement elements(LocatorType locator, String locatorValue) throws Exception {
		GuiElementFindMetaData finderMetaData = this.buildElementFinderMetaData(locator, locatorValue);
		finderMetaData.setMatchType(MatchType.ALL);
		return this.updateElements(SetuGuiAutoClient.identifyAll(finderMetaData));
	}
	
	public SetuClientGuiElement element(String name) throws Exception {
		return this.element(LocatorType.ASSIGNED_NAME, name);
	}
	
	public SetuClientGuiMultiElement elements(String name) throws Exception {
		return this.elements(LocatorType.ASSIGNED_NAME, name);
	}

	public SetuClientGuiElement elementWithId(String id) throws Exception {
		return this.element(LocatorType.ID, id);
	}

	
	public SetuClientGuiElement elementWithName(String name) throws Exception {
		return this.element(LocatorType.NAME, name);
	}

	
	public SetuClientGuiElement elementWithClass(String klass) throws Exception {
		return this.element(LocatorType.CLASS, klass);
	}

	
	public SetuClientGuiElement elementWithCss(String cssSelector) throws Exception {
		return this.element(LocatorType.CSS, cssSelector);
	}

	
	public SetuClientGuiElement elementWithTagName(String tag) throws Exception {
		return this.element(LocatorType.TAG, tag);
	}
	
	
	public SetuClientGuiElement elementWithLinkText(String text) throws Exception {
		return this.element(LocatorType.LINK_TEXT, text);
	}

	
	public SetuClientGuiElement elementWithPartialLinkText(String textContent) throws Exception {
		return this.element(LocatorType.X_PARTIAL_TEXT, textContent);
	}

	
	public SetuClientGuiElement elementWithXPath(String xpath) throws Exception {
		return this.element(LocatorType.XPATH, xpath);
	}

	
	public SetuClientGuiElement elementWithXText(String text) throws Exception {
		return this.element(LocatorType.X_TEXT, text);
	}

	
	public SetuClientGuiElement elementWithXPartialText(String textContent) throws Exception {
		return this.element(LocatorType.X_PARTIAL_TEXT, textContent);
	}

	
	public SetuClientGuiElement elementWithXValue(String value) throws Exception {
		return this.element(LocatorType.X_VALUE, value);
	}
	
	
	public SetuClientGuiElement elementWithXTitle(String title) throws Exception {
		return this.element(LocatorType.X_TITLE, title);
	}

	
	public SetuClientGuiElement elementWithXImageSource(String path) throws Exception {
		return this.element(LocatorType.X_IMAGE_SRC, path);
	}

	
	public SetuClientGuiElement elementOfXType(GuiElementType type) throws Exception {
		return this.element(LocatorType.X_TYPE, type.toString());
	}
	
	public SetuClientGuiElement elementBasedOnImage(String imagePath) throws Exception {
		return this.element(LocatorType.IMAGE, imagePath);
	}
	
	
	public SetuClientGuiMultiElement elementsWithId(String id) throws Exception {
		return this.elements(LocatorType.ID, id);
	}

	
	public SetuClientGuiMultiElement elementsWithName(String name) throws Exception {
		return this.elements(LocatorType.NAME, name);
	}

	
	public SetuClientGuiMultiElement elementsWithClass(String klass) throws Exception {
		return this.elements(LocatorType.CLASS, klass);
	}

	
	public SetuClientGuiMultiElement elementsWithCss(String cssSelector) throws Exception {
		return this.elements(LocatorType.CSS, cssSelector);
	}

	
	public SetuClientGuiMultiElement elementsWithTagName(String tag) throws Exception {
		return this.elements(LocatorType.TAG, tag);
	}
	
	
	public SetuClientGuiMultiElement elementsWithLinkText(String text) throws Exception {
		return this.elements(LocatorType.LINK_TEXT, text);
	}

	
	public SetuClientGuiMultiElement elementsWithPartialLinkText(String textContent) throws Exception {
		return this.elements(LocatorType.PARTIAL_LINK_TEXT, textContent);
	}

	
	public SetuClientGuiMultiElement elementsWithXPath(String xpath) throws Exception {
		return this.elements(LocatorType.XPATH, xpath);
	}

	
	public SetuClientGuiMultiElement elementsWithXText(String text) throws Exception {
		return this.elements(LocatorType.X_TEXT, text);
	}

	
	public SetuClientGuiMultiElement elementsWithXPartialText(String textContent) throws Exception {
		return this.elements(LocatorType.X_PARTIAL_TEXT, textContent);
	}

	
	public SetuClientGuiMultiElement elementsWithXValue(String value) throws Exception {
		return this.elements(LocatorType.X_VALUE, value);
	}
	
	
	public SetuClientGuiMultiElement elementsWithXTitle(String title) throws Exception {
		return this.elements(LocatorType.X_TITLE, title);
	}

	
	public SetuClientGuiMultiElement elementsWithXImageSource(String path) throws Exception {
		return this.elements(LocatorType.X_IMAGE_SRC, path);
	}

	
	public SetuClientGuiMultiElement elementsOfXType(GuiElementType type) throws Exception {
		return this.elements(LocatorType.X_TYPE, type.toString());
	}

	
	public SetuClientGuiMultiElement elementsBasedOnImage(String imagePath) throws Exception {
		return this.elements(LocatorType.IMAGE, imagePath);
	}

	
	public GuiAutomatorName getGuiDriverEngineName() throws Exception {
		return this.getAutomator().getGuiDriverEngineName();
	}
	
	public File takeScreenshot() throws Exception {
		return this.getAutomator().takeScreenshot();
	}
	
	public void setAppTitle(String appTitle) throws Exception {
		this.getAutomator().setAppTitle(appTitle);
	}
	
	public String getAppTitle() throws Exception {
		return this.getAutomator().getAppTitle();
	}
	
	public void focusOnApp() throws Exception {
		this.getAutomator().focusOnApp();
	}
	
	public void confirmAlertIfPresent() throws Exception {
		this.getAutomator().confirmAlertIfPresent();
	}
	
	public void close() throws Exception {
		this.getAutomator().close();
	}
	
	public String getCurrentWindow() throws Exception {
		return this.getAutomator().getCurrentWindow();
	}
	
	public void switchToNewWindow() throws Exception {
		this.getAutomator().switchToNewWindow();
	}
	
	public void switchToWindow(String windowHandle) throws Exception {
		this.getAutomator().switchToWindow(windowHandle);
	}
	
	public void closeCurrentWindow() throws Exception {
		this.getAutomator().closeCurrentWindow();
	}
	
	public void goTo(String url) throws Exception {
		this.getAutomator().goTo(url);
	}
	
	public void refresh() throws Exception {
		this.getAutomator().refresh();
	}
	
	public void back() throws Exception {
		this.getAutomator().back();
	}
	
	public void forward() throws Exception {
		this.getAutomator().forward();
	}
	
	public void waitForBody() throws Exception {
		this.getAutomator().waitForBody();
	}
	
	public void switchToFrameByIndex(int index) throws Exception {
		this.getAutomator().switchToFrameByIndex(index);
	}
	
	public void switchToFrameByName(String name) throws Exception {
		this.getAutomator().switchToFrameByName(name);
	}
	
	public void switchToDefaultFrame() throws Exception {
		this.getAutomator().switchToDefaultFrame();
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

	public void compareImageFiles(String absolutePath, String absolutePath2, Double minScore) {
		// TODO Auto-generated method stub
		
	}

	public void compareImageFiles(String leftImagePath, String rightImagePath) {
		// TODO Auto-generated method stub
		
	}

}
