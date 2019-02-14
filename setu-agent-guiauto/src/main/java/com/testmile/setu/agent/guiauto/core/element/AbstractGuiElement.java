package com.testmile.setu.agent.guiauto.core.element;

import com.testmile.setu.agent.guiauto.core.handler.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
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