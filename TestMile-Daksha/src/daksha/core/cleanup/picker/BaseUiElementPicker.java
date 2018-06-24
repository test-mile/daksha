package daksha.core.cleanup.picker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daksha.core.cleanup.automator.ConcreteUiAutomator;
import daksha.core.cleanup.automator.proxy.UiAutomatorProxy;
import daksha.core.cleanup.element.ConcreteUiElement;
import daksha.core.cleanup.element.ConcreteUiMultiElement;
import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.element.proxy.UiMultiElementProxy;
import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.tpi.cleanup.element.UiElement;
import daksha.tpi.cleanup.enums.UiElementType;
import daksha.tpi.cleanup.ui.UI;

public abstract class BaseUiElementPicker<D,E> implements UiElementPicker<D,E>{
	private ConcreteUiAutomator<D,E> automator = null;
	
	public BaseUiElementPicker(ConcreteUiAutomator<D,E> automator) {
		this.automator = automator;
	}
	
	protected ConcreteUiAutomator<D,E> getAutomator() {
		return automator;
	}

	protected UiAutomatorProxy getAutomatorProxy() {
		return automator.getProxy();
	}
	
	protected abstract void setConcreteElement(UI ui, UiMultiElementProxy proxy);
	protected abstract void setConcreteElement(UI ui, UiElementProxy proxy) throws Exception;
	protected abstract void setConcreteElement(UiMultiElementProxy proxy);
	protected abstract void setConcreteElement(UiElementProxy proxy) throws Exception;

