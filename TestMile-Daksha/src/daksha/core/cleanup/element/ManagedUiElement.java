package daksha.core.cleanup.element;

import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.picker.UiElementMetaData;

public interface ManagedUiElement {
	
	String getName();

	void setName(String name);

	String getCompositeUiName();

	void setCompositeUiName(String name);
	
	void setLoaderType(ElementLoaderType type);
	
	ElementLoaderType getLoaderType();

	String getUiLabel();

	void setUiLabel(String label);

	String property(String propName);

	String getProperty(String propName);

	UiElementMetaData getMetaData();
	
	void setMetaData(UiElementMetaData gei);

	void setProperty(String propName, String value);

}
