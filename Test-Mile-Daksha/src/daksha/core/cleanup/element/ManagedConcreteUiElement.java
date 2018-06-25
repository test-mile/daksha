package daksha.core.cleanup.element;

public interface ManagedConcreteUiElement {
	String getAutomatorName();
	void setAutomatorName(String name);

	void identify() throws Exception;
	void identifyIfNull() throws Exception;
	int getUiElementCount() throws Exception;
}
