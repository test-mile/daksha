package daksha.core.cleanup.element;

import java.util.List;

import daksha.core.cleanup.element.proxy.UiElementProxy;

public interface ConcreteUiMultiElement<D,E> extends ManagedConcreteUiElement{

	UiElementProxy getInstanceAtIndex(int index) throws Exception;
	UiElementProxy getInstanceByText(String text) throws Exception;
	UiElementProxy getInstanceByTextContent(String text) throws Exception;
	List<UiElementProxy> getAllInstances() throws Exception;
	void setToolElements(List<E> elements);
}
