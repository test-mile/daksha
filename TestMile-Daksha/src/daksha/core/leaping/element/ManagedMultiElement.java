package daksha.core.leaping.element;

public interface ManagedMultiElement extends ManagedElement {
	public ConcreteMultiGuiElement getConcreteElement();
	public void setConcreteElement(ConcreteMultiGuiElement element);

}
