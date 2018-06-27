package daksha.core.uiauto.element;

public interface ManagedSingleGuiElement extends ManagedGuiElement{

	public ConcreteGuiElement<?,?> getConcreteElement();

	public void setConcreteElement(ConcreteGuiElement<?,?> element);

}
