package com.testmile.daksha.core.guiauto.identifier;

import java.util.List;

import com.testmile.daksha.core.guiauto.actions.GuiElementCreationHandler;
import com.testmile.daksha.core.guiauto.element.ConcreteGuiElement;
import com.testmile.daksha.core.guiauto.element.proxy.GuiElementProxy;
import com.testmile.daksha.core.guiauto.element.proxy.GuiMultiElementProxy;
import com.testmile.daksha.tpi.guiauto.element.GuiElement;
import com.testmile.daksha.tpi.guiauto.gui.Gui;

public interface GuiElementIdentifier<D,E> extends GuiElementCreationHandler{
	
	E find(GuiElementMetaData emd) throws Exception;
	E find(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception;	
	
	List<E> findAll(GuiElementMetaData emd) throws Exception;
	List<E> findAll(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception;
	
	GuiElementProxy convertToolElementToProxy(Gui gui, GuiElementMetaData metaData, E element) throws Exception;
	GuiMultiElementProxy convertMultiToolElementToProxy(Gui gui, GuiElementMetaData metaData, List<E> elements) throws Exception;
	
	GuiElementProxy createProxy(GuiElementMetaData emd) throws Exception;
	GuiElementProxy createProxy(Gui gui, GuiElementMetaData emd) throws Exception;
	
	GuiMultiElementProxy createMultiProxy(GuiElementMetaData emd) throws Exception;
	GuiMultiElementProxy createMultiProxy(Gui gui, GuiElementMetaData emd) throws Exception;

	void convertToDropDown(GuiElementProxy proxy) throws Exception;
	
	boolean isPresent(GuiElement element) throws Exception;
	boolean isVisible(GuiElement element) throws Exception;
	boolean isClickable(GuiElement element) throws Exception;

}
