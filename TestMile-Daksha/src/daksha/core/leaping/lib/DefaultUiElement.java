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
package daksha.core.leaping.lib;

import java.io.File;
import java.io.IOException;
import java.util.List;

import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.Daksha;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.interfaces.ManagedElement;
import daksha.core.leaping.interfaces.UiElementIdentifier;
import daksha.core.leaping.interfaces.UiElementProxy;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.enums.UiElementType;
import daksha.tpi.leaping.interfaces.GuiElement;

public class DefaultUiElement implements GuiElement{
	private UiElementType elementType = UiElementType.GENERIC;
	private UiElementProxy proxy = null;
	private UiElementIdentifier metaData = null;
	private String name = null;
	private String entityName = null;
	private Object element = null;
	private Object elements = null;
	private boolean compositeFlag = false;
	private String imagePath = null;
	private ElementLoaderType loaderType = null;
	private String uiLabel = null;
	private UiAutomationContext idType = null;

	public DefaultUiElement(UiElementIdentifier metaData) {
		this.metaData = metaData;
	}

	@Override
	public void setLoaderType(ElementLoaderType type) {
		this.loaderType = type;
	}

	@Override
	public ElementLoaderType getLoaderType() {
		return this.loaderType;
	}

	@Override
	public String getPageLabel() {
		return this.uiLabel;
	}

	@Override
	public void setPageLabel(String label) {
		this.uiLabel = label;
	}

	@Override
	public UiElementProxy getProxy() {
		return this.proxy;
	}

