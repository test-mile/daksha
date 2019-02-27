package com.testmile.daksha.core.guiauto.automator;

import com.testmile.daksha.core.guiauto.setu.AbstractGuiAutoAction;
import com.testmile.daksha.tpi.guiauto.GuiAutomator;

public class GuiAutomatorAction extends AbstractGuiAutoAction {
	
	public GuiAutomatorAction(GuiAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
	}

	public GuiAutomatorAction(GuiAutomator automator, GuiAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", automator.getSetuId());
	}

}
