package daksha.core.leaping.identifier.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.leaping.enums.WebIdentifyBy;
import daksha.core.leaping.identifier.BaseIdentifier;
import daksha.core.leaping.identifier.Locator;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.enums.GuiElementType;

public abstract class BaseSeleniumIdentifier<D extends WebDriver,E extends WebElement> extends BaseIdentifier<D,E>{
	protected GuiAutomator<D,E> automator = null;
	
	public BaseSeleniumIdentifier(GuiAutomator<D,E> automator) {
		super(automator);
	}

	@Override
	public By getFinderType(String identifier, String idValue) throws Exception {
		By findBy = null;
		WebIdentifyBy idType = null;
		try{
			idType = WebIdentifyBy.valueOf(identifier.toUpperCase());
		} catch (Throwable e){
			throwUnsupportedIndentifierException(
					"WEBDRIVER_AUTOMATOR",
					"getFinderType",
					identifier);
		}
		switch(idType){
		case ID: findBy = By.id(idValue); break;
		case NAME: findBy = By.name(idValue); break;
		case CLASS: findBy = By.className(idValue); break;
		case LINK_TEXT: findBy = By.linkText(idValue); break;
		case PARTIAL_LINK_TEXT: findBy = By.partialLinkText(idValue); break;
		case XPATH: findBy = By.xpath(idValue); break;
		case CSS: findBy = By.cssSelector(idValue); break;
		case TAG: findBy = By.tagName(idValue); break;
		}
		return findBy;
	}
	
	private List<?> findElements(SearchContext context, Locator locator) throws Exception {
		By finderType = getFinderType(locator.NAME, locator.VALUE);
		return context.findElements(finderType);
	}
	
	@SuppressWarnings("unchecked")
	protected List<E> findAllUsingAutomator(D driver, Locator locator) throws Exception{
		return (List<E>) findElements(driver, locator);
	}
	
	@SuppressWarnings("unchecked")
	protected List<E> findAllUsingElement(E element, Locator locator) throws Exception{
		return (List<E>) findElements(element, locator);		
	}
	
	protected GuiElementType getElementType(WebElement wdElement) {
		String tagName = wdElement.getTagName().toLowerCase();
		if (tagName.equals("select")){
			return GuiElementType.DROPDOWN;
		} else if (tagName.equals("input") && wdElement.getAttribute("type").toLowerCase().equals("radio") ){
			return GuiElementType.RADIO;
		} else {
			return GuiElementType.GENERIC;
		}
	}
}
