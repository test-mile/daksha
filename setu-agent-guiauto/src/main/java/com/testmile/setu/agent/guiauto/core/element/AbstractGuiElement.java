package com.testmile.setu.agent.guiauto.core.element;

import com.testmile.setu.agent.guiauto.core.handler.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.handler.element.BasicActionsHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.CheckboxHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.DropdownHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementFrameHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementInquirer;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementStateHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.RadioButtonHandler;

public class AbstractGuiElement implements GuiElement {
	private BasicActionsHandler basicActionsHandler;
	private CheckboxHandler checkBoxHandler;
	private ElementFrameHandler frameHandler;
	private ElementInquirer inquirer;
	private ElementStateHandler stateHandler;
	private RadioButtonHandler radioHandler;
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
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiElement#getCheckBoxHandler()
	 */
	@Override
	public CheckboxHandler getCheckBoxHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(checkBoxHandler, this.getClass().getSimpleName(), CheckboxHandler.class.getSimpleName());
		return checkBoxHandler;
	}

	protected void setCheckBoxHandler(CheckboxHandler checkBoxHandler) {
		this.checkBoxHandler = checkBoxHandler;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiElement#getDropdownHandler()
	 */
	@Override
	public DropdownHandler asDropDown() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(null, this.getClass().getSimpleName(), DropdownHandler.class.getSimpleName());;
		return null;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiElement#getFrameHandler()
	 */
	@Override
	public ElementFrameHandler getFrameHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(frameHandler, this.getClass().getSimpleName(), ElementFrameHandler.class.getSimpleName());;
		return frameHandler;
	}

	protected void setFrameHandler(ElementFrameHandler frameHandler) {
		this.frameHandler = frameHandler;
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

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiElement#getRadioHandler()
	 */
	@Override
	public RadioButtonHandler getRadioHandler() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(radioHandler, this.getClass().getSimpleName(), RadioButtonHandler.class.getSimpleName());;
		return radioHandler;
	}

	protected void setRadioHandler(RadioButtonHandler radioHandler) {
		this.radioHandler = radioHandler;
	}
	
	
	@Override
	public ElementFinder getElementFinder() throws Exception {
		HandlerUtils.throwUnsupportedComponentExceptionForNullObject(elementFinder, this.getClass().getSimpleName(), ElementFinder.class.getSimpleName());
		return this.elementFinder;
	}
	
	protected void setElementFinder(ElementFinder elementFinder) {
		this.elementFinder = elementFinder;
	}

}