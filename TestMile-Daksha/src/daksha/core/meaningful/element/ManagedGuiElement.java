package daksha.core.meaningful.element;

import daksha.core.meaningful.enums.GuiElementLoaderType;
import daksha.core.meaningful.identifier.GuiElementMetaData;

public interface ManagedGuiElement {
	
	String getName();

	void setName(String name);

	String getCompositeGuiName();

	void setCompositeGuiName(String name);
	
	void setLoaderType(GuiElementLoaderType type);
	
	GuiElementLoaderType getLoaderType();

	String getGuiLabel();

	void setGuiLabel(String label);

	String property(String propName);

	String getProperty(String propName);

	GuiElementMetaData getMetaData();
	
	void setMetaData(GuiElementMetaData gei);

	void setProperty(String propName, String value);

}
