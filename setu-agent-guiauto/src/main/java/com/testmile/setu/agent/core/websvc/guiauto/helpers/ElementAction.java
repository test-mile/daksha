package com.testmile.setu.agent.core.websvc.guiauto.helpers;

class ElementAction extends GuiAutoAction{
	private ElementActionType action;
	
	public ElementAction(String jsonActionStr) {
		super(jsonActionStr);
		this.action = ElementActionType.valueOf(this.getActionTypeStr());
	}

	public ElementActionType getActionType() {
		return action;
	}
	
	public boolean isInstanceAction() throws Exception {
		if (this.getArgs().containsKey("isInstanceAction")){
			return this.getArgs().get("isInstanceAction").asBoolean();
		} else {
			return false;
		}
	}

	public int getInstanceIndex() throws Exception {
		if (!isInstanceAction()) {
			throw new Exception("This is not an instnce action.");
		}
		return this.getArgs().get("instanceIndex").asInt();
	}
}