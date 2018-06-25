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

import java.util.ArrayList;
import java.util.List;

import daksha.core.cleanup.automator.ConcreteUiAutomator;
import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.element.proxy.UiMultiElementProxy;
import daksha.core.cleanup.picker.UiElementMetaData;
import daksha.core.cleanup.picker.UiElementPicker;
import daksha.tpi.cleanup.ui.UI;

public abstract class BaseConcreteUiMultiElement<D,E> extends BaseManagedConcreteUiElement<D,E,UiMultiElementProxy> implements ConcreteUiMultiElement<D,E>{
	private List<E> toolElements = null;
	private UiElementMetaData emd = null;
	private UiElementPicker<D,E> picker = null;

	public BaseConcreteUiMultiElement(UI ui, ConcreteUiAutomator<D,E> automator, UiMultiElementProxy eProxy) {
		super(ui, automator, eProxy);
		this.emd = eProxy.getMetaData();
		this.picker = automator.getPicker();
	}
	
	public BaseConcreteUiMultiElement(ConcreteUiAutomator<D,E> automator, UiMultiElementProxy eProxy) {
		super(automator, eProxy);
	}
	
	public void identify() throws Exception{
		this.toolElements = this.picker.findAll(this.emd);
	}
	
	public void identifyIfNull() throws Exception {
		if (toolElements == null) {
			this.identify();
		}
	}
	
	protected UiElementPicker<D,E> getPicker(){
		return this.picker;
	}

	protected List<E> getToolElements(){
		return this.toolElements;
	}
	
	public void setToolElements(List<E> elements) {
		this.toolElements = elements;
	}
	
	public UiElementMetaData getMetaData() {
		return emd;
	}
	
	public boolean isIdentified() throws Exception{
		return (this.toolElements != null) && (this.toolElements.size() > 0);
	}
	
	public int getUiElementCount() throws Exception {
		if (this.isIdentified()) {
			return toolElements.size();
		} else {
			return 0;
		}
	}
	
	
	protected abstract boolean doesTextMatch(E element, String text) throws Exception;
	protected abstract boolean doesTextContain(E element, String text) throws Exception;
	
	public UiElementProxy getInstanceAtIndex(int index) throws Exception {
		return this.getPicker().convertToolElementToProxy(this.getUI(), this.getMetaData(), this.toolElements.get(index));
	}
	
	@Override
	public List<UiElementProxy> getAllInstances() throws Exception {
		List<UiElementProxy> outList = new ArrayList<UiElementProxy>();
		for (E element: this.toolElements) {
			outList.add(this.getPicker().convertToolElementToProxy(this.getUI(), this.getMetaData(), element));
		}
		return outList;
	}
	
	@Override
	public UiElementProxy getInstanceByText(String text) throws Exception {
		for (E element: this.toolElements){
			if (doesTextMatch(element, text)) {
				return this.getPicker().convertToolElementToProxy(this.getUI(), this.getMetaData(), element);
			}
		}
		throw new Exception("None of the element instances has the specified text.");
	}

	@Override
	public UiElementProxy getInstanceByTextContent(String text) throws Exception {
		for (E element: this.getToolElements()){
			if (doesTextContain(element, text)) {
				return this.getPicker().convertToolElementToProxy(this.getUI(), this.getMetaData(), element);
			}
		}
		throw new Exception("None of the element instances has the specified text content.");
	}
}
