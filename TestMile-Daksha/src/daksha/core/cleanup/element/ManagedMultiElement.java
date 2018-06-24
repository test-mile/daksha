package daksha.core.cleanup.element;

public interface ManagedMultiElement extends ManagedUiElement {
	public ConcreteUiMultiElement getConcreteElement();
	public void setConcreteElement(ConcreteUiMultiElement element);

}
