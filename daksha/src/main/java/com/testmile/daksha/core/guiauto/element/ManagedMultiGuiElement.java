package com.testmile.daksha.core.guiauto.element;

public interface ManagedMultiGuiElement extends ManagedGuiElement {
	public ConcreteGuiMultiElement getConcreteElement();
	public void setConcreteElement(ConcreteGuiMultiElement element);

}
