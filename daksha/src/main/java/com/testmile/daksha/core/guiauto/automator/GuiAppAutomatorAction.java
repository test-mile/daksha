package com.testmile.daksha.core.guiauto.automator;

import com.testmile.daksha.core.guiauto.setu.AbstractGuiAutoAction;

public class GuiAppAutomatorAction extends AbstractGuiAutoAction {
	
	public GuiAppAutomatorAction(GuiAppAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
	}

	public GuiAppAutomatorAction(AppAutomator automator, GuiAppAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", automator.getSetuId());
	}

}
