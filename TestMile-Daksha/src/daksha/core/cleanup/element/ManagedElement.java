package daksha.core.cleanup.element;

import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.picker.GuiElementMetaData;

public interface ManagedElement {
	
	String getName();

	void setName(String name);

	String getCompositeGuiName();

	void setCompositeGuiName(String name);
	
	void setLoaderType(ElementLoaderType type);
	
	ElementLoaderType getLoaderType();

	String getGuiLabel();

	void setGuiLabel(String label);

	String property(String propName);

	String getProperty(String propName);

	GuiElementMetaData getMetaData();
	
	void setMetaData(GuiElementMetaData gei);

	void setProperty(String propName, String value);

}
