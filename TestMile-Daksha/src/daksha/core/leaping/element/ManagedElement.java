package daksha.core.leaping.element;

import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.identifier.GuiElementMetaData;

public interface ManagedElement {
	
	String getName();

	void setName(String name);

	String getCompositePageName();

	void setCompositePageName(String name);
	
	void setLoaderType(ElementLoaderType type);
	
	ElementLoaderType getLoaderType();

	String getPageLabel();

	void setPageLabel(String label);

	String property(String propName);

	String getProperty(String propName);

	GuiElementMetaData getMetaData();
	
	void setIdentifier(GuiElementMetaData gei);

	void setProperty(String propName, String value);

}
