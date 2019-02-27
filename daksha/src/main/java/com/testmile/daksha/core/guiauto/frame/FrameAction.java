package com.testmile.daksha.core.guiauto.frame;

import com.testmile.daksha.core.guiauto.setu.AbstractGuiAutoAction;
import com.testmile.daksha.tpi.guiauto.Frame;

public class FrameAction extends AbstractGuiAutoAction {
	
	public FrameAction(Frame element, FrameActionType action) throws Exception {
		super();		
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", element.getAutomator().getSetuId());
		this.getActionRequest().addArg("elementSetuId", element.getSetuId());
	}

}
