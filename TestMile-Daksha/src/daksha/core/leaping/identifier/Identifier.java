package daksha.core.leaping.identifier;

import java.util.List;

import org.openqa.selenium.By;

import daksha.core.leaping.actions.ElementCreationHandler;
import daksha.core.leaping.element.ConcreteGuiElement;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.tpi.leaping.pageobject.Page;

public interface Identifier<D,E> extends ElementCreationHandler{
	By getFinderType(String identifier, String idValue) throws Exception;
	
	E find(GuiElementMetaData emd) throws Exception;
	E find(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception;	
	
	List<E> findAll(GuiElementMetaData emd) throws Exception;
	List<E> findAll(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception;
	
	GuiElementProxy convertToolElementToProxy(Page page, GuiElementMetaData metaData, E element) throws Exception;
	MultiGuiElementProxy convertMultiToolElementToProxy(Page page, GuiElementMetaData metaData, List<E> elements) throws Exception;
	
	GuiElementProxy createProxy(GuiElementMetaData emd) throws Exception;
	GuiElementProxy createProxy(Page page, GuiElementMetaData emd) throws Exception;
	
	GuiElementProxy createDropDownProxy(GuiElementMetaData emd) throws Exception;
	GuiElementProxy createDropDownProxy(Page page, GuiElementMetaData emd) throws Exception;
	
	MultiGuiElementProxy createMultiProxy(GuiElementMetaData emd) throws Exception;
	MultiGuiElementProxy createMultiProxy(Page page, GuiElementMetaData emd) throws Exception;

}
