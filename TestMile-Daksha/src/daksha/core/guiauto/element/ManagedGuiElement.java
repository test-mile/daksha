package daksha.core.guiauto.element;

import daksha.core.guiauto.enums.GuiElementLoaderType;
import daksha.core.guiauto.identifier.GuiElementMetaData;

public interface ManagedGuiElement {
	
	String getName();

	void setName(String name);

	String getCompositeGuiName();

	void setCompositeGuiName(String name);
	
	void setLoaderType(GuiElementLoaderType type);
	
	GuiElementLoaderType getLoaderType();

	String getGuiLabel();

	void setGuiLabel(String label);

	GuiElementMetaData getMetaData();
	
	void setMetaData(GuiElementMetaData gei);

}
