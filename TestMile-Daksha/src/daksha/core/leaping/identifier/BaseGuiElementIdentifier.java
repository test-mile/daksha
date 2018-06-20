package daksha.core.leaping.identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daksha.core.leaping.automator.ConcreteGuiAutomator;
import daksha.core.leaping.automator.proxy.GuiAutomatorProxy;
import daksha.core.leaping.element.ConcreteGuiElement;
import daksha.core.leaping.element.ConcreteMultiGuiElement;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.pageobject.Page;

public abstract class BaseGuiElementIdentifier<D,E> implements GuiElementIdentifier<D,E>{
	private ConcreteGuiAutomator<D,E> automator = null;
	
	public BaseGuiElementIdentifier(ConcreteGuiAutomator<D,E> automator) {
		this.automator = automator;
	}
	
	protected ConcreteGuiAutomator<D,E> getGuiAutomator() {
		return automator;
	}

	protected GuiAutomatorProxy getGuiAutomatorProxy() {
		return automator.getProxy();
	}
	
	protected abstract void setConcreteElement(Page page, MultiGuiElementProxy proxy);
	protected abstract void setConcreteElement(Page page, GuiElementProxy proxy) throws Exception;
	protected abstract void setConcreteElement(MultiGuiElementProxy proxy);
	protected abstract void setConcreteElement(GuiElementProxy proxy) throws Exception;

	private GuiElementMetaData createMetaDataObject(String idType, String idValue) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put(idType, idValue);
		GuiElementMetaData metaData = new DefaultGuiElementMetaData(map);
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
	public GuiElementProxy elementWithXImageSource(String path) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_IMAGE_SRC", path));
	}

	@Override
	public GuiElementProxy elementOfXType(GuiElementType type) throws Exception {
		return this.createProxy(this.createMetaDataObject("X_TYPE", type.toString()));
	}

	@Override
	public GuiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		return this.createProxy(this.createMetaDataObject("IMAGE", imagePath));
	}
	
	@Override
	public MultiGuiElementProxy elementsWithId(String id) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("ID", id));
	}

	@Override
	public MultiGuiElementProxy elementsWithName(String name) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("NAME", name));
	}

	@Override
	public MultiGuiElementProxy elementsWithClass(String klass) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("CLASS", klass));
	}

	@Override
	public MultiGuiElementProxy elementsWithCss(String cssSelector) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("CSS", cssSelector));
	}
	
	@Override
	public MultiGuiElementProxy elementsWithTagName(String tag) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("TAG", tag));
	}

	@Override
	public MultiGuiElementProxy elementsWithLinkText(String text) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("LINK_TEXT", text));
	}

	@Override
	public MultiGuiElementProxy elementsWithPartialLinkText(String textContent) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("PARTIAL_LINK_TEXT", textContent));
	}

	@Override
	public MultiGuiElementProxy elementsWithXPath(String xpath) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("XPATH", xpath));
	}

	@Override
	public MultiGuiElementProxy elementsWithXText(String text) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_TEXT", text));
	}

	@Override
	public MultiGuiElementProxy elementsWithXPartialText(String textContent) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_PARTIAL_TEXT", textContent));
	}

	@Override
	public MultiGuiElementProxy elementsWithXValue(String value) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_VALUE", value));
	}

	@Override
	public MultiGuiElementProxy elementsWithXImageSource(String path) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_IMAGE_SRC", path));
	}

	@Override
	public MultiGuiElementProxy elementsOfXType(GuiElementType type) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("X_TYPE", type.toString()));
	}

	@Override
	public MultiGuiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("IMAGE", imagePath));
	}
	
	@Override
	public GuiElementProxy createProxy(GuiElementMetaData emd) throws Exception {
		GuiElementProxy proxy = new GuiElementProxy(this.getGuiAutomatorProxy(), emd);
		setConcreteElement(proxy);
		proxy.setLoaderType(ElementLoaderType.AUTOMATOR);
		return proxy;
	}

	@Override
	public MultiGuiElementProxy createMultiProxy(GuiElementMetaData emd) throws Exception {
		MultiGuiElementProxy proxy = new MultiGuiElementProxy(this.getGuiAutomatorProxy(), emd);
		setConcreteElement(proxy);
		proxy.setLoaderType(ElementLoaderType.AUTOMATOR);
		return proxy;
	}
	
	@Override
	public GuiElementProxy createProxy(Page page, GuiElementMetaData emd) throws Exception {
		GuiElementProxy proxy = new GuiElementProxy(page, this.getGuiAutomatorProxy(), emd);
		setConcreteElement(page, proxy);
		proxy.setLoaderType(this.automator.getElementLoaderType());
		return proxy;
	}

	@Override
	public MultiGuiElementProxy createMultiProxy(Page page, GuiElementMetaData emd) throws Exception {
		MultiGuiElementProxy proxy = new MultiGuiElementProxy(page, this.getGuiAutomatorProxy(), emd);
		setConcreteElement(page, proxy);
		proxy.setLoaderType(this.automator.getElementLoaderType());
		return proxy;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public GuiElementProxy convertToolElementToProxy(Page page, GuiElementMetaData metaData, E element) throws Exception{
		GuiElementProxy proxy = createProxy(page, metaData);
		((ConcreteGuiElement<D,E>) proxy.getConcreteElement()).setToolElement(element);
		return proxy;
	}
	
	@SuppressWarnings("unchecked")
	public MultiGuiElementProxy convertMultiToolElementToProxy(Page page, GuiElementMetaData metaData, List<E> elements) throws Exception{
		MultiGuiElementProxy proxy = createMultiProxy(page, metaData);
		((ConcreteMultiGuiElement<D,E>) proxy.getConcreteElement()).setToolElements(elements);
		return proxy;		
	}
	
	protected abstract List<E> findAllUsingAutomator(D engine, GuiLocator locator) throws Exception;
	protected abstract List<E> findAllUsingElement(E element, GuiLocator locator) throws Exception;
	
	private List<E> identify(IdType type, D engine, E element, GuiElementMetaData emd) throws Exception {
		List<E> toolElements  = null;
		for (GuiLocator locator: emd.getLocators()){
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
	
	public E find(GuiElementMetaData emd) throws Exception {
		return identify(IdType.AUTOMATOR, this.automator.getUiDriverEngine(), null, emd).get(0);
	}
	
	public E find(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception {
		return identify(IdType.ELEMENT, null, element.getToolElement(), emd).get(0);		
	}	
	
	public List<E> findAll(GuiElementMetaData emd) throws Exception {
		return identify(IdType.AUTOMATOR, this.automator.getUiDriverEngine(), null, emd);
	}
	
	public List<E> findAll(ConcreteGuiElement<D,E> element, GuiElementMetaData emd) throws Exception {
		return identify(IdType.ELEMENT, null, element.getToolElement(), emd);		
	}	
	
	@Override
	public void convertToDropDown(GuiElementProxy proxy) throws Exception {
		throw new Exception("Drop down conversion not supported.");
	}
	
	public void throwUnsupportedIndentifierException(String componentName, String methodName, String idString) throws Exception{
//		throwGenericUiAutomatorException(
//				componentName,
//				methodName,
//				ErrorType.UNSUPPORTED_IDENTIFIER,
//				String.format(
//						ErrorType.UNSUPPORTED_IDENTIFIER,
//						idString
//				)
//		);
	}

}

enum IdType{
	AUTOMATOR, ELEMENT
}
