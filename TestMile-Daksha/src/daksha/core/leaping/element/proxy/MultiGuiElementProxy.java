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
package daksha.core.leaping.element.proxy;

import java.util.List;
import java.util.Random;

import daksha.core.leaping.element.ConcreteMultiGuiElement;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.core.problem.ErrorType;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.element.MultiGuiElement;
import daksha.tpi.leaping.pageobject.Page;

public class MultiGuiElementProxy extends BaseGuiElementProxy implements MultiGuiElement{
	private ConcreteMultiGuiElement<?,?> concreteElement = null;
	private GuiAutomator<?,?> automator = null;

	public MultiGuiElementProxy(Page page, GuiAutomator<?,?> automator, GuiElementMetaData emd) {
		super(page, emd);
		this.automator = automator;
	}
	
	public MultiGuiElementProxy(GuiAutomator<?,?> automator, GuiElementMetaData emd) {
		super(emd);
		this.automator = automator;
	}
	
	public MultiGuiElement identify() throws Exception {
		try {
			this.getFinder().identify();
			return this;
		} catch (Exception e){
			return (MultiGuiElement) throwElementIdentificationException(e, "identifyAll", "identify all elements");
		}
	}
	
	@Override
	public Object throwNegativeIndexException(String action) throws Exception {
		return throwExceptionFromProxy(ErrorType.ELEMENT_NEGATIVE_INEDX, action);
	}

	@Override
	public Object throwZeroOrdinalException(String action) throws Exception {
		return throwExceptionFromProxy(ErrorType.ELEMENT_ZERO_ORDINAL, action);
	}

	@Override
	public Object throwEmptyElementQueueException(String action) throws Exception {
		return throwExceptionFromProxy(ErrorType.ELEMENT_EMPTY_QUEUE, action);
	}
	
	@Override
	public ConcreteMultiGuiElement getConcreteElement() {
		return this.concreteElement;
	}

	public void setConcreteElement(ConcreteMultiGuiElement element){
		this.concreteElement = element;
		this.setConcreteFinder(element);
	}
	
	public void verifyIndex(int index) throws Exception {
		if (index < 0){
			this.throwNegativeIndexException("verifyIndex");
		}
	}

	public void verifyOrdinal(int ordinal) throws Exception {
		if (ordinal < 1){
			this.throwZeroOrdinalException("verifyOrdinal");
		}
	}

	public void prepareIndexIndentification(int index) throws Exception {
		verifyIndex(index);
		identify();		
	}
	
	@Override
	public List<GuiElementProxy> getAllInstances() throws Exception {
		this.getFinder().identifyIfNull();
		return this.concreteElement.getAllInstances();
	}

	@Override
	public GuiElement getInstanceAtIndex(int index) throws Exception {
		try {
			this.getFinder().identifyIfNull();
			return this.concreteElement.getInstanceAtIndex(index);
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getInstanceAtIndex", String.format("get instance at index %d", index));
		}
	}

	@Override
	public GuiElement get(int index) throws Exception {
		try {
			this.verifyIndex(index);
			return this.getInstanceAtIndex(index);
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "get (index)", String.format("get instance at index %d", index));
		}
	}

	@Override
	public GuiElement getInstanceAtOrdinal(int ordinal) throws Exception {
		try {
			this.verifyOrdinal(ordinal);
			return this.getInstanceAtIndex(ordinal - 1);
		} catch (Exception e){
			System.out.println(e);
			e.printStackTrace();
			return (GuiElement) throwElementGetInstanceException(e, "getInstanceAtOrdinal", String.format("get instance at ordinal %d", ordinal));
		}
	}
	
	public int getElementCount() throws Exception {
		this.getFinder().identifyIfNull();
		return this.getFinder().getGuiElementCount();
	}
	
	private int getRandomElementIndex() throws Exception {
		this.getFinder().identifyIfNull();
		int count = this.getFinder().getGuiElementCount();
		if (count == 0) {
			return (int) this.throwEmptyElementQueueException("getRandomElementIndex");
		} else if (count == 1) {
			return 0;
		} else {
			Random rn = new Random();
			return rn.nextInt(count);
		}
	}

	@Override
	public GuiElement getRandomInstance() throws Exception {
		try {
			return this.getInstanceAtIndex(getRandomElementIndex());
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getRandomInstance", "get random instance");
		}
	}

	@Override
	public GuiElement getFirstInstance() throws Exception {
		try {
			return this.getInstanceAtIndex(0);
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getFirstInstance", "get first instance");
		}
	}
	
	private int getLastIndex() throws Exception {
		this.getFinder().identifyIfNull();
		int count = this.getFinder().getGuiElementCount();
		if (count == 0) {
			return (int) this.throwEmptyElementQueueException("getLastIndex");
		} else {
			return count - 1;
		}
	}

	@Override
	public GuiElement getLastInstance() throws Exception {
		try {
			return this.getInstanceAtIndex(getLastIndex());
		} catch (Exception e){
			return (GuiElement) throwElementGetInstanceException(e, "getLastInstance", "get last instance");
		}
	}
	
	@Override
	public GuiElementProxy getInstanceByText(String text) throws Exception {
		this.getFinder().identifyIfNull();
		try {
			return  this.concreteElement.getInstanceByText(text);
		} catch (Exception e){
			return (GuiElementProxy) throwElementGetInstanceException(e, "getInstanceByText", "get instance with text: " + text);
		}
	}

	@Override
	public GuiElementProxy getInstanceByTextContent(String text) throws Exception {
		this.getFinder().identifyIfNull();
		try {
			return  this.concreteElement.getInstanceByTextContent(text);
		} catch (Exception e){
			return (GuiElementProxy) throwElementGetInstanceException(e, "getInstanceByTextContext", "get instance containing text: " + text);
		}
	}

}
