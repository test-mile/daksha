package com.testmile.daksha.core.guiauto.radiogroup;

import com.testmile.daksha.core.guiauto.setu.AbstractGuiAutoAction;
import com.testmile.daksha.tpi.guiauto.RadioGroup;

public class RadioGroupAction extends AbstractGuiAutoAction {
	
	public RadioGroupAction(RadioGroup element, RadioGroupActionType action) throws Exception {
		super();		
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", element.getAutomator().getSetuId());
		this.getActionRequest().addArg("elementSetuId", element.getSetuId());
	}

}
