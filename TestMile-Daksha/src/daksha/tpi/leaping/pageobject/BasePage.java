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
package daksha.tpi.leaping.pageobject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.Daksha;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.UiAutomatorPropertyType;
import daksha.core.leaping.enums.UiDriverEngine;
import daksha.core.leaping.exceptions.IgnoreElementException;
import daksha.core.leaping.factories.PageMapperFactory;
import daksha.core.leaping.interfaces.CentralPageDefMap;
import daksha.core.leaping.interfaces.UiElementIdentifier;
import daksha.core.leaping.lib.DefaultElementMetaData;
import daksha.core.leaping.lib.config.UiAutomatorSingleton;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.enums.UiElementType;
import daksha.tpi.leaping.interfaces.Page;
import daksha.tpi.leaping.interfaces.PageDefLoader;
import daksha.tpi.leaping.interfaces.GuiAutomator;
import daksha.tpi.leaping.interfaces.GuiElement;
import daksha.tpi.sysauto.utils.DataUtils;

public class BasePage implements Page{
	private Logger logger = Logger.getLogger(Batteries.getCentralLogName());
	private GuiAutomator automator = null;
	private Map<String, GuiElement> uiElementMap = new HashMap<String, GuiElement>();
	private UiAutomationContext context = null;
	private String label;
	private Page parent = null;
	private ElementLoaderType loaderType = null;
	private String imagesDirectory =  null;
	private String name = null;
	private CentralPageDefMap uiMap = null;
	
//	private Map<String, Object> childUiEntities = new  HashMap<String, Object>();
	
	public BasePage(){
	}
	
	public CentralPageDefMap getUiMap(){
		return uiMap;
	}
	
	public BasePage(String uiLabel) throws Exception{
		this();
		imagesDirectory = Batteries.value(UiAutomatorPropertyType.DIRECTORY_PROJECT_UI_IMAGES).asString();
		Random rn = new Random();
		java.util.Date date= new java.util.Date();
		long millis = date.getTime();
		this.name = String.format(
									"%s (OIC:%d.%d)",
									uiLabel, // This is entity_name.ui_label
									rn.nextInt(10000),
									millis
								);
		loadMap();
	}
	
	public BasePage(
			String uiLabel,
			GuiAutomator automator) throws Exception{
		this(uiLabel);
		this.setContext(automator.getContext());
		this.setElementLoaderType(ElementLoaderType.PAGE);
		automator.setElementLoaderType(ElementLoaderType.PAGE);
		this.setUiDriver(automator);
		this.setLabel(uiLabel);
	}
	
	public BasePage(
			GuiAutomator automator) throws Exception{
		this("Default Page", automator);
	}

	public BasePage(
			String uiLabel, 
			Page parent, 
			GuiAutomator automator) throws Exception {
		this(uiLabel, automator);
		this.setParent(parent);
		this.setName(parent.getName() + "." + this.getName());
		this.setElementLoaderType(ElementLoaderType.COMPOSITE_PAGE);
		automator.setElementLoaderType(ElementLoaderType.COMPOSITE_PAGE);
	}
	
	public BasePage(
			String uiLabel, 
			Page parent,
			GuiAutomator automator, 
			String mapPath) throws Exception {
		this(uiLabel, parent, automator);
		this.populate(PageMapperFactory.getFileMapper(mapPath));
	}
	
