package daksha.core.meaningful.identifier;

import java.util.List;

import daksha.core.meaningful.actions.GuiElementCreationHandler;
import daksha.core.meaningful.element.ConcreteGuiElement;
import daksha.core.meaningful.element.proxy.GuiElementProxy;
import daksha.core.meaningful.element.proxy.GuiMultiElementProxy;
import daksha.tpi.meaningful.element.GuiElement;
import daksha.tpi.meaningful.gui.Gui;

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
