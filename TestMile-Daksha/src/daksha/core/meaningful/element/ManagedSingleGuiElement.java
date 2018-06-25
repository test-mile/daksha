package daksha.core.meaningful.element;

public interface ManagedSingleGuiElement extends ManagedGuiElement{

	public ConcreteGuiElement<?,?> getConcreteElement();

	public void setConcreteElement(ConcreteGuiElement<?,?> element);

}
