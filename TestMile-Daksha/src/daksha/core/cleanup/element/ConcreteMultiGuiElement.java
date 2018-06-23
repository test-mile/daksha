package daksha.core.cleanup.element;

import java.util.List;

import daksha.core.cleanup.element.proxy.GuiElementProxy;

public interface ConcreteMultiGuiElement<D,E> extends ManagedConcreteGuiElement{

	GuiElementProxy getInstanceAtIndex(int index) throws Exception;
	GuiElementProxy getInstanceByText(String text) throws Exception;
	GuiElementProxy getInstanceByTextContent(String text) throws Exception;
	List<GuiElementProxy> getAllInstances() throws Exception;
	void setToolElements(List<E> elements);
}