	protected UiElementMetaData createMetaDataObject(String idType, String idValue) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put(idType, idValue);
		UiElementMetaData metaData = new DefaultUiElementMetaData(map);
		metaData.process();
		return metaData;
	}
	
	@Override
	public UiElementProxy elementWithId(String id) throws Exception {
		return this.createProxy(this.createMetaDataObject("ID", id));
	}

	@Override
	public UiElementProxy elementWithName(String name) throws Exception {
		return this.createProxy(this.createMetaDataObject("NAME", name));
	}

	@Override
	public UiElementProxy elementWithClass(String klass) throws Exception {
		return this.createProxy(this.createMetaDataObject("CLASS", klass));
	}

	@Override
	public UiElementProxy elementWithCss(String cssSelector) throws Exception {
		return this.createProxy(this.createMetaDataObject("CSS", cssSelector));
	}
	
	@Override
	public UiElementProxy elementWithTagName(String tag) throws Exception {
		return this.createProxy(this.createMetaDataObject("TAG", tag));
	}

	@Override
	public UiElementProxy elementWithLinkText(String text) throws Exception {
		return this.createProxy(this.createMetaDataObject("LINK_TEXT", text));
	}

	@Override
	public UiElementProxy elementWithPartialLinkText(String textContent) throws Exception {
		return this.createProxy(this.createMetaDataObject("PARTIAL_LINK_TEXT", textContent));
	}

	@Override
	public UiElementProxy elementWithXPath(String xpath) throws Exception {
		return this.createProxy(this.createMetaDataObject("XPATH", xpath));
	}

	@Override
	public UiElementProxy elementWithXText(String text) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_TEXT", text));
	}

	@Override
	public UiElementProxy elementWithXPartialText(String textContent) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_PARTIAL_TEXT", textContent));
	}

	@Override
	public UiElementProxy elementWithXValue(String value) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_VALUE", value));
	}
	
	@Override
	public UiElementProxy elementWithXTitle(String value) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_TITLE", value));
	}

	@Override
	public UiElementProxy elementWithXImageSource(String path) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_IMAGE_SRC", path));
	}

	@Override
	public UiElementProxy elementOfXType(UiElementType type) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_TYPE", type.toString()));
	}

	@Override
	public UiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		throw new Exception("Image based identification not supported for: " + this.getAutomator().getName());
	}
	
	@Override
	public UiMultiElementProxy elementsWithId(String id) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("ID", id));
	}

	@Override
	public UiMultiElementProxy elementsWithName(String name) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("NAME", name));
	}

	@Override
	public UiMultiElementProxy elementsWithClass(String klass) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("CLASS", klass));
	}

	@Override
	public UiMultiElementProxy elementsWithCss(String cssSelector) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("CSS", cssSelector));
	}
	
	@Override
	public UiMultiElementProxy elementsWithTagName(String tag) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("TAG", tag));
	}

	@Override
	public UiMultiElementProxy elementsWithLinkText(String text) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("LINK_TEXT", text));
	}

	@Override
	public UiMultiElementProxy elementsWithPartialLinkText(String textContent) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("PARTIAL_LINK_TEXT", textContent));
	}

	@Override
	public UiMultiElementProxy elementsWithXPath(String xpath) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("XPATH", xpath));
	}

	@Override
	public UiMultiElementProxy elementsWithXText(String text) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_TEXT", text));
	}

	@Override
	public UiMultiElementProxy elementsWithXPartialText(String textContent) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_PARTIAL_TEXT", textContent));
	}

	@Override
	public UiMultiElementProxy elementsWithXValue(String value) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_VALUE", value));
	}
	
	@Override
	public UiMultiElementProxy elementsWithXTitle(String value) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_TITLE", value));
	}

	@Override
	public UiMultiElementProxy elementsWithXImageSource(String path) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_IMAGE_SRC", path));
	}

	@Override
	public UiMultiElementProxy elementsOfXType(UiElementType type) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_TYPE", type.toString()));
	}

	@Override
	public UiMultiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		throw new Exception("Image based identification not supported for: " + this.getAutomator().getName());
	}
	
	@Override
	public UiElementProxy createProxy(UiElementMetaData emd) throws Exception {
		UiElementProxy proxy = new UiElementProxy(this.getAutomatorProxy(), emd);
		setConcreteElement(proxy);
		proxy.setLoaderType(ElementLoaderType.AUTOMATOR);
		return proxy;
	}

	@Override
	public UiMultiElementProxy createMultiProxy(UiElementMetaData emd) throws Exception {
		UiMultiElementProxy proxy = new UiMultiElementProxy(this.getAutomatorProxy(), emd);
		setConcreteElement(proxy);
		proxy.setLoaderType(ElementLoaderType.AUTOMATOR);
		return proxy;
	}
	
	@Override
	public UiElementProxy createProxy(UI ui, UiElementMetaData emd) throws Exception {
		UiElementProxy proxy = new UiElementProxy(ui, this.getAutomatorProxy(), emd);
		setConcreteElement(ui, proxy);
		proxy.setLoaderType(this.automator.getElementLoaderType());
		return proxy;
	}

	@Override
	public UiMultiElementProxy createMultiProxy(UI ui, UiElementMetaData emd) throws Exception {
		UiMultiElementProxy proxy = new UiMultiElementProxy(ui, this.getAutomatorProxy(), emd);
		setConcreteElement(ui, proxy);
		proxy.setLoaderType(this.automator.getElementLoaderType());
		return proxy;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public UiElementProxy convertToolElementToProxy(UI ui, UiElementMetaData metaData, E element) throws Exception{
		UiElementProxy proxy = createProxy(ui, metaData);
		((ConcreteUiElement<D,E>) proxy.getConcreteElement()).setToolElement(element);
		return proxy;
	}
	
	@SuppressWarnings("unchecked")
	public UiMultiElementProxy convertMultiToolElementToProxy(UI ui, UiElementMetaData metaData, List<E> elements) throws Exception{
		UiMultiElementProxy proxy = createMultiProxy(ui, metaData);
		((ConcreteUiMultiElement<D,E>) proxy.getConcreteElement()).setToolElements(elements);
		return proxy;		
	}
	
	protected abstract void waitUntilPresent(UiElementLocator locator) throws Exception;
	
	protected abstract void waitUntilVisible(UiElementLocator locator) throws Exception;
	
	protected abstract void waitUntilClickable(UiElementLocator locator) throws Exception;

	private boolean isPresent(UiElementLocator locator) {
		try {
			waitUntilPresent(locator);
			return true;
		} catch ( Exception e){
			return false;
		}
		
	}
	
	private boolean isVisible(UiElementLocator locator) {
		try {
			waitUntilVisible(locator);
			return true;
		} catch ( Exception e){
			return false;
		}
		
	}
	
	private boolean isClickable(UiElementLocator locator) {
		try {
			waitUntilClickable(locator);
			return true;
		} catch ( Exception e){
			return false;
		}
		
	}
	
	@Override
	public boolean isPresent(UiElement element) throws Exception {
		boolean present = false;
		for(UiElementLocator locator: element.getMetaData().getLocators()){
			try{
				present = isPresent(locator);
				if (present) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return present;
	}
		
	@Override
	public boolean isVisible(UiElement element) throws Exception {
		boolean visible = false;
		for(UiElementLocator locator: element.getMetaData().getLocators()){
			try{
				visible = isVisible(locator);
				if (visible) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return visible;
	}
	
	@Override
	public boolean isClickable(UiElement element) throws Exception {
		boolean clickable = false;
		for(UiElementLocator locator: element.getMetaData().getLocators()){
			try{
				clickable = isClickable(locator);
				if (clickable) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return clickable;
	}
	
	protected abstract List<E> findAllUsingAutomator(D engine, UiElementLocator locator) throws Exception;
	protected abstract List<E> findAllUsingElement(E element, UiElementLocator locator) throws Exception;
	
	private List<E> identify(IdType type, D engine, E element, UiElementMetaData emd) throws Exception {
		List<E> toolElements  = null;
		for (UiElementLocator locator: emd.getLocators()){
			try{
				switch (type) {
				case AUTOMATOR:
					toolElements = findAllUsingAutomator(engine, locator);
					break;
				case ELEMENT:
					toolElements = findAllUsingElement(element, locator);
					break;
				}
				break;
			} catch (Exception f){
				//Do nothing
			}
		}
		if (toolElements == null){
			throw new Exception("Element Identification failed.");
		}
		
		return toolElements;
//		setElementForUiElement(wdElement);
	}
	
	public E find(UiElementMetaData emd) throws Exception {
		return identify(IdType.AUTOMATOR, this.automator.getUiDriverEngine(), null, emd).get(0);
	}
	
	public E find(ConcreteUiElement<D,E> element, UiElementMetaData emd) throws Exception {
		return identify(IdType.ELEMENT, null, element.getToolElement(), emd).get(0);		
	}	
	
	public List<E> findAll(UiElementMetaData emd) throws Exception {
		return identify(IdType.AUTOMATOR, this.automator.getUiDriverEngine(), null, emd);
	}
	
	public List<E> findAll(ConcreteUiElement<D,E> element, UiElementMetaData emd) throws Exception {
		return identify(IdType.ELEMENT, null, element.getToolElement(), emd);		
	}	
	
	@Override
	public void convertToDropDown(UiElementProxy proxy) throws Exception {
		throw new Exception("Drop down conversion not supported.");
	}

}

enum IdType{
	AUTOMATOR, ELEMENT
}
