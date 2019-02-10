package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

public abstract class GuiElementLocateBy<B> {
	private String by;
	private String value;
	
	public GuiElementLocateBy(String by, String value) {
		this.by = by;
		this.value = value;
	}
	
	public abstract B getByObject() throws Exception;
	
	protected String getBy() {
		return this.by;
	}
	
	public String getValue() {
		return value;
	}

}
