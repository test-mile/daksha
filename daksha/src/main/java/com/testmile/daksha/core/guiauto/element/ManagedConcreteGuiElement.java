package com.testmile.daksha.core.guiauto.element;

public interface ManagedConcreteGuiElement {
	String getAutomatorName();
	void setAutomatorName(String name);

	void identify() throws Exception;
	void identifyIfNull() throws Exception;
	int getGuiElementCount() throws Exception;
}
