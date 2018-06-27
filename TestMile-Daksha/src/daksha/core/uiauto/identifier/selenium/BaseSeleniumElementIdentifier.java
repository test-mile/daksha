package daksha.core.uiauto.identifier.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import daksha.core.uiauto.automator.ConcreteGuiAutomator;
import daksha.core.uiauto.enums.WebLocateBy;
import daksha.core.uiauto.identifier.BaseGuiElementIdentifier;
import daksha.core.uiauto.identifier.GuiElementLocator;
import daksha.tpi.uiauto.enums.GuiElementType;

public abstract class BaseSeleniumElementIdentifier<D extends WebDriver,E extends WebElement> extends BaseGuiElementIdentifier<D,E>{
	WebDriverWait waiter = null;
	
	public BaseSeleniumElementIdentifier(ConcreteGuiAutomator<D,E> automator) throws Exception {
		super(automator);
		D driver = this.getAutomator().getGuiDriverEngine();
		waiter = new WebDriverWait((WebDriver) driver, this.getAutomator().getWaitTime());
	}

	private By getBy(GuiElementLocator locator) throws Exception {
		By findBy = null;
		WebLocateBy idType = null;
		try{
			idType = locator.asWebLocateBy();
		} catch (Throwable e){
			throw new Exception(
					String.format("Unsupported locator for %s: ",
					"WEBDRIVER",
					locator.asLocateBy().toString()));
		}
		
		String idValue = locator.getValue();
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
	
	
	protected void waitUntilPresent(GuiElementLocator locator) throws Exception {
		waiter.until(ExpectedConditions.presenceOfElementLocated(getBy(locator)));
	}
	
	protected void waitUntilVisible(GuiElementLocator locator) throws Exception {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(getBy(locator)));
	}
	
	protected void waitUntilClickable(GuiElementLocator locator) throws Exception {
		waiter.until(ExpectedConditions.elementToBeClickable(getBy(locator)));
	}
	
	private List<?> findElements(SearchContext context, GuiElementLocator locator) throws Exception {
		By finderType = getBy(locator);
		return context.findElements(finderType);
	}
	
	@SuppressWarnings("unchecked")
	protected List<E> findAllUsingAutomator(D driver, GuiElementLocator locator) throws Exception{
		return (List<E>) findElements(driver, locator);
	}
	
	@SuppressWarnings("unchecked")
	protected List<E> findAllUsingElement(E element, GuiElementLocator locator) throws Exception{
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
