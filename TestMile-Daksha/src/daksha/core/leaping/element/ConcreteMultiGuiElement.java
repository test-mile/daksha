package daksha.core.leaping.element;

import java.util.List;

import daksha.core.leaping.element.proxy.GuiElementProxy;

public interface ConcreteMultiGuiElement<D,E> extends ManagedConcreteGuiElement{

	GuiElementProxy getInstanceAtIndex(int index) throws Exception;
	GuiElementProxy getInstanceByText(String text) throws Exception;
	GuiElementProxy getInstanceByTextContent(String text) throws Exception;
	void setToolElements(List<E> elements);
}
