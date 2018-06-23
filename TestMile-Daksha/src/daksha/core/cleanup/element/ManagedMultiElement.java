package daksha.core.cleanup.element;

public interface ManagedMultiElement extends ManagedElement {
	public ConcreteMultiGuiElement getConcreteElement();
	public void setConcreteElement(ConcreteMultiGuiElement element);

}
