package com.testmile.setu.agent.core.websvc.guiauto.helpers;

class AutomatorAction extends GuiAutoAction{
	private AutomatorActionType action;
	
	public AutomatorAction(String jsonActionStr) {
		super(jsonActionStr);
		this.action = AutomatorActionType.valueOf(this.getActionTypeStr());
	}

	public AutomatorActionType getActionType() {
		return action;
	}

}