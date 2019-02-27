package com.testmile.daksha.core.guiauto.dropdown;

import com.testmile.daksha.core.guiauto.setu.AbstractGuiAutoAction;
import com.testmile.daksha.tpi.guiauto.DropDown;

public class DropDownAction extends AbstractGuiAutoAction {
	
	public DropDownAction(DropDown element, DropDownActionType action) throws Exception {
		super();		
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", element.getAutomator().getSetuId());
		this.getActionRequest().addArg("elementSetuId", element.getSetuId());
	}

}
