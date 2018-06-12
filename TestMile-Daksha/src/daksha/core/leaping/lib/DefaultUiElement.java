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

import java.util.List;

import daksha.core.batteries.config.Batteries;
import daksha.core.leaping.UiAutomator;
import daksha.core.leaping.interfaces.ElementMetaData;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.interfaces.UiElement;

public class DefaultUiElement  extends DefaultACElement implements UiElement{
	private UiAutomationContext idType = null;

	public DefaultUiElement(ElementMetaData metaData) {
		super(metaData);
	}

	@Override
	public Object throwUnsupportedActionException(String action) throws Exception {
		return throwUnsupportedException(action);		
	}

	@Override
	public Object throwNegativeIndexException(String action) throws Exception {
		return throwExceptionFromProxy(UiAutomator.problem.ELEMENT_NEGATIVE_INEDX, action);
	}

	@Override
	public Object throwZeroOrdinalException(String action) throws Exception {
		return throwExceptionFromProxy(UiAutomator.problem.ELEMENT_ZERO_ORDINAL, action);
	}

	@Override
	public Object throwEmptyElementQueueException(String action) throws Exception {
		return throwExceptionFromProxy(UiAutomator.problem.ELEMENT_EMPTY_QUEUE, action);
	}

	@Override
	public Object throwUnsupportedSelectActionException(String action) throws Exception {
		return throwUnsupportedActionExceptionFromProxy(UiAutomator.problem.ELEMENT_UNSUPPORTED_SELECT_ACTION, action);
	}

	@Override
	public void hoverAndClickElement(UiElement uiElement) throws Exception {
		try{
			this.getProxy().hoverAndClickElement(uiElement);} catch (Exception e){
			throwElementException(
					e,
					UiAutomator.problem.ACTION_MULTIELEMENT_FAILURE,
					"hoverAndClickElement",
					Batteries.getProblemText(
							UiAutomator.problem.ACTION_MULTIELEMENT_FAILURE,
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
	public void rightClickAndClickElement(UiElement uiElement) throws Exception {
		try{
			this.getProxy().rightClickAndClickElement(uiElement);} catch (Exception e){
			throwElementException(
					e,
					UiAutomator.problem.ACTION_MULTIELEMENT_FAILURE,
					"rightClickAndClicElement",
					Batteries.getProblemText(
							UiAutomator.problem.ACTION_MULTIELEMENT_FAILURE,
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

	public UiElement identify() throws Exception {
		try {
			this.getProxy().identify();
			return this;
		} catch (Exception e){
			return (UiElement) throwElementIdentificationException(e, "identify", "identify element");
		}
	}

	@Override
	public UiElement identifyAll() throws Exception {
		try {
			this.getProxy().identifyAll();
			return this;
		} catch (Exception e){
			return (UiElement) throwElementIdentificationException(e, "identify", "identify all elements");
		}
	}

	@Override
	public UiElement getInstanceAtIndex(int index) throws Exception {
		try {
			return this.getProxy().getInstanceAtIndex(index);
		} catch (Exception e){
			return (UiElement) throwElementGetInstanceException(e, "getInstanceAtIndex", String.format("get instance at index %d", index));
		}
	}

	@Override
	public UiElement get(int index) throws Exception {
		try {
			return this.getProxy().get(index);
		} catch (Exception e){
			return (UiElement) throwElementGetInstanceException(e, "get (index)", String.format("get instance at index %d", index));
		}
	}

	@Override
	public UiElement get() throws Exception {
		try {
			return this.getProxy().get();
		} catch (Exception e){
			return (UiElement) throwElementGetInstanceException(e, "get", String.format("get instance at index %d", 0));
		}
	}

	@Override
	public UiElement getInstanceAtOrdinal(int ordinal) throws Exception {
		try {
			return this.getProxy().getInstanceAtOrdinal(ordinal);
		} catch (Exception e){
			return (UiElement) throwElementGetInstanceException(e, "getInstanceAtOrdinal", String.format("get instance at ordinal %d", ordinal));
		}
	}

	@Override
	public UiElement getRandomInstance() throws Exception {
		try {
			return this.getProxy().getRandomInstance();
		} catch (Exception e){
			return (UiElement) throwElementGetInstanceException(e, "getRandomInstance", "get random instance");
		}
	}

	@Override
	public UiElement getFirstInstance() throws Exception {
		try {
			return this.getProxy().getFirstInstance();
		} catch (Exception e){
			return (UiElement) throwElementGetInstanceException(e, "getFirstInstance", "get first instance");
		}
	}

	@Override
	public UiElement getLastInstance() throws Exception {
		try {
			return this.getProxy().getLastInstance();
		} catch (Exception e){
			return (UiElement) throwElementGetInstanceException(e, "getLastInstance", "get last instance");
		}
	}

	@Override
	public List<UiElement> getAllInstances() throws Exception {
		try {
			return this.getProxy().getAllInstances();
		} catch (Exception e){
			return (List<UiElement>) throwElementGetInstanceException(e, "getAllInstances", "get all instances");
		}
	}

	@Override
	public UiElement getInstanceByText(String text) throws Exception {
		try {
			return this.getProxy().getInstanceByText(text);
		} catch (Exception e){
			return (UiElement) throwElementGetInstanceException(e, "getInstanceByText", String.format("get instance by text '%s'", text));
		}
	}

	@Override
	public UiElement getInstanceByTextContent(String textContent) throws Exception {
		try {
			return this.getProxy().getInstanceByTextContent(textContent);
		} catch (Exception e){
			return (UiElement) throwElementGetInstanceException(e, "getInstanceByTextContent", String.format("get instance by text content '%s'", textContent));
		}
	}

}