	protected void loadMap(){
		this.uiMap = UiAutomatorSingleton.INSTANCE.getCentralMap();		
	}
	
//	public String getImagesDirectory(){
//		return this.imagesDirectory;
//	}
//	
//	protected void setImagesDirectory(String imageDirectory){
//		this.imagesDirectory = imageDirectory;
//	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public ElementLoaderType getElementLoaderType() {
		return this.loaderType;
	}

	public void setElementLoaderType(ElementLoaderType type) {
		this.loaderType = type;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public UiAutomationContext getContext() throws Exception {
		return context;
	}

	@Override
	public void setContext(UiAutomationContext context) throws Exception {
		this.context = context;
	}

	@Override
	public Page getParent() {
		return parent;
	}

	@Override
	public void setParent(Page entity) {
		this.parent = entity;
	}

	@Override
	public GuiAutomator getUiDriver() throws Exception {
		return this.automator;
	}

	@Override
	public void setUiDriver(GuiAutomator automator) throws Exception {
		if (automator != null){
			this.automator = automator;
		} else {
			throwNullAutomatorException("setAutomator");
		}
	}

	protected void processUiProperties(String elementName, Map<String, String> properties) throws Exception {
//		if (this.getParent() != null){
//			this.processElementProperties(elementName, properties);
//			if (properties != null){
//				this.processElementPropertiesForLabel(this.getLabel(), elementName, properties);
//			}
//		}
		
		this.processElementProperties(elementName, properties);
//		if (properties != null){
//			this.processElementPropertiesForLabel(this.getLabel(), elementName, properties);
//		}
	}
	
	@Override
	public void processElementProperties(String elementName, Map<String, String> properties) throws Exception  {
		// TODO Auto-generated method stub
		
	}

	public void processElementPropertiesForLabel(String uiName, String elementName, Map<String, String> properties) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public GuiElement element(String name) throws Exception {
		return getElement(name);
	}

	public void populate(PageDefLoader uiMapper) throws Exception {
		getUiMap().populateRawPageDef(this.getName(), uiMapper);
		Map<String, HashMap<String,String>> rawMap = getUiMap().getRawPageDef(this.getName());
		for (String uiElementName: rawMap.keySet()){
			Map<String,String> elemMap = rawMap.get(uiElementName);
			try{
				processUiProperties(uiElementName, elemMap);
			} catch (IgnoreElementException e){
				continue;
			}
			if (elemMap != null){
				addElement(uiElementName, elemMap);
			}
		}
	}
	
	public void populate(String mapPath) throws Exception{
		this.populate(PageMapperFactory.getFileMapper(mapPath));
	}

	public GuiElement getElement(String elementName) throws Exception {
		if (elementName == null){
			return (GuiElement) throwNullElementException("element", elementName);
		} else if (!uiElementMap.containsKey(elementName)) {
			return (GuiElement) throwUndefinedElementException("element", elementName);		
		} else {
			return uiElementMap.get(elementName);
		}
	}

	private GuiElement registerElement(String elementName, UiElementIdentifier elementMap) throws Exception {
		if (Daksha.displayPageElementProcessing){
			logger.debug("Declaring element");
		}
		GuiElement uiElement = getUiDriver().declareElement(elementMap);
		if (Daksha.displayPageElementProcessing){
		logger.debug("Set element name as " + elementName);
		}
		uiElement.setName(elementName);
		if (this.getParent() != null){
			if (Daksha.displayPageElementProcessing){
				logger.debug("Set element entity as " + this.getParent().getName());
			}
			uiElement.setCompositePageName(this.getParent().getName());
		}
		if (Daksha.displayPageElementProcessing){
			logger.debug("Verifying entity: " + uiElement.getCompositePageName());
		}
		uiElementMap.put(elementName, uiElement);
		return uiElement;
	}

	public void addElement(String elementName, Map<String, String> elementMap) throws Exception {
		UiElementIdentifier elementMetaData = new DefaultElementMetaData(elementMap);
		elementMetaData.process(this.getContext());
		if (elementMetaData.isRelevantForPage()){
			if (this.isAutomatorPresent()){
				GuiElement element = this.registerElement(elementName, elementMetaData);
				element.setPageLabel(this.getLabel());
				element.setMetaData(elementMetaData);
			}			
		}
	}

	@Override
	public boolean isAutomatorPresent() throws Exception {
		return this.automator != null;
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
				Daksha.problem.PAGE_NULL_AUTOMATOR,
				Batteries.getProblemText(
						Daksha.problem.PAGE_NULL_AUTOMATOR,
						Daksha.getAutomationContextName(this.getContext())
						)
				);
	}

	public Object throwUndefinedElementException(String methodName, String elementName) throws Exception {
		return throwDefaultUiException(
				methodName,
				Daksha.problem.PAGE_UNDEFINED_ELEMENT,
				Batteries.getProblemText(
						Daksha.problem.PAGE_UNDEFINED_ELEMENT,
						elementName,
						DataUtils.toTitleCase(this.getContext().toString())
						//						Batteries.toTitleCase(getDeviceType().toString()),
						//						Batteries.toTitleCase(getAutomationType().toString())
						)
				);
	}

	public Object throwNullElementException(String methodName, String elementName) throws Exception {
		return throwDefaultUiException(
				methodName,
				Daksha.problem.UI_NULL_ELEMENT,
				Batteries.getProblemText(
						Daksha.problem.UI_NULL_ELEMENT,
						DataUtils.toTitleCase(this.getContext().toString())
						//						Batteries.toTitleCase(getDeviceType().toString()),
						//						Batteries.toTitleCase(getAutomationType().toString())
						)
				);
	}
	
	@Override
	public Object getUnderlyingEngine() throws Exception {
		return this.getUiDriver().getUnderlyingEngine();
	}

	@Override
	public GuiElement elementWithId(String id) throws Exception {
		return this.getUiDriver().elementWithId(id);
	}

	@Override
	public GuiElement elementWithName(String name) throws Exception {
		return this.getUiDriver().elementWithName(name);
	}

	@Override
	public GuiElement elementWithClass(String klass) throws Exception {
		return this.getUiDriver().elementWithClass(klass);
	}

	@Override
	public GuiElement elementWithCss(String cssSelector) throws Exception {
		return this.getUiDriver().elementWithCss(cssSelector);
	}

	@Override
	public GuiElement elementWithLinkText(String text) throws Exception {
		return this.getUiDriver().elementWithLinkText(text);
	}

	@Override
	public GuiElement elementWithPartialLinkText(String textContent) throws Exception {
		return this.getUiDriver().elementWithPartialLinkText(textContent);
	}

	@Override
	public GuiElement elementWithXPath(String xpath) throws Exception {
		return this.getUiDriver().elementWithXPath(xpath);
	}

	@Override
	public GuiElement elementWithXText(String text) throws Exception {
		return this.getUiDriver().elementWithXText(text);
	}

	@Override
	public GuiElement elementWithXPartialText(String textContent) throws Exception {
		return this.getUiDriver().elementWithXPartialText(textContent);
	}

	@Override
	public GuiElement elementWithXValue(String value) throws Exception {
		return this.getUiDriver().elementWithXValue(value);
	}

	@Override
	public GuiElement elementWithXImageSource(String path) throws Exception {
		return this.getUiDriver().elementWithXImageSource(path);
	}

	@Override
	public GuiElement elementOfXType(UiElementType type) throws Exception {
		return this.getUiDriver().elementOfXType(type);
	}

	@Override
	public GuiElement elementBasedOnImage(String imagePath) throws Exception {
		return this.getUiDriver().elementBasedOnImage(imagePath);
	}

	@Override
	public UiDriverEngine getUiDriverEngineName() throws Exception {
		return this.getUiDriver().getUiDriverEngineName();
	}
	//
	@Override
	public Object getUiDriverEngine() throws Exception {
		return this.getUiDriver().getUiDriverEngine();
	}
	//
	@Override
	public File takeScreenshot() throws Exception {
		return this.getUiDriver().takeScreenshot();
	}
	//
	@Override
	public void setAppTitle(String appTitle) throws Exception {
		this.getUiDriver().setAppTitle(appTitle);
	}
	//
	@Override
	public String getAppTitle() throws Exception {
		return this.getUiDriver().getAppTitle();
	}
	//
	@Override
	public void focusOnApp() throws Exception {
		this.getUiDriver().focusOnApp();
	}
	//
	
	//
	@Override
	public void confirmAlertIfPresent() throws Exception {
		this.getUiDriver().confirmAlertIfPresent();
	}
	//
	@Override
	public void close() throws Exception {
		this.getUiDriver().close();
	}
	//
	@Override
	public String getCurrentWindow() throws Exception {
		return this.getUiDriver().getCurrentWindow();
	}
	//
	@Override
	public void switchToNewWindow() throws Exception {
		this.getUiDriver().switchToNewWindow();
	}
	//
	@Override
	public void switchToWindow(String windowHandle) throws Exception {
		this.getUiDriver().switchToWindow(windowHandle);
	}
	//
	@Override
	public void closeCurrentWindow() throws Exception {
		this.getUiDriver().closeCurrentWindow();
	}
	//
	@Override
	public void goTo(String url) throws Exception {
		this.getUiDriver().goTo(url);
	}
	//
	@Override
	public void refresh() throws Exception {
		this.getUiDriver().refresh();
	}
	//
	@Override
	public void back() throws Exception {
		this.getUiDriver().back();
	}
	//
	@Override
	public void forward() throws Exception {
		this.getUiDriver().forward();
	}
	//
	@Override
	public void waitForBody() throws Exception {
		this.getUiDriver().waitForBody();
	}
	//
	@Override
	public void switchToFrame(int index) throws Exception {
		this.getUiDriver().switchToFrame(index);
	}
	//
	@Override
	public void switchToFrame(String name) throws Exception {
		this.getUiDriver().switchToFrame(name);
	}
	//
	@Override
	public void switchToDefaultFrame() throws Exception {
		this.getUiDriver().switchToDefaultFrame();
	}
	//
	@Override
	public boolean areImagesSimilar(File leftImagePath, File rightImage, Double minScore) throws Exception {
		return this.getUiDriver().areImagesSimilar(leftImagePath, rightImage, minScore);
	}
	//
	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage) throws Exception {
		return this.getUiDriver().areImagesSimilar(leftImagePath, rightImage);
	}
	//
	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage, Double minScore) throws Exception {
		return this.getUiDriver().areImagesSimilar(leftImagePath, rightImage, minScore);
	}
	//
	@Override
	public boolean areImagesSimilar(String leftImagePath, String rightImagePath) throws Exception {
		return this.getUiDriver().areImagesSimilar(leftImagePath, rightImagePath);
	}
	//
	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage) throws Exception {
		return this.getUiDriver().areImagesSimilar(leftImage, rightImage);
	}
	
	@Override
	public void switchToWebContext() throws Exception {
		this.getUiDriver().switchToWebContext();
	}

	@Override
	public void switchToNativeContext() throws Exception {
		this.getUiDriver().switchToNativeContext();
	}

}
