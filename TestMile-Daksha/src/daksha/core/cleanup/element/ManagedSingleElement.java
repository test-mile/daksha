package daksha.core.cleanup.element;

public interface ManagedSingleElement extends ManagedElement{

	public ConcreteGuiElement<?,?> getConcreteElement();

	public void setConcreteElement(ConcreteGuiElement<?,?> element);

}
