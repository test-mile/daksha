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
package daksha.core.leaping.element;

import java.io.File;
import java.util.List;
import java.util.Random;

import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.core.leaping.identifier.Identifier;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.element.MultiGuiElement;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.pageobject.Page;

public abstract class BaseConcreteSingleGuiElement<D,E> extends BaseManagedConcreteGuiElement<D,E,GuiElementProxy> implements ConcreteGuiElement<D,E>{
	private E toolElement = null;
	private GuiElementMetaData emd = null;
	private GuiElementType elementType = null;
	private Identifier<D,E> identifier = null;

	public BaseConcreteSingleGuiElement(Page page, GuiAutomator<D,E> automator, GuiElementProxy proxy) {
		super(page, automator, proxy);
		this.emd = proxy.getMetaData();
		this.identifier = automator.getIdentifier();
	}
	
	public BaseConcreteSingleGuiElement(GuiAutomator<D,E> automator, GuiElementProxy proxy) {
		super(automator, proxy);
	}
	
	public void identify() throws Exception{
		this.getAutomator().getIdentifier().find(this.emd);
	}
	
	public void identifyIfNull() throws Exception {
		if (toolElement == null) {
			this.identify();
		}
	}

	public GuiElementProxy element(String name) throws Exception{
		GuiElementMetaData metaData = this.getPage().getLoader().getMetaData(name);
		E element = this.identifier.find(this, metaData);
		return this.identifier.convertToolElementToProxy(this.getPage(), metaData, element);
	}

	protected Identifier<D,E> getIdentifier() {
		return this.identifier;
	}
	
	public MultiGuiElement elements(String name) throws Exception{
		GuiElementMetaData metaData = this.getPage().getLoader().getMetaData(name);
		List<E> elements = this.identifier.findAll(this, metaData);
		return this.identifier.convertMultiToolElementToProxy(this.getPage(), metaData, elements);
	}
	
	public void setToolElement(E element) {
		this.toolElement = element;
	}
	
	public E getToolElement() {
		return this.toolElement;
	}

	protected E getToolElementWithRetry() throws Exception {
		E element = this.getToolElement();
		if (element == null){
			identify();
			return this.getToolElement();
		} else {
			return element;
		}
	}
	
	public boolean isIdentified() throws Exception{
		return this.toolElement != null;
	}
	
	public GuiElementMetaData getMetaData() {
		return emd;
	}

	public int getGuiElementCount() throws Exception {
		if (this.isIdentified()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public GuiElementType getElementType() {
		return this.elementType;
	}

	public void setElementType(GuiElementType elementType) {
		this.elementType = elementType;
	}

	@Override
	public String getText() throws Exception {
		return (String) this.getGuiElementProxy().throwUnsupportedActionException("getText");
	}

	@Override
	public String getValue() throws Exception {
		return (String) this.getGuiElementProxy().throwUnsupportedActionException("getValue");
	}

	@Override
	public String getAttribute(String attr) throws Exception {
		return (String) this.getGuiElementProxy().throwUnsupportedActionException("getAttribute");
	}

	@Override
	public void enterText(String text) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("enterText");
	}

	@Override
	public void setText(String text) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("setText");
	}

	@Override
	public void clearText() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("clearText");
	}

	@Override
	public void focus() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("focus");
	}

	@Override
	public void click() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("click");
	}

	@Override
	public void hover() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("hover");
	}

	@Override
	public void hoverAndClick() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("hoverAndClick");
	}

	@Override
	public void rightClick() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("rightClick");
	}

	@Override
	public void check() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("check");
	}

	@Override
	public void uncheck() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("uncheck");
	}

	@Override
	public void toggle() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("toggle");
	}

	@Override
	public String getImagePath() throws Exception {
		return (String) this.getGuiElementProxy().throwUnsupportedActionException("getImagePath");
	}

	@Override
	public void setImagePath(String imagePath) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("setImagePath");
	}

	@Override
	public void select(String text) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("select");
	}

	@Override
	public void selectText(String text) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("selectLabel");
	}

	@Override
	public void selectValue(String value) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("selectValue");
	}

	@Override
	public void selectAtIndex(int index) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("selectIndex");
	}

	@Override
	public boolean hasSelectedText(String text) throws Exception {
		return (boolean) this.getGuiElementProxy().throwUnsupportedActionException("hasSelectedLabel");
	}

	@Override
	public boolean hasSelectedValue(String value) throws Exception {
		return (boolean) this.getGuiElementProxy().throwUnsupportedActionException("hasSelectedValue");
	}

	@Override
	public boolean hasSelectedIndex(int index) throws Exception {
		return (boolean) this.getGuiElementProxy().throwUnsupportedActionException("hasSelectedIndex");
	}

	@Override
	public List<String> getAllOptions() throws Exception {
		return (List<String>) this.getGuiElementProxy().throwUnsupportedActionException("getAllLabels");
	}

	@Override
	public List<String> getAllValues() throws Exception {
		return (List<String>) this.getGuiElementProxy().throwUnsupportedActionException("getAllValues");
	}

	@Override
	public int getOptionCount() throws Exception {
		return (int) this.getGuiElementProxy().throwUnsupportedActionException("getOptionCount");
	}

	@Override
	public void switchToFrame() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("switchToFrame");
	}

	@Override
	public int getWaitTime() throws Exception {
		return (int) this.getGuiElementProxy().throwUnsupportedActionException("getWaitTime");
	}

	public void hoverAndClickElement(GuiElement uiElement) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("hoverAndClickElement");
	}

	public void rightClickAndClickElement(GuiElement uiElement) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("rightClickAndClickElement");
	}

	public GuiElement getInstanceByText(String text) throws Exception {
		return (GuiElement) this.getGuiElementProxy().throwUnsupportedActionException("getInstanceByText");
	}

	public GuiElement getInstanceByTextContent(String text) throws Exception {
		return (GuiElement) this.getGuiElementProxy().throwUnsupportedActionException("getInstanceByTextContent");
	}
	
	@Override
	public boolean isPresent() throws Exception {
		return (boolean) this.getGuiElementProxy().throwUnsupportedActionException("isPresent");
	}
	
	@Override
	public boolean isAbsent() throws Exception {
		return (boolean) this.getGuiElementProxy().throwUnsupportedActionException("isAbsent");
	}

	@Override
	public boolean isVisible() throws Exception {
		return (boolean) this.getGuiElementProxy().throwUnsupportedActionException("isVisible");
	}

	@Override
	public boolean isInvisible() throws Exception {
		return (boolean) this.getGuiElementProxy().throwUnsupportedActionException("isInvisible");
	}
	
	@Override
	public void waitForPresence() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("waitForPresence");
	}

	@Override
	public void waitForAbsence() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("waitForAbsence");
	}

	@Override
	public void waitForVisibility() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("waitForVisibility");
	}

	@Override
	public void waitForInvisibility() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("waitForInvisibility");
	}
	
	
	@Override
	public void doubleClick() throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("doubleClick");
	}

	@Override
	public void dragAndDrop(String name) throws Exception {
		this.getGuiElementProxy().throwUnsupportedActionException("dragAndDrop");
	}
}
