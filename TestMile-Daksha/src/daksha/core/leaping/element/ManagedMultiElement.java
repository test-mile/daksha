package daksha.core.leaping.element;

import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.tpi.leaping.element.GuiElement;

public interface ManagedMultiElement extends ManagedElement {
	public ConcreteMultiGuiElement getConcreteElement();
	public void setConcreteElement(ConcreteMultiGuiElement element);

}
