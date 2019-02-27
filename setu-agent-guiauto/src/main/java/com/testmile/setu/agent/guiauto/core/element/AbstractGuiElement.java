/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
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

package com.testmile.setu.agent.guiauto.core.element;

import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.tpi.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.handler.element.BasicActionsHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementInquirer;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementStateHandler;

public abstract class AbstractGuiElement implements GuiElement {
	private BasicActionsHandler basicActionsHandler;
	private ElementInquirer inquirer;
	private ElementStateHandler stateHandler;
	private ElementFinder elementFinder;

	public AbstractGuiElement() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiElement#getBasicActionsHandler()
	 */
	@Override
	public BasicActionsHandler getBasicActionsHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(basicActionsHandler, this.getClass().getSimpleName(), BasicActionsHandler.class.getSimpleName());;
		return basicActionsHandler;
	}

	protected void setBasicActionsHandler(BasicActionsHandler basicActionsHandler) {
		this.basicActionsHandler = basicActionsHandler;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiElement#getInquirer()
	 */
	@Override
	public ElementInquirer getInquirer() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(inquirer, this.getClass().getSimpleName(), ElementInquirer.class.getSimpleName());;
		return inquirer;
	}

	protected void setInquirer(ElementInquirer inquirer) {
		this.inquirer = inquirer;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiElement#getStateHandler()
	 */
	@Override
	public ElementStateHandler getStateHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(stateHandler, this.getClass().getSimpleName(), ElementStateHandler.class.getSimpleName());;
		return stateHandler;
	}

	protected void setStateHandler(ElementStateHandler stateHandler) {
		this.stateHandler = stateHandler;
	}	
	
	@Override
	public ElementFinder getElementFinder() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(elementFinder, this.getClass().getSimpleName(), ElementFinder.class.getSimpleName());
		return this.elementFinder;
	}
	
	protected void setElementFinder(ElementFinder elementFinder) {
		this.elementFinder = elementFinder;
	}
	
	public void switchToFrame() throws Exception{
		throw new Exception("Frame handling is not applicable to Sikuli.");
	}

}