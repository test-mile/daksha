package daksha.core.cleanup.element;

public interface ManagedUiSingleElement extends ManagedUiElement{

	public ConcreteUiElement<?,?> getConcreteElement();

	public void setConcreteElement(ConcreteUiElement<?,?> element);

}
