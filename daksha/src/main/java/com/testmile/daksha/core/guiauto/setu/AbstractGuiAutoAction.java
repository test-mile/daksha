package com.testmile.daksha.core.guiauto.setu;

import com.google.gson.Gson;

public abstract class AbstractGuiAutoAction implements GuiAutoAction {
	private static Gson gson = new Gson();
	private GuiActionRequest actionRequest;
	
	public AbstractGuiAutoAction() {
		this.actionRequest = new GuiActionRequest();
	}
	
	public void addArg(String name, Object value) {
		this.getActionRequest().addArg(name, value);
	}
	
	@Override
	public String asJsonString() {
		return gson.toJson(this.getActionRequest(), GuiActionRequest.class);
	}

	protected GuiActionRequest getActionRequest() {
		return actionRequest;
	}
	
}
