package daksha.core.meaningful.element;

public interface ManagedMultiGuiElement extends ManagedGuiElement {
	public ConcreteGuiMultiElement getConcreteElement();
	public void setConcreteElement(ConcreteGuiMultiElement element);

}
