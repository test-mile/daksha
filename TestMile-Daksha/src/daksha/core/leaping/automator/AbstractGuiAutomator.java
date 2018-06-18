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
package daksha.core.leaping.automator;

import java.io.File;
import java.util.Map;

import daksha.ErrorType;
import daksha.core.batteries.config.TestContext;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.OSType;
import daksha.core.leaping.enums.UiDriverEngine;
import daksha.core.leaping.identifier.Identifier;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.enums.GuiAutomationContext;
import daksha.tpi.leaping.enums.GuiElementType;

public abstract class AbstractGuiAutomator<D,E> implements GuiAutomator<D,E>{
	private TestContext testContext = null;
	private OSType os = null;
	UiDriverEngine uiAutomatorEngineName = null;
	private GuiAutomationContext automatorContext = null;
	String appTitle = null;
	private ElementLoaderType loaderType = ElementLoaderType.AUTOMATOR;
	private Identifier<D,E> identifier = null;
	
	public AbstractGuiAutomator(TestContext testContext) {
		this.setUiTestEngineName(UiDriverEngine.DEFAULT);
		this.testContext = testContext;
	}
	
	public AbstractGuiAutomator(TestContext testContext, UiDriverEngine name, GuiAutomationContext automatorContext){
		this(testContext);
		this.setUiTestEngineName(name);
		this.setAutomatorContext(automatorContext);
	}
	
	public AbstractGuiAutomator(TestContext testContext, UiDriverEngine name, GuiAutomationContext automatorContext, ElementLoaderType loaderType){
		this(testContext, name, automatorContext);
		this.setElementLoaderType(loaderType);
	}
	
	public String getName() {
		return uiAutomatorEngineName.toString();
	}
	
	protected void setIdentifier(Identifier<D,E> identifier) {
		this.identifier = identifier;
	}
	
	public Identifier<D,E> getIdentifier(){
		return this.identifier;
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
	
//	public void throwUnsupportedMultipleIndentifiersException(String componentName, String methodName, String mapString) throws Exception{
//		throwGenericUiAutomatorException(
//				componentName,
//				methodName, 
//				UiAutomator.problem.UNSUPPORTED_MULTIPLE_IDENTIFIERS,
//				Unitee.getProblemText(
//						UiAutomator.problem.UNSUPPORTED_MULTIPLE_IDENTIFIERS,
//						mapString
//				)
//		);
//	}
	
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
		return this.identifier.elementWithId(id);
	}

	@Override
	public GuiElementProxy elementWithName(String name) throws Exception {
		return this.identifier.elementWithName(name);
	}

	@Override
	public GuiElementProxy elementWithClass(String klass) throws Exception {
		return this.identifier.elementWithClass(klass);
	}

	@Override
	public GuiElementProxy elementWithCss(String cssSelector) throws Exception {
		return this.identifier.elementWithCss(cssSelector);
	}

	@Override
	public GuiElementProxy elementWithLinkText(String text) throws Exception {
		return this.identifier.elementWithLinkText(text);
	}

	@Override
	public GuiElementProxy elementWithPartialLinkText(String textContent) throws Exception {
		return this.identifier.elementWithPartialLinkText(textContent);
	}

	@Override
	public GuiElementProxy elementWithXPath(String xpath) throws Exception {
		return this.identifier.elementWithXPath(xpath);
	}

	@Override
	public GuiElementProxy elementWithXText(String text) throws Exception {
		return this.identifier.elementWithXText(text);
	}

	@Override
	public GuiElementProxy elementWithXPartialText(String textContent) throws Exception {
		return this.identifier.elementWithXPartialText(textContent);
	}

	@Override
	public GuiElementProxy elementWithXValue(String value) throws Exception {
		return this.identifier.elementWithXValue(value);
	}

	@Override
	public GuiElementProxy elementWithXImageSource(String path) throws Exception {
		return this.identifier.elementWithXImageSource(path);
	}

	@Override
	public GuiElementProxy elementOfXType(GuiElementType type) throws Exception {
		return this.identifier.elementOfXType(type);
	}

	@Override
	public GuiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		return this.identifier.elementBasedOnImage(imagePath);
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
	public boolean areImagesSimilar(String leftImagePath, File rightImage) throws Exception {
		return (boolean) throwUnsupportedActionException("areImagesSimilar");}

	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage, Double minScore) throws Exception {
		return (boolean) throwUnsupportedActionException("areImagesSimilar");}

	@Override
	public boolean areImagesSimilar(String leftImagePath, String rightImagePath) throws Exception {
		return (boolean) throwUnsupportedActionException("areImagesSimilar");}

	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage) throws Exception {
		return (boolean) throwUnsupportedActionException("areImagesSimilar");}

	@Override
	public void switchToWebContext() throws Exception{
		throwUnsupportedActionException("switchToWebContext");
	}
	
	@Override
	public void switchToNativeContext() throws Exception{
		throwUnsupportedActionException("switchToNativeContext");
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
