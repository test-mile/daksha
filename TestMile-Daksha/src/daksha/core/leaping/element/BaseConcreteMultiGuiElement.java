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

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;

import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.core.leaping.identifier.Identifier;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.pageobject.Page;

public abstract class BaseConcreteMultiGuiElement<D,E> extends BaseManagedConcreteGuiElement<D,E,MultiGuiElementProxy> implements ConcreteMultiGuiElement<D,E>{
	private List<E> toolElements = null;
	private GuiElementMetaData emd = null;
	private Identifier<D,E> identifier = null;

	public BaseConcreteMultiGuiElement(Page page, GuiAutomator<D,E> automator, MultiGuiElementProxy eProxy) {
		super(page, automator, eProxy);
		this.emd = eProxy.getMetaData();
		this.identifier = automator.getIdentifier();
	}
	
	public BaseConcreteMultiGuiElement(GuiAutomator<D,E> automator, MultiGuiElementProxy eProxy) {
		super(automator, eProxy);
	}
	
	public void identify() throws Exception{
		this.identifier.findAll(this.emd);
	}
	
	public void identifyIfNull() throws Exception {
		if (toolElements == null) {
			this.identify();
		}
	}
	
	protected Identifier<D,E> getIdentifier(){
		return this.identifier;
	}

	protected List<E> getToolElements(){
		return this.toolElements;
	}
	
	public void setToolElements(List<E> elements) {
		this.toolElements = elements;
	}
	
	public GuiElementMetaData getMetaData() {
		return emd;
	}
	
	public boolean isIdentified() throws Exception{
		return (this.toolElements != null) && (this.toolElements.size() > 0);
	}
	
	public int getGuiElementCount() throws Exception {
		if (this.isIdentified()) {
			return toolElements.size();
		} else {
			return 0;
		}
	}
	
	
	protected abstract boolean doesTextMatch(E element, String text) throws Exception;
	protected abstract boolean doesTextContain(E element, String text) throws Exception;
	
	public GuiElementProxy getInstanceAtIndex(int index) throws Exception {
		return this.getIdentifier().convertToolElementToProxy(this.getPage(), this.getMetaData(), this.toolElements.get(index));
	}
	
	@Override
	public GuiElementProxy getInstanceByText(String text) throws Exception {
		for (E element: this.toolElements){
			if (doesTextMatch(element, text)) {
				return this.getIdentifier().convertToolElementToProxy(this.getPage(), this.getMetaData(), element);
			}
		}
		throw new Exception("None of the element instances has the specified text.");
	}

	@Override
	public GuiElementProxy getInstanceByTextContent(String text) throws Exception {
		for (E element: this.getToolElements()){
			if (doesTextContain(element, text)) {
				return this.getIdentifier().convertToolElementToProxy(this.getPage(), this.getMetaData(), element);
			}
		}
		throw new Exception("None of the element instances has the specified text content.");
	}
}
