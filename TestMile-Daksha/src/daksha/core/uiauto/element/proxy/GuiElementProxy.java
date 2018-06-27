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
package daksha.core.uiauto.element.proxy;

import java.util.List;

import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.uiauto.element.ConcreteGuiElement;
import daksha.core.uiauto.identifier.GuiElementMetaData;
import daksha.core.problem.ErrorType;
import daksha.tpi.uiauto.element.GuiElement;
import daksha.tpi.uiauto.enums.GuiElementType;
import daksha.tpi.uiauto.gui.Gui;

public class GuiElementProxy extends BaseGuiElementProxy implements GuiElement{
	private GuiElementType elementType = GuiElementType.GENERIC;
	private ConcreteGuiElement<?,?> concreteElement = null;

	public GuiElementProxy(Gui gui, GuiAutomatorProxy automator, GuiElementMetaData emd) {
		super(gui, automator, emd);
	}
	
	public GuiElementProxy(GuiAutomatorProxy automator, GuiElementMetaData emd) {
		super(automator, emd);
	}

	public GuiElement identify() throws Exception {
		try {
			this.getFinder().identify();
			return this;
		} catch (Exception e){
			return (GuiElement) throwElementIdentificationException(e, "identify", "identify element");
		}
	}
	
	public GuiElementProxy asDropDown() throws Exception {
		this.getAutomator().getConcreteAutomator().getIdentifier().convertToDropDown(this);
		return this;
	}

	private GuiElementType getType() {
		return this.elementType;
	}
	
	@Override
	public ConcreteGuiElement<?,?> getConcreteElement() {
		return this.concreteElement;
	}

	public void setConcreteElement(ConcreteGuiElement<?,?> element){
		this.concreteElement = element;
		this.setConcreteFinder(element);
	}
	
	@Override
	public void enterText(String text) throws Exception{
		try {
			getConcreteElement().enterText(text);
		} catch (Exception e){
			e.printStackTrace();
			throwElementActionException(e, "enterText", "enter text in");
		}
	}


	@Override
	public boolean isPresent() throws Exception{
		try {
			return getConcreteElement().isPresent();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isPresent", "presence of");
		}
	}
	
	@Override
	public boolean isAbsent() throws Exception {
		try {
			return getConcreteElement().isAbsent();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isAbsent", "absence of");
		}
	}
	
	@Override
	public boolean isVisible() throws Exception {
		try {
			return getConcreteElement().isVisible();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isVisible", "visibility of");
		}
	}

	@Override
	public boolean isInvisible() throws Exception {
		try {
			return getConcreteElement().isInvisible();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isInvisible", "invisibility of");
		}
	}


	@Override
	public void click() throws Exception {
		try {
			getConcreteElement().click();
		} catch (Exception e){
			throwElementActionException(e, "click", "click");
		}
	}

	@Override
	public void focus() throws Exception {
		try {
			getConcreteElement().focus();} catch (Exception e){
			throwElementActionException(e, "focus", "focus on");
		}
	}

