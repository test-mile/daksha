package daksha.core.leaping.element;

public interface ManagedSingleElement extends ManagedElement{

	public ConcreteGuiElement<?,?> getConcreteElement();

	public void setConcreteElement(ConcreteGuiElement<?,?> element);

}
