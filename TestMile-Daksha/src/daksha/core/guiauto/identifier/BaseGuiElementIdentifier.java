package daksha.core.guiauto.identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daksha.core.guiauto.automator.ConcreteGuiAutomator;
import daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.guiauto.element.ConcreteGuiElement;
import daksha.core.guiauto.element.ConcreteGuiMultiElement;
import daksha.core.guiauto.element.proxy.GuiElementProxy;
import daksha.core.guiauto.element.proxy.GuiMultiElementProxy;
import daksha.core.guiauto.enums.GuiElementLoaderType;
import daksha.core.guiauto.namestore.StringNVPair;
import daksha.tpi.guiauto.element.GuiElement;
import daksha.tpi.guiauto.enums.GuiElementType;
import daksha.tpi.guiauto.gui.Gui;

public abstract class BaseGuiElementIdentifier<D,E> implements GuiElementIdentifier<D,E>{
	private ConcreteGuiAutomator<D,E> automator = null;
	
	public BaseGuiElementIdentifier(ConcreteGuiAutomator<D,E> automator) {
		this.automator = automator;
	}
	
	protected ConcreteGuiAutomator<D,E> getAutomator() {
		return automator;
	}

	protected GuiAutomatorProxy getAutomatorProxy() {
		return automator.getProxy();
	}
	
	protected abstract void setConcreteElement(Gui gui, GuiMultiElementProxy proxy);
	protected abstract void setConcreteElement(Gui gui, GuiElementProxy proxy) throws Exception;
	protected abstract void setConcreteElement(GuiMultiElementProxy proxy);
	protected abstract void setConcreteElement(GuiElementProxy proxy) throws Exception;

	protected GuiElementMetaData createMetaDataObject(String idType, String idValue) throws Exception {
		List<StringNVPair> locatorInfo = new ArrayList<StringNVPair>();
		locatorInfo.add(new StringNVPair(idType, idValue));
		Map<String, String> map = new HashMap<String, String>();
		map.put(idType, idValue);
		GuiElementMetaData metaData = new DefaultGuiElementMetaData(locatorInfo);
		metaData.process();
		return metaData;
	}
	
	@Override
	public GuiElementProxy elementWithId(String id) throws Exception {
		return this.createProxy(this.createMetaDataObject("ID", id));
	}

	@Override
	public GuiElementProxy elementWithName(String name) throws Exception {
		return this.createProxy(this.createMetaDataObject("NAME", name));
	}

	@Override
	public GuiElementProxy elementWithClass(String klass) throws Exception {
		return this.createProxy(this.createMetaDataObject("CLASS", klass));
	}

	@Override
	public GuiElementProxy elementWithCss(String cssSelector) throws Exception {
		return this.createProxy(this.createMetaDataObject("CSS", cssSelector));
	}
	
	@Override
	public GuiElementProxy elementWithTagName(String tag) throws Exception {
		return this.createProxy(this.createMetaDataObject("TAG", tag));
	}

	@Override
	public GuiElementProxy elementWithLinkText(String text) throws Exception {
		return this.createProxy(this.createMetaDataObject("LINK_TEXT", text));
	}

	@Override
	public GuiElementProxy elementWithPartialLinkText(String textContent) throws Exception {
		return this.createProxy(this.createMetaDataObject("PARTIAL_LINK_TEXT", textContent));
	}

	@Override
	public GuiElementProxy elementWithXPath(String xpath) throws Exception {
		return this.createProxy(this.createMetaDataObject("XPATH", xpath));
	}