	@Override
	public void waitUntilPresent() throws Exception {
		try {
			getConcreteElement().waitUntilPresent();
		} catch (Exception e){
			throwElementException(
					e,
					ErrorType.ELEMENT_WAIT_FAILURE,
					"waitForPresence",
					String.format(
							ErrorType.ELEMENT_WAIT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							this.getConcreteElement().getWaitTime(),
							"presence of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}
	

	@Override
	public void waitUntilAbsent() throws Exception {
		try {
			getConcreteElement().waitUntilAbsent();
		} catch (Exception e){
			throwElementException(
					e,
					ErrorType.ELEMENT_WAIT_FAILURE,
					"waitForAbsence",
					String.format(
							ErrorType.ELEMENT_WAIT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							this.getConcreteElement().getWaitTime(),
							"absence of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}

	@Override
	public void waitUntilVisible() throws Exception {
		try {
			getConcreteElement().waitUntilVisible();} catch (Exception e){
			throwElementException(
					e,
					ErrorType.ELEMENT_WAIT_FAILURE,
					"waitForVisibility",
					String.format(
							ErrorType.ELEMENT_WAIT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							this.getConcreteElement().getWaitTime(),
							"visibility of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}

	@Override
	public void waitUntilInvisible() throws Exception {
		try {
			getConcreteElement().waitUntilInvisible();} catch (Exception e){
			throwElementException(
					e,
					ErrorType.ELEMENT_WAIT_FAILURE,
					"waitForInvisibility",
					String.format(
							ErrorType.ELEMENT_WAIT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							this.getConcreteElement().getWaitTime(),
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
			getConcreteElement().setText(text);} catch (Exception e){
			throwElementActionException(e, "setText", "set text of");
		}
	}

	public void clearText() throws Exception {
		try {
			getConcreteElement().clearText();} catch (Exception e){
			throwElementActionException(e, "clearText", "clear text of");
		}
	}

	@Override
	public boolean isChecked() throws Exception {
		try {
			return getConcreteElement().isChecked();
		} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isChecked", "checked state of");
		}
	}

	@Override
	public boolean isSelected() throws Exception {
		try {
			return getConcreteElement().isSelected();
		} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isSelected", "selected state of");
		}
	}
	
	public void check() throws Exception {
		try {
			getConcreteElement().check();} catch (Exception e){
			throwElementActionException(e, "check", "check checkbox");
		}
	}

	public void uncheck() throws Exception {
		try {
			getConcreteElement().uncheck();} catch (Exception e){
			throwElementActionException(e, "uncheck", "uncheck checkbox");
		}
	}

	public void toggle() throws Exception {
		try {
			getConcreteElement().toggle();} catch (Exception e){
			throwElementActionException(e, "toggle", "toggle checkbox");
		}
	}

	/*
	 * Selection API
	 */

	public void selectText(String text) throws Exception {
		try {
			getConcreteElement().selectText(text);} catch (Exception e){
			throwElementActionException(
					e, "selectLabel",
					String.format("select label %s from %s", text, this.getType().toString().toLowerCase())
					);
		}
	}	

	public void select(String text) throws Exception {
		try {
			getConcreteElement().select(text);} catch (Exception e){
			throwElementActionException(
					e, "select",
					String.format("select label %s from %s", text, this.getType().toString().toLowerCase())
					);
		}
	}

	public void selectValue(String value) throws Exception {
		try {
			getConcreteElement().selectValue(value);} catch (Exception e){
			throwElementActionException(
					e, "selectValue",
					String.format("select value %s from %s", value, this.getType().toString().toLowerCase())
					);
		}
	}

	public void selectAtIndex(int index) throws Exception {
		try {
			getConcreteElement().selectAtIndex(index);} catch (Exception e){
			throwElementActionException(
					e, "selectIndex",
					String.format("select option at index %d from %s", index, this.getType().toString().toLowerCase())
					);
		}
	}

	public boolean hasSelectedText(String text) throws Exception {
		try {
			return getConcreteElement().hasSelectedText(text);} catch (Exception e){
			return (boolean) throwElementInquiryException(
					e, "hasSelectedLabel",
					String.format("whether label %s is selected for %s", text, this.getType().toString().toLowerCase())
					);
		}
	}

	public boolean hasSelectedValue(String value) throws Exception {
		try {
			return getConcreteElement().hasSelectedValue(value);} catch (Exception e){
			return (boolean) throwElementInquiryException(
					e, "hasSelectedValue",
					String.format("whether value %s is selected for %s", value, this.getType().toString().toLowerCase())
					);
		}
	}

	public boolean hasSelectedIndex(int index) throws Exception {
		try {
			return getConcreteElement().hasSelectedIndex(index);} catch (Exception e){
			return (boolean) throwElementInquiryException(
					e, "hasSelectedIndex",
					String.format("whether option at index %d is selected for %s", index, this.getType().toString().toLowerCase())
					);
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllOptions() throws Exception {
		try {
			return getConcreteElement().getAllOptions();} catch (Exception e){
			return (List<String>) throwElementGetAttributeException(
					e, "getAllLabels",
					String.format("all labels for %s", this.getType().toString().toLowerCase())
					);
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllValues() throws Exception {
		try {
			return getConcreteElement().getAllValues();} catch (Exception e){
			return (List<String>) throwElementGetAttributeException(
					e, "getAllValues",
					String.format("all values for %s", this.getType().toString().toLowerCase())
					);
		}
	}


	public int getOptionCount() throws Exception {
		try {
			return getConcreteElement().getOptionCount();} catch (Exception e){
			return (int) throwElementGetAttributeException(
					e, "getOptionCount",
					String.format("option count for %s", this.getType().toString().toLowerCase())
					);
		}
	}

	// Properties


	public String getText() throws Exception {
		try {
			return getConcreteElement().getText();} catch (Exception e){
			return (String) throwElementGetAttributeException(e, "getText","text of");
		}
	}


	public String getValue() throws Exception {
		try {
			return getConcreteElement().getValue();} catch (Exception e){
			return (String) throwElementGetAttributeException(e, "getValue","value of");
		}
	}


	public String getAttribute(String attr) throws Exception {
		try {
			return getConcreteElement().getAttribute(attr);} catch (Exception e){
			return (String) throwElementGetAttributeException(e, "getAttribute",String.format("value of %s attribute of", attr));
		}
	}

	// Frame related action

	public void switchToFrame() throws Exception {
		this.getConcreteElement().switchToFrame();
	}

	public void hover() throws Exception {
		try{
			this.getConcreteElement().hover();} catch (Exception e){
			throwElementActionException(e, "hover", "hover on");
		}
	}


	public void hoverAndClick() throws Exception {
		try{
			this.getConcreteElement().hoverAndClick();} catch (Exception e){
			throwElementActionException(e, "hoverAndClick", "hover and click on");
		}
	}


	public void rightClick() throws Exception {
		try{
			this.getConcreteElement().rightClick();} catch (Throwable e){
			throwElementActionException(e, "rightClick", "right click on");
		}
	}

	@Override
	public Object throwUnsupportedActionException(String action) throws Exception {
		return throwUnsupportedException(action);		
	}

	@Override
	public Object throwUnsupportedSelectActionException(String action) throws Exception {
		return throwUnsupportedActionExceptionFromProxy(ErrorType.ELEMENT_UNSUPPORTED_SELECT_ACTION, action);
	}

	@Override
	public void hoverAndClickElement(String name) throws Exception {
		try{
			this.getConcreteElement().hoverAndClickElement(name);} catch (Exception e){
			throwElementException(
					e,
					ErrorType.ACTION_MULTIELEMENT_FAILURE,
					"hoverAndClickElement",
					String.format(
							ErrorType.ACTION_MULTIELEMENT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							"hover on",
							this.getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString(),
							"click on",
							name,
							""
							)
					);
		}
	}

	@Override
	public void rightClickAndClickElement(String name) throws Exception {
		try{
			this.getConcreteElement().rightClickAndClickElement(name);
		} catch (Exception e){
			throwElementException(
					e,
					ErrorType.ACTION_MULTIELEMENT_FAILURE,
					"rightClickAndClicElement",
					String.format(
							ErrorType.ACTION_MULTIELEMENT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							"right click on",
							this.getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString(),
							"click on",
							name,
							""	
							)
					);
		}
	}

	@Override
	public void waitUntilClickable() throws Exception {
		getConcreteElement().waitUntilClickable();
	}

	@Override
	public boolean isClickable() throws Exception {
		return getConcreteElement().isClickable();
	}
	
	@Override
	public void doubleClick() throws Exception {
		getConcreteElement().doubleClick();
	}

	@Override
	public void dragAndDrop(String name) throws Exception {
		getConcreteElement().dragAndDrop(name);
	}

	@Override
	public void submit() throws Exception {
		getConcreteElement().submit();
	}

}
