package daksha.core.meaningful.element;

import java.util.List;

import daksha.core.meaningful.element.proxy.GuiElementProxy;

public interface ConcreteGuiMultiElement<D,E> extends ManagedConcreteGuiElement{

	GuiElementProxy getInstanceAtIndex(int index) throws Exception;
	GuiElementProxy getInstanceByText(String text) throws Exception;
	GuiElementProxy getInstanceByTextContent(String text) throws Exception;
	List<GuiElementProxy> getAllInstances() throws Exception;
	void setToolElements(List<E> elements);
}
