package daksha.core.cleanup.picker;

import java.util.List;

import daksha.core.cleanup.actions.GuiElementCreationHandler;
import daksha.core.cleanup.element.ConcreteGuiElement;
import daksha.core.cleanup.element.proxy.GuiElementProxy;
import daksha.core.cleanup.element.proxy.MultiGuiElementProxy;
import daksha.tpi.cleanup.element.GuiElement;
import daksha.tpi.cleanup.gui.Gui;

public interface GuiElementPicker<D,E> extends GuiElementCreationHandler{
	
	E find(GuiElementMetaData emd) throws Exception;
	E find(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception;	
	
	List<E> findAll(GuiElementMetaData emd) throws Exception;
	List<E> findAll(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception;
	
	GuiElementProxy convertToolElementToProxy(Gui gui, GuiElementMetaData metaData, E element) throws Exception;
	MultiGuiElementProxy convertMultiToolElementToProxy(Gui gui, GuiElementMetaData metaData, List<E> elements) throws Exception;
	
	GuiElementProxy createProxy(GuiElementMetaData emd) throws Exception;
	GuiElementProxy createProxy(Gui gui, GuiElementMetaData emd) throws Exception;
	
	MultiGuiElementProxy createMultiProxy(GuiElementMetaData emd) throws Exception;
	MultiGuiElementProxy createMultiProxy(Gui gui, GuiElementMetaData emd) throws Exception;

	void convertToDropDown(GuiElementProxy guiElementProxy) throws Exception;
	
	boolean isPresent(GuiElement element) throws Exception;
	boolean isVisible(GuiElement element) throws Exception;
	boolean isClickable(GuiElement element) throws Exception;

}
