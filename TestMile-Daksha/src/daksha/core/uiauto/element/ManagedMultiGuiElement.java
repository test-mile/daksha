package daksha.core.uiauto.element;

public interface ManagedMultiGuiElement extends ManagedGuiElement {
	public ConcreteGuiMultiElement getConcreteElement();
	public void setConcreteElement(ConcreteGuiMultiElement element);

}
