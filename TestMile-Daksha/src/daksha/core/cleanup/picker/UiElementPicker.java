package daksha.core.cleanup.picker;

import java.util.List;

import daksha.core.cleanup.actions.UiElementCreationHandler;
import daksha.core.cleanup.element.ConcreteUiElement;
import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.element.proxy.UiMultiElementProxy;
import daksha.tpi.cleanup.element.UiElement;
import daksha.tpi.cleanup.ui.UI;

public interface UiElementPicker<D,E> extends UiElementCreationHandler{
	
	E find(UiElementMetaData emd) throws Exception;
	E find(ConcreteUiElement<D,E> element, UiElementMetaData emd) throws Exception;	
	
	List<E> findAll(UiElementMetaData emd) throws Exception;
	List<E> findAll(ConcreteUiElement<D,E> element, UiElementMetaData emd) throws Exception;
	
	UiElementProxy convertToolElementToProxy(UI ui, UiElementMetaData metaData, E element) throws Exception;
	UiMultiElementProxy convertMultiToolElementToProxy(UI ui, UiElementMetaData metaData, List<E> elements) throws Exception;
	
	UiElementProxy createProxy(UiElementMetaData emd) throws Exception;
	UiElementProxy createProxy(UI ui, UiElementMetaData emd) throws Exception;
	
	UiMultiElementProxy createMultiProxy(UiElementMetaData emd) throws Exception;
	UiMultiElementProxy createMultiProxy(UI ui, UiElementMetaData emd) throws Exception;

	void convertToDropDown(UiElementProxy proxy) throws Exception;
	
	boolean isPresent(UiElement element) throws Exception;
	boolean isVisible(UiElement element) throws Exception;
	boolean isClickable(UiElement element) throws Exception;

}
