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
package daksha.core.cleanup.element;

import java.util.List;

import daksha.core.cleanup.automator.ConcreteUiAutomator;
import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.picker.UiElementMetaData;
import daksha.core.cleanup.picker.UiElementPicker;
import daksha.tpi.cleanup.element.UiElement;
import daksha.tpi.cleanup.element.UiMultiElement;
import daksha.tpi.cleanup.enums.UiElementType;
import daksha.tpi.cleanup.ui.UI;

public abstract class BaseConcreteUiSingleElement<D,E> extends BaseManagedConcreteUiElement<D,E,UiElementProxy> implements ConcreteUiElement<D,E>{
	private E toolElement = null;
	private UiElementMetaData emd = null;
	private UiElementType elementType = null;
	private UiElementPicker<D,E> picker = null;
	private int waitTime = 10;
	
	public BaseConcreteUiSingleElement(ConcreteUiAutomator<D,E> automator, UiElementProxy proxy) {
		super(automator, proxy);
		populateObjectVars(automator, proxy);
	}
	
	public BaseConcreteUiSingleElement(UI ui, ConcreteUiAutomator<D,E> automator, UiElementProxy proxy) {
		super(ui, automator, proxy);
		populateObjectVars(automator, proxy);

	}
	
	private void populateObjectVars(ConcreteUiAutomator<D,E> automator, UiElementProxy proxy) {
		this.emd = proxy.getMetaData();
		this.picker = automator.getPicker();		
		this.waitTime = automator.getWaitTime();
	}
	
	@Override
	public int getWaitTime() throws Exception {
		return this.waitTime;
	}
	
	public void identify() throws Exception{
		this.setToolElement(this.getAutomator().getPicker().find(this.emd));
	}
	
	public void identifyIfNull() throws Exception {
		if (this.getToolElement() == null) {
			this.identify();
		}
	}

	public UiElementProxy element(String name) throws Exception{
		
		UiElementMetaData metaData = this.getUI().getDefinition().getMetaData(name);
		E element = this.picker.find(this, metaData);
		return this.picker.convertToolElementToProxy(this.getUI(), metaData, element);
	}

	protected UiElementPicker<D,E> getPicker() {
		return this.picker;
	}
	
	public UiMultiElement elements(String name) throws Exception{
		UiElementMetaData metaData = this.getUI().getDefinition().getMetaData(name);
		List<E> elements = this.picker.findAll(this, metaData);
		return this.picker.convertMultiToolElementToProxy(this.getUI(), metaData, elements);
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
		return this.getToolElement() != null;
	}
	
	public UiElementMetaData getMetaData() {
		return emd;
	}

	public int getUiElementCount() throws Exception {
		if (this.isIdentified()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public UiElementType getElementType() {
		return this.elementType;
	}

	public void setElementType(UiElementType elementType) {
		this.elementType = elementType;
	}
	
	@Override
	public boolean isPresent() throws Exception {
		return this.getPicker().isPresent(this.getUiElementProxy());
	}
	
	@Override
	public void waitUntilPresent() throws Exception {
		if (!isPresent()) {
			throw new Exception("Not present despite waiting.");
		}
	}
	
	@Override
	public boolean isAbsent() throws Exception {
		return !isPresent();
	}
	
	@Override
	public void waitUntilAbsent() throws Exception {
		if (!isAbsent()) {
			throw new Exception("Not absent despite waiting.");
		}
	}
		
	@Override
	public boolean isVisible() throws Exception {
		return this.getPicker().isVisible(this.getUiElementProxy());
	}

	@Override
	public void waitUntilVisible() throws Exception {
		if (!isVisible()) {
			throw new Exception("Not visible despite waiting.");
		}
	}

	@Override
	public boolean isInvisible() throws Exception {
		return !isVisible();
	}
	
	@Override
	public void waitUntilInvisible() throws Exception {
		if (!isInvisible()) {
			throw new Exception("Not invisible despite waiting.");
		}
	}
	
	@Override
	public boolean isClickable() throws Exception {
		return this.getPicker().isClickable(this.getUiElementProxy());
	}
	
	@Override
	public void waitUntilClickable() throws Exception {
		if (!isClickable()) {
			throw new Exception("Not clickable despite waiting.");
		}
	}

	@Override
	public String getText() throws Exception {
		return (String) this.getUiElementProxy().throwUnsupportedActionException("getText");
	}

	@Override
	public String getValue() throws Exception {
		return (String) this.getUiElementProxy().throwUnsupportedActionException("getValue");
	}

	@Override
	public String getAttribute(String attr) throws Exception {
		return (String) this.getUiElementProxy().throwUnsupportedActionException("getAttribute");
	}

	@Override
	public void enterText(String text) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("enterText");
	}

	@Override
	public void setText(String text) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("setText");
	}

	@Override
	public void clearText() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("clearText");
	}

	@Override
	public void focus() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("focus");
	}
	
	@Override
	public void submit() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("submit");
	}

	@Override
	public void click() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("click");
	}

	@Override
	public void hover() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("hover");
	}

	@Override
	public void hoverAndClick() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("hoverAndClick");
	}

	@Override
	public void rightClick() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("rightClick");
	}

	@Override
	public void check() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("check");
	}

	@Override
	public void uncheck() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("uncheck");
	}

	@Override
	public void toggle() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("toggle");
	}

	@Override
	public String getImagePath() throws Exception {
		return (String) this.getUiElementProxy().throwUnsupportedActionException("getImagePath");
	}

	@Override
	public void setImagePath(String imagePath) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("setImagePath");
	}

	@Override
	public void select(String text) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("select");
	}

	@Override
	public void selectText(String text) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("selectLabel");
	}

	@Override
	public void selectValue(String value) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("selectValue");
	}

	@Override
	public void selectAtIndex(int index) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("selectIndex");
	}

	@Override
	public boolean hasSelectedText(String text) throws Exception {
		return (boolean) this.getUiElementProxy().throwUnsupportedActionException("hasSelectedLabel");
	}

	@Override
	public boolean hasSelectedValue(String value) throws Exception {
		return (boolean) this.getUiElementProxy().throwUnsupportedActionException("hasSelectedValue");
	}

	@Override
	public boolean hasSelectedIndex(int index) throws Exception {
		return (boolean) this.getUiElementProxy().throwUnsupportedActionException("hasSelectedIndex");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllOptions() throws Exception {
		return (List<String>) this.getUiElementProxy().throwUnsupportedActionException("getAllLabels");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllValues() throws Exception {
		return (List<String>) this.getUiElementProxy().throwUnsupportedActionException("getAllValues");
	}

	@Override
	public int getOptionCount() throws Exception {
		return (int) this.getUiElementProxy().throwUnsupportedActionException("getOptionCount");
	}

	@Override
	public void switchToFrame() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("switchToFrame");
	}

	public void hoverAndClickElement(UiElement uiElement) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("hoverAndClickElement");
	}

	public void rightClickAndClickElement(UiElement uiElement) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("rightClickAndClickElement");
	}
	
	@Override
	public boolean isChecked() throws Exception {
		return (boolean) this.getUiElementProxy().throwUnsupportedActionException("isChecked");
	}

	@Override
	public boolean isSelected() throws Exception {
		return (boolean) this.getUiElementProxy().throwUnsupportedActionException("isSelected");
	}
	
	@Override
	public void doubleClick() throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("doubleClick");
	}

	@Override
	public void dragAndDrop(String name) throws Exception {
		this.getUiElementProxy().throwUnsupportedActionException("dragAndDrop");
	}
}