	@Override
	public GuiElementProxy elementWithXText(String text) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_TEXT", text));
	}

	@Override
	public GuiElementProxy elementWithXPartialText(String textContent) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_PARTIAL_TEXT", textContent));
	}

	@Override
	public GuiElementProxy elementWithXValue(String value) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_VALUE", value));
	}
	
	@Override
	public GuiElementProxy elementWithXTitle(String value) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_TITLE", value));
	}

	@Override
	public GuiElementProxy elementWithXImageSource(String path) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_IMAGE_SRC", path));
	}

	@Override
	public GuiElementProxy elementOfXType(GuiElementType type) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_TYPE", type.toString()));
	}

	@Override
	public GuiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		throw new Exception("Image based identification not supported for: " + this.getAutomator().getName());
	}
	
	@Override
	public GuiMultiElementProxy elementsWithId(String id) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("ID", id));
	}

	@Override
	public GuiMultiElementProxy elementsWithName(String name) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("NAME", name));
	}

	@Override
	public GuiMultiElementProxy elementsWithClass(String klass) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("CLASS", klass));
	}

	@Override
	public GuiMultiElementProxy elementsWithCss(String cssSelector) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("CSS", cssSelector));
	}
	
	@Override
	public GuiMultiElementProxy elementsWithTagName(String tag) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("TAG", tag));
	}

	@Override
	public GuiMultiElementProxy elementsWithLinkText(String text) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("LINK_TEXT", text));
	}

	@Override
	public GuiMultiElementProxy elementsWithPartialLinkText(String textContent) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("PARTIAL_LINK_TEXT", textContent));
	}

	@Override
	public GuiMultiElementProxy elementsWithXPath(String xpath) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("XPATH", xpath));
	}

	@Override
	public GuiMultiElementProxy elementsWithXText(String text) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_TEXT", text));
	}

	@Override
	public GuiMultiElementProxy elementsWithXPartialText(String textContent) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_PARTIAL_TEXT", textContent));
	}

	@Override
	public GuiMultiElementProxy elementsWithXValue(String value) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_VALUE", value));
	}
	
	@Override
	public GuiMultiElementProxy elementsWithXTitle(String value) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_TITLE", value));
	}

	@Override
	public GuiMultiElementProxy elementsWithXImageSource(String path) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_IMAGE_SRC", path));
	}

	@Override
	public GuiMultiElementProxy elementsOfXType(GuiElementType type) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_TYPE", type.toString()));
	}

	@Override
	public GuiMultiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		throw new Exception("Image based identification not supported for: " + this.getAutomator().getName());
	}
	
	@Override
	public GuiElementProxy createProxy(GuiElementMetaData emd) throws Exception {
		GuiElementProxy proxy = new GuiElementProxy(this.getAutomatorProxy(), emd);
		setConcreteElement(proxy);
		proxy.setLoaderType(GuiElementLoaderType.AUTOMATOR);
		return proxy;
	}

	@Override
	public GuiMultiElementProxy createMultiProxy(GuiElementMetaData emd) throws Exception {
		GuiMultiElementProxy proxy = new GuiMultiElementProxy(this.getAutomatorProxy(), emd);
		setConcreteElement(proxy);
		proxy.setLoaderType(GuiElementLoaderType.AUTOMATOR);
		return proxy;
	}
	
	@Override
	public GuiElementProxy createProxy(Gui gui, GuiElementMetaData emd) throws Exception {
		GuiElementProxy proxy = new GuiElementProxy(gui, this.getAutomatorProxy(), emd);
		setConcreteElement(gui, proxy);
		proxy.setLoaderType(this.automator.getElementLoaderType());
		return proxy;
	}

	@Override
	public GuiMultiElementProxy createMultiProxy(Gui gui, GuiElementMetaData emd) throws Exception {
		GuiMultiElementProxy proxy = new GuiMultiElementProxy(gui, this.getAutomatorProxy(), emd);
		setConcreteElement(gui, proxy);
		proxy.setLoaderType(this.automator.getElementLoaderType());
		return proxy;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public GuiElementProxy convertToolElementToProxy(Gui gui, GuiElementMetaData metaData, E element) throws Exception{
		GuiElementProxy proxy = createProxy(gui, metaData);
		((ConcreteGuiElement<D,E>) proxy.getConcreteElement()).setToolElement(element);
		return proxy;
	}
	
	@SuppressWarnings("unchecked")
	public GuiMultiElementProxy convertMultiToolElementToProxy(Gui gui, GuiElementMetaData metaData, List<E> elements) throws Exception{
		GuiMultiElementProxy proxy = createMultiProxy(gui, metaData);
		((ConcreteGuiMultiElement<D,E>) proxy.getConcreteElement()).setToolElements(elements);
		return proxy;		
	}
	
	protected abstract void waitUntilPresent(GuiElementLocator locator) throws Exception;
	
	protected abstract void waitUntilVisible(GuiElementLocator locator) throws Exception;
	
	protected abstract void waitUntilClickable(GuiElementLocator locator) throws Exception;

	private boolean isPresent(GuiElementLocator locator) {
		try {
			waitUntilPresent(locator);
			return true;
		} catch ( Exception e){
			return false;
		}
		
	}
	
	private boolean isVisible(GuiElementLocator locator) {
		try {
			waitUntilVisible(locator);
			return true;
		} catch ( Exception e){
			return false;
		}
		
	}
	
	private boolean isClickable(GuiElementLocator locator) {
		try {
			waitUntilClickable(locator);
			return true;
		} catch ( Exception e){
			return false;
		}
		
	}
	
	@Override
	public boolean isPresent(GuiElement element) throws Exception {
		boolean present = false;
		for(GuiElementLocator locator: element.getMetaData().getLocators()){
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
	public boolean isVisible(GuiElement element) throws Exception {
		boolean visible = false;
		for(GuiElementLocator locator: element.getMetaData().getLocators()){
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
	public boolean isClickable(GuiElement element) throws Exception {
		boolean clickable = false;
		for(GuiElementLocator locator: element.getMetaData().getLocators()){
			try{
				clickable = isClickable(locator);
				if (clickable) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return clickable;
	}
	
	protected abstract List<E> findAllUsingAutomator(D engine, GuiElementLocator locator) throws Exception;
	protected abstract List<E> findAllUsingElement(E element, GuiElementLocator locator) throws Exception;
	
	private List<E> identify(IdType type, D engine, E element, GuiElementMetaData emd) throws Exception {
		List<E> toolElements  = null;
		for (GuiElementLocator locator: emd.getLocators()){
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
	}
	
	public E find(GuiElementMetaData emd) throws Exception {
		return identify(IdType.AUTOMATOR, this.automator.getGuiDriverEngine(), null, emd).get(0);
	}
	
	public E find(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception {
		return identify(IdType.ELEMENT, null, element.getToolElement(), emd).get(0);		
	}	
	
	public List<E> findAll(GuiElementMetaData emd) throws Exception {
		return identify(IdType.AUTOMATOR, this.automator.getGuiDriverEngine(), null, emd);
	}
	
	public List<E> findAll(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception {
		return identify(IdType.ELEMENT, null, element.getToolElement(), emd);		
	}	
	
	@Override
	public void convertToDropDown(GuiElementProxy proxy) throws Exception {
		throw new Exception("Drop down conversion not supported.");
	}

}

enum IdType{
	AUTOMATOR, ELEMENT
}