	public void setProxy(UiElementProxy proxy){
		this.proxy = proxy;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getCompositePageName(){
		return this.entityName;
	}

	public void setCompositePageName(String name){
		this.entityName = name;
	}

	public void setElement (Object element){
		this.element = element;
	}

	public Object getElement(){
		return this.element;
	}

	public void setElements (Object elements){
		this.elements = elements;
	}

	public Object getElements (){
		return this.elements;
	}

	public void setType(UiElementType type) {
		elementType = type;
	}

	public UiElementType getType() {
		return elementType;
	}

	@Override
	public void switchOnCompositeFlag() {
		this.compositeFlag = true;
	}

	@Override
	public void switchOffCompositeFlag() {
		this.compositeFlag = false;
	}

	public boolean isComposite() {
		return this.compositeFlag;
	}

	public void reset() throws Exception {
		this.element = null;
		this.elements = null;
	}

	public String property(String propName) {
		return metaData.get(propName);
	}

	public String getProperty(String propName) {
		return metaData.get(propName);
	}

	public UiElementIdentifier getMetaData() {
		return this.metaData;
	}

	public void setProperty(String propName, String value) {
		metaData.set(propName, value);
	}

	public void setMetaData(UiElementIdentifier map) {
		this.metaData = map;
	}

	private String takeScreenshotIfPossible() throws IOException{
		try{
			File path = this.getProxy().takeScreenshot();
			if (path != null){
				return path.getCanonicalPath();
			} else {
				return "NA";
			}
		} catch (Exception e){
			return "Not able to take snapshot";
		}
	}

	protected String getClassForLoaderType() throws Exception{
		String rValue = "";
		if(this.getLoaderType() == null){
			return "Element API";
		}
		switch(this.getLoaderType()){
		case AUTOMATOR: rValue = "Element API"; break;
		case PAGE: rValue = this.getPageLabel(); break;
		case COMPOSITE_PAGE: rValue = this.getCompositePageName() + "." + this.getPageLabel(); break;
		}
		return rValue;
	}

	protected String getElementNameFillerForException() throws Exception{
		String rValue = "";
		if(this.getLoaderType() == null){
			return "";
		}
		switch(this.getLoaderType()){
		case AUTOMATOR: rValue = ""; break;
		case PAGE:  rValue = String.format("name %s and ", this.getName()); break;
		case COMPOSITE_PAGE: rValue = String.format("name %s and ", this.getName()); break;
		}
		return rValue;
	}

	protected String getElementNameFillerForException(ManagedElement element) throws Exception{
		String rValue = "";
		if(element.getLoaderType() == null){
			return "";
		}
		switch(element.getLoaderType()){
		case AUTOMATOR: rValue =  ""; break;
		case PAGE: rValue = String.format("name %s and ", element.getName()); break;
		case COMPOSITE_PAGE: rValue = String.format("name %s and ", element.getName()); break;
		}
		return rValue;
	}

	protected Object throwElementException(
			Throwable e,
			String code,
			String action,
			String message
			) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
				getClassForLoaderType(),
				action,
				code,
				message,
				takeScreenshotIfPossible(),
				e
				);
	}

	private Object throwBasicElementMessageException(Throwable e, String code, String action, String filler) throws Exception{
		return throwElementException(
				e, 
				code,
				action,
				Batteries.getProblemText(
						code,
						this.getProxy().getAutomatorName(),
						filler,
						getElementNameFillerForException(),
						this.getMetaData().getAllProperties().toString()
						)
				);		
	}

	protected Object throwUnsupportedException(String action) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
				getClassForLoaderType(),
				action,
				Daksha.problem.ELEMENT_UNSUPPORTED_ACTION,
				Batteries.getProblemText(
						Daksha.problem.ELEMENT_UNSUPPORTED_ACTION,
						action,
						getElementNameFillerForException(),
						this.getMetaData().getAllProperties().toString()
						),
				takeScreenshotIfPossible()
				);	
	}
	
	protected Object throwExceptionFromProxy(String code, String action) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
				getClassForLoaderType(),
				action,
				code,
				Batteries.getProblemText(
						code,
						getElementNameFillerForException(),
						this.getMetaData().getAllProperties().toString()
						),
				takeScreenshotIfPossible()
				);	
	}
	
	protected Object throwUnsupportedActionExceptionFromProxy(String code, String action) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
				getClassForLoaderType(),
				action,
				code,
				Batteries.getProblemText(
						code,
						action,
						getElementNameFillerForException(),
						this.getMetaData().getAllProperties().toString()
						),
				takeScreenshotIfPossible()
				);	
	}
	
	private Object throwElementActionException(Throwable e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, Daksha.problem.ELEMENT_ACTION_FAILURE, action, filler);		
	}

	private Object throwElementInquiryException(Exception e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, Daksha.problem.ELEMENT_INQUIRY_FAILURE, action, filler);		
	}

	private Object throwElementGetAttributeException(Exception e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, Daksha.problem.ELEMENT_GET_ATTR_FAILURE, action, filler);		
	}

	protected Object throwElementGetInstanceException(Exception e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, Daksha.problem.ELEMENT_GET_INSTANCE_FAILURE, action, filler);		
	}

	protected Object throwElementIdentificationException(Exception e, String action, String filler) throws Exception{
		return throwBasicElementMessageException(e, Daksha.problem.ELEMENT_IDENTIFICATION_FAILURE, action, filler);		
	}



	@Override
	public void enterText(String text) throws Exception{
		try {
			getProxy().enterText(text);} catch (Exception e){
			throwElementActionException(e, "enterText", "enter text in");
		}
	}


	@Override
	public boolean isPresent() throws Exception{
		try {
			return getProxy().isPresent();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isPresent", "presence of");
		}
	}
	
	@Override
	public boolean isAbsent() throws Exception {
		try {
			return getProxy().isAbsent();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isAbsent", "absence of");
		}
	}
	
	@Override
	public boolean isVisible() throws Exception {
		try {
			return getProxy().isVisible();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isVisible", "visibility of");
		}
	}

	@Override
	public boolean isInvisible() throws Exception {
		try {
			return getProxy().isInvisible();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isInvisible", "invisibility of");
		}
	}


	@Override
	public void click() throws Exception {
		try {
			getProxy().click();} catch (Exception e){
			throwElementActionException(e, "click", "click");
		}
	}

	@Override
	public void focus() throws Exception {
		try {
			getProxy().focus();} catch (Exception e){
			throwElementActionException(e, "focus", "focus on");
		}
	}

	@Override
	public void waitForPresence() throws Exception {
		try {
			getProxy().waitForPresence();} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ELEMENT_WAIT_FAILURE,
					"waitForPresence",
					Batteries.getProblemText(
							Daksha.problem.ELEMENT_WAIT_FAILURE,
							this.getProxy().getAutomatorName(),
							this.getProxy().getWaitTime(),
							"presence of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}
	

	@Override
	public void waitForAbsence() throws Exception {
		try {
			getProxy().waitForAbsence();} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ELEMENT_WAIT_FAILURE,
					"waitForAbsence",
					Batteries.getProblemText(
							Daksha.problem.ELEMENT_WAIT_FAILURE,
							this.getProxy().getAutomatorName(),
							this.getProxy().getWaitTime(),
							"absence of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}

	@Override
	public void waitForVisibility() throws Exception {
		try {
			getProxy().waitForVisibility();} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ELEMENT_WAIT_FAILURE,
					"waitForVisibility",
					Batteries.getProblemText(
							Daksha.problem.ELEMENT_WAIT_FAILURE,
							this.getProxy().getAutomatorName(),
							this.getProxy().getWaitTime(),
							"visibility of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}

	@Override
	public void waitForInvisibility() throws Exception {
		try {
			getProxy().waitForInvisibility();} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ELEMENT_WAIT_FAILURE,
					"waitForInvisibility",
					Batteries.getProblemText(
							Daksha.problem.ELEMENT_WAIT_FAILURE,
							this.getProxy().getAutomatorName(),
							this.getProxy().getWaitTime(),
							"invisibility of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}

	@Override
	public void setText(String text) throws Exception {
		try {
			getProxy().setText(text);} catch (Exception e){
			throwElementActionException(e, "setText", "set text of");
		}
	}

	public void clearText() throws Exception {
		try {
			getProxy().clearText();} catch (Exception e){
			throwElementActionException(e, "clearText", "clear text of");
		}
	}

	public void check() throws Exception {
		try {
			getProxy().check();} catch (Exception e){
			throwElementActionException(e, "check", "check checkbox");
		}
	}

	public void uncheck() throws Exception {
		try {
			getProxy().uncheck();} catch (Exception e){
			throwElementActionException(e, "uncheck", "uncheck checkbox");
		}
	}

	public void toggle() throws Exception {
		try {
			getProxy().toggle();} catch (Exception e){
			throwElementActionException(e, "toggle", "toggle checkbox");
		}
	}

	/*
	 * Selection API
	 */

	public void selectLabel(String text) throws Exception {
		try {
			getProxy().selectLabel(text);} catch (Exception e){
			throwElementActionException(
					e, "selectLabel",
					String.format("select label %s from %s", text, this.getType().toString().toLowerCase())
					);
		}
	}	

	public void select(String text) throws Exception {
		try {
			getProxy().select(text);} catch (Exception e){
			throwElementActionException(
					e, "select",
					String.format("select label %s from %s", text, this.getType().toString().toLowerCase())
					);
		}
	}

	public void selectValue(String value) throws Exception {
		try {
			getProxy().selectValue(value);} catch (Exception e){
			throwElementActionException(
					e, "selectValue",
					String.format("select value %s from %s", value, this.getType().toString().toLowerCase())
					);
		}
	}

	public void selectIndex(int index) throws Exception {
		try {
			getProxy().selectIndex(index);} catch (Exception e){
			throwElementActionException(
					e, "selectIndex",
					String.format("select option at index %d from %s", index, this.getType().toString().toLowerCase())
					);
		}
	}

	public boolean hasSelectedLabel(String text) throws Exception {
		try {
			return getProxy().hasSelectedLabel(text);} catch (Exception e){
			return (boolean) throwElementInquiryException(
					e, "hasSelectedLabel",
					String.format("whether label %s is selected for %s", text, this.getType().toString().toLowerCase())
					);
		}
	}

	public boolean hasSelectedValue(String value) throws Exception {
		try {
			return getProxy().hasSelectedValue(value);} catch (Exception e){
			return (boolean) throwElementInquiryException(
					e, "hasSelectedValue",
					String.format("whether value %s is selected for %s", value, this.getType().toString().toLowerCase())
					);
		}
	}

	public boolean hasSelectedIndex(int index) throws Exception {
		try {
			return getProxy().hasSelectedIndex(index);} catch (Exception e){
			return (boolean) throwElementInquiryException(
					e, "hasSelectedIndex",
					String.format("whether option at index %d is selected for %s", index, this.getType().toString().toLowerCase())
					);
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllLabels() throws Exception {
		try {
			return getProxy().getAllLabels();} catch (Exception e){
			return (List<String>) throwElementGetAttributeException(
					e, "getAllLabels",
					String.format("all labels for %s", this.getType().toString().toLowerCase())
					);
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllValues() throws Exception {
		try {
			return getProxy().getAllValues();} catch (Exception e){
			return (List<String>) throwElementGetAttributeException(
					e, "getAllValues",
					String.format("all values for %s", this.getType().toString().toLowerCase())
					);
		}
	}


	public int getOptionCount() throws Exception {
		try {
			return getProxy().getOptionCount();} catch (Exception e){
			return (int) throwElementGetAttributeException(
					e, "getOptionCount",
					String.format("option count for %s", this.getType().toString().toLowerCase())
					);
		}
	}

	// Properties


	public String getText() throws Exception {
		try {
			return getProxy().getText();} catch (Exception e){
			return (String) throwElementGetAttributeException(e, "getText","text of");
		}
	}


	public String getValue() throws Exception {
		try {
			return getProxy().getValue();} catch (Exception e){
			return (String) throwElementGetAttributeException(e, "getValue","value of");
		}
	}


	public String getAttribute(String attr) throws Exception {
		try {
			return getProxy().getAttribute(attr);} catch (Exception e){
			return (String) throwElementGetAttributeException(e, "getAttribute",String.format("value of %s attribute of", attr));
		}
	}

	// Frame related action

	public void switchToFrame() throws Exception {
		this.getProxy().switchToFrame();
	}

	public void hover() throws Exception {
		try{
			this.getProxy().hover();} catch (Exception e){
			throwElementActionException(e, "hover", "hover on");
		}
	}


	public void hoverAndClick() throws Exception {
		try{
			this.getProxy().hoverAndClick();} catch (Exception e){
			throwElementActionException(e, "hoverAndClick", "hover and click on");
		}
	}


	public void rightClick() throws Exception {
		try{
			this.getProxy().rightClick();} catch (Throwable e){
			throwElementActionException(e, "rightClick", "right click on");
		}
	}


	public String getImagePath() throws Exception {
		return this.imagePath;
	}


	public void setImagePath(String imagePath) throws Exception {
		this.imagePath = imagePath;
	}

	@Override
	public Object throwUnsupportedActionException(String action) throws Exception {
		return throwUnsupportedException(action);		
	}

	@Override
	public Object throwNegativeIndexException(String action) throws Exception {
		return throwExceptionFromProxy(Daksha.problem.ELEMENT_NEGATIVE_INEDX, action);
	}

	@Override
	public Object throwZeroOrdinalException(String action) throws Exception {
		return throwExceptionFromProxy(Daksha.problem.ELEMENT_ZERO_ORDINAL, action);
	}

	@Override
	public Object throwEmptyElementQueueException(String action) throws Exception {
		return throwExceptionFromProxy(Daksha.problem.ELEMENT_EMPTY_QUEUE, action);
	}

	@Override
	public Object throwUnsupportedSelectActionException(String action) throws Exception {
		return throwUnsupportedActionExceptionFromProxy(Daksha.problem.ELEMENT_UNSUPPORTED_SELECT_ACTION, action);
	}

	@Override
	public void hoverAndClickElement(GuiElement uiElement) throws Exception {
		try{
			this.getProxy().hoverAndClickElement(uiElement);} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ACTION_MULTIELEMENT_FAILURE,
					"hoverAndClickElement",
					Batteries.getProblemText(
							Daksha.problem.ACTION_MULTIELEMENT_FAILURE,
							this.getProxy().getAutomatorName(),
							"hover on",
							this.getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString(),
							"click on",
							this.getElementNameFillerForException(uiElement),
							uiElement.getMetaData().getAllProperties().toString()	
							)
					);
		}
	}

	@Override
	public void rightClickAndClickElement(GuiElement uiElement) throws Exception {
		try{
			this.getProxy().rightClickAndClickElement(uiElement);} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ACTION_MULTIELEMENT_FAILURE,
					"rightClickAndClicElement",
					Batteries.getProblemText(
							Daksha.problem.ACTION_MULTIELEMENT_FAILURE,
							this.getProxy().getAutomatorName(),
							"right click on",
							this.getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString(),
							"click on",
							this.getElementNameFillerForException(uiElement),
							uiElement.getMetaData().getAllProperties().toString()	
							)
					);
		}
	}

	public GuiElement identify() throws Exception {
		try {
			this.getProxy().identify();
			return this;
		} catch (Exception e){
			return (GuiElement) throwElementIdentificationException(e, "identify", "identify element");
		}
	}

	@Override
	public GuiElement identifyAll() throws Exception {
		try {
			this.getProxy().identifyAll();
			return this;
		} catch (Exception e){
			return (GuiElement) throwElementIdentificationException(e, "identify", "identify all elements");
		}
	}

	@Override
	public GuiElement getInstanceAtIndex(int index) throws Exception {
		try {
			return this.getProxy().getInstanceAtIndex(index);
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getInstanceAtIndex", String.format("get instance at index %d", index));
		}
	}

	@Override
	public GuiElement get(int index) throws Exception {
		try {
			return this.getProxy().get(index);
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "get (index)", String.format("get instance at index %d", index));
		}
	}

	@Override
	public GuiElement get() throws Exception {
		try {
			return this.getProxy().get();
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "get", String.format("get instance at index %d", 0));
		}
	}

	@Override
	public GuiElement getInstanceAtOrdinal(int ordinal) throws Exception {
		try {
			return this.getProxy().getInstanceAtOrdinal(ordinal);
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getInstanceAtOrdinal", String.format("get instance at ordinal %d", ordinal));
		}
	}

	@Override
	public GuiElement getRandomInstance() throws Exception {
		try {
			return this.getProxy().getRandomInstance();
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getRandomInstance", "get random instance");
		}
	}

	@Override
	public GuiElement getFirstInstance() throws Exception {
		try {
			return this.getProxy().getFirstInstance();
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getFirstInstance", "get first instance");
		}
	}

	@Override
	public GuiElement getLastInstance() throws Exception {
		try {
			return this.getProxy().getLastInstance();
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getLastInstance", "get last instance");
		}
	}

	@Override
	public List<GuiElement> getAllInstances() throws Exception {
		try {
			return this.getProxy().getAllInstances();
		} catch (Exception e){
			return (List<GuiElement>) throwElementGetInstanceException(e, "getAllInstances", "get all instances");
		}
	}

	@Override
	public GuiElement getInstanceByText(String text) throws Exception {
		try {
			return this.getProxy().getInstanceByText(text);
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getInstanceByText", String.format("get instance by text '%s'", text));
		}
	}

	@Override
	public GuiElement getInstanceByTextContent(String textContent) throws Exception {
		try {
			return this.getProxy().getInstanceByTextContent(textContent);
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getInstanceByTextContent", String.format("get instance by text content '%s'", textContent));
		}
	}

}
