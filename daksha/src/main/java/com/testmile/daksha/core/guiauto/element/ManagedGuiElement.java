package com.testmile.daksha.core.guiauto.element;

import com.testmile.daksha.core.guiauto.enums.GuiElementLoaderType;
import com.testmile.daksha.core.guiauto.identifier.GuiElementMetaData;

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
