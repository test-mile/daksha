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

import daksha.Daksha;
import daksha.UiAutomatorSingleton;
import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.UiAutomatorPropertyType;
import daksha.core.leaping.enums.UiDriverEngine;
import daksha.core.leaping.exceptions.IgnoreElementException;
import daksha.core.leaping.identifier.DefaultElementMetaData;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.generator.PageDefLoaderFactory;
import daksha.tpi.leaping.loader.PageDefLoader;
import daksha.tpi.sysauto.utils.DataUtils;
import daksha.core.leaping.loader.PageDefRepository;
import daksha.core.leaping.loader.PageDefinition;

public class BasePage implements Page{
	private Logger logger = Logger.getRootLogger();
	private GuiAutomator<?,?> automator = null;
	private PageDefinition pageDef = null;
	private Map<String, GuiElement> uiElementMap = new HashMap<String, GuiElement>();
	private UiAutomationContext context = null;
	private String label;
	private Page parent = null;
	private ElementLoaderType loaderType = null;
	private String imagesDirectory =  null;
	private String name = null;
	
	public BasePage(
			String name,
			GuiAutomator<?,?> automator,
			String mapPath) throws Exception{
		populateSinglePage(name, automator);
		loadPageDef(mapPath);
	}
	
	public BasePage(
			Page parent,
			String uiLabel, 
			GuiAutomator<?,?> automator, 
			String mapPath) throws Exception {
		populateSinglePage(name, automator);
		populteCompositePage(parent);
		loadPageDef(mapPath);
	}
	
	private void populateSinglePage(String name, GuiAutomator<?,?> automator) throws Exception {
		this.setName(name);
		this.setLabel(name);
		this.setContext(automator.getContext());
		this.setElementLoaderType(ElementLoaderType.PAGE);
		this.setGuiAutomator(automator);	
	}
	
	private void populteCompositePage(Page parent) {
		this.setParent(parent);
		this.setName(parent.getName() + "." + this.getLabel());
		this.setElementLoaderType(ElementLoaderType.COMPOSITE_PAGE);		
	}
	
	private void loadPageDef(String mapPath) throws Exception {
		if (!PageDefRepository.INSTANCE.hasPageDef(this.getLabel())) {
			this.pageDef = PageDefRepository.INSTANCE.loadPageDef(this.getLabel(), PageDefLoaderFactory.getFileMapper(mapPath));
		}
		
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
	public UiAutomationContext getContext() throws Exception {
		return context;
	}

	private void setContext(UiAutomationContext context) throws Exception {
		this.context = context;
	}

	@Override
	public Page getParent() {
		return parent;
	}

	private void setParent(Page parent) {
		this.parent = parent;
	}

	@Override
	public GuiAutomator<?,?> getGuiAutomator() throws Exception {
		return this.automator;
	}

	private void setGuiAutomator(GuiAutomator<?,?> automator) throws Exception {
		if (automator != null){
			this.automator = automator;
		} else {
			throwNullAutomatorException("setAutomator");
		}
	}

	public GuiElementProxy getElement(String elementName) throws Exception {
		if (elementName == null){
			return (GuiElementProxy) throwNullElementException("element", elementName);
		} else if (!uiElementMap.containsKey(elementName)) {
			return (GuiElementProxy) throwUndefinedElementException("element", elementName);		
		} else {
			GuiElementProxy proxy = this.getGuiAutomator().getIdentifier().createProxy(this, this.pageDef.getMetaData(elementName));
			return proxy;
		}
	}
	
	public GuiElementProxy element(String name) throws Exception {
		return getElement(name);
	}
	
	@Override
	public GuiElementProxy dropdown(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiGuiElementProxy elements(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
				String.format(
						Daksha.problem.PAGE_NULL_AUTOMATOR,
						Daksha.getAutomationContextName(this.getContext())
						)
				);
	}

	public Object throwUndefinedElementException(String methodName, String elementName) throws Exception {
		return throwDefaultUiException(
				methodName,
				Daksha.problem.PAGE_UNDEFINED_ELEMENT,
				String.format(
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
				String.format(
						Daksha.problem.UI_NULL_ELEMENT,
						DataUtils.toTitleCase(this.getContext().toString())
						//						Batteries.toTitleCase(getDeviceType().toString()),
						//						Batteries.toTitleCase(getAutomationType().toString())
						)
				);
	}

	private GuiElementProxy wrapAutomatorElementProxy(GuiElementProxy proxy) {
		proxy.setPage(this);
		proxy.setLoaderType(this.getElementLoaderType());
		return proxy;
	}
	
	@Override
	public GuiElementProxy elementWithId(String id) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithId(id));
	}

	@Override
	public GuiElementProxy elementWithName(String name) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithName(name));
	}

	@Override
	public GuiElementProxy elementWithClass(String klass) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithClass(klass));
	}

	@Override
	public GuiElementProxy elementWithCss(String cssSelector) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithCss(cssSelector));
	}

	@Override
	public GuiElementProxy elementWithLinkText(String text) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithLinkText(text));
	}

	@Override
	public GuiElementProxy elementWithPartialLinkText(String textContent) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithPartialLinkText(textContent));
	}

	@Override
	public GuiElementProxy elementWithXPath(String xpath) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithXPath(xpath));
	}

	@Override
	public GuiElementProxy elementWithXText(String text) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithXText(text));
	}

	@Override
	public GuiElementProxy elementWithXPartialText(String textContent) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithXPartialText(textContent));
	}

	@Override
	public GuiElementProxy elementWithXValue(String value) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithXValue(value));
	}

	@Override
	public GuiElementProxy elementWithXImageSource(String path) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementWithXImageSource(path));
	}

	@Override
	public GuiElementProxy elementOfXType(GuiElementType type) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementOfXType(type));
	}

	@Override
	public GuiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		return wrapAutomatorElementProxy(this.getGuiAutomator().elementBasedOnImage(imagePath));
	}

	@Override
	public UiDriverEngine getUiDriverEngineName() throws Exception {
		return this.getGuiAutomator().getUiDriverEngineName();
	}
	//
	@Override
	public Object getUiDriverEngine() throws Exception {
		return this.getGuiAutomator().getUiDriverEngine();
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

}
