package daksha.core.leaping.element;

import daksha.core.leaping.element.proxy.GuiElementProxy;

public interface ManagedConcreteGuiElement {
	String getAutomatorName();
	void setAutomatorName(String name);

	void identify() throws Exception;
	void identifyIfNull() throws Exception;
	int getGuiElementCount() throws Exception;
}
