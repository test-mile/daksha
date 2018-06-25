package daksha.core.cleanup.picker.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import daksha.core.cleanup.automator.ConcreteUiAutomator;
import daksha.core.cleanup.enums.WebPickBy;
import daksha.core.cleanup.picker.BaseUiElementPicker;
import daksha.core.cleanup.picker.UiElementLocator;
import daksha.tpi.cleanup.enums.UiElementType;

public abstract class BaseSeleniumElementPicker<D extends WebDriver,E extends WebElement> extends BaseUiElementPicker<D,E>{
	WebDriverWait waiter = null;
	
	public BaseSeleniumElementPicker(ConcreteUiAutomator<D,E> automator) throws Exception {
		super(automator);
		D driver = this.getAutomator().getUiDriverEngine();
		waiter = new WebDriverWait((WebDriver) driver, this.getAutomator().getWaitTime());
	}

	private By getBy(UiElementLocator locator) throws Exception {
		By findBy = null;
		WebPickBy idType = null;
		try{
			idType = locator.asWebPickBy();
		} catch (Throwable e){
			throw new Exception(
					String.format("Unsupported locator for %s: ",
					"WEBDRIVER",
					locator.asPickBy().toString()));
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
	
	
	protected void waitUntilPresent(UiElementLocator locator) throws Exception {
		waiter.until(ExpectedConditions.presenceOfElementLocated(getBy(locator)));
	}
	
	protected void waitUntilVisible(UiElementLocator locator) throws Exception {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(getBy(locator)));
	}
	
	protected void waitUntilClickable(UiElementLocator locator) throws Exception {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(getBy(locator)));
	}
	
	private List<?> findElements(SearchContext context, UiElementLocator locator) throws Exception {
		By finderType = getBy(locator);
		return context.findElements(finderType);
	}
	
	@SuppressWarnings("unchecked")
	protected List<E> findAllUsingAutomator(D driver, UiElementLocator locator) throws Exception{
		return (List<E>) findElements(driver, locator);
	}
	
	@SuppressWarnings("unchecked")
	protected List<E> findAllUsingElement(E element, UiElementLocator locator) throws Exception{
		return (List<E>) findElements(element, locator);		
	}
	
	protected UiElementType getElementType(WebElement wdElement) {
		String tagName = wdElement.getTagName().toLowerCase();
		if (tagName.equals("select")){
			return UiElementType.DROPDOWN;
		} else if (tagName.equals("input") && wdElement.getAttribute("type").toLowerCase().equals("radio") ){
			return UiElementType.RADIO;
		} else {
			return UiElementType.GENERIC;
		}
	}
}
