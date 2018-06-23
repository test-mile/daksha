package daksha.core.cleanup.picker.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import daksha.core.cleanup.automator.ConcreteGuiAutomator;
import daksha.core.cleanup.enums.WebLocateBy;
import daksha.core.cleanup.picker.BaseGuiElementIdentifier;
import daksha.core.cleanup.picker.GuiLocator;
import daksha.tpi.cleanup.enums.GuiElementType;

public abstract class BaseSeleniumGuiElementIdentifier<D extends WebDriver,E extends WebElement> extends BaseGuiElementIdentifier<D,E>{
	WebDriverWait waiter = null;
	
	public BaseSeleniumGuiElementIdentifier(ConcreteGuiAutomator<D,E> automator) throws Exception {
		super(automator);
		D driver = this.getGuiAutomator().getUiDriverEngine();
		waiter = new WebDriverWait((WebDriver) driver, this.getGuiAutomator().getWaitTime());
	}

	private By getBy(GuiLocator locator) throws Exception {
		By findBy = null;
		WebLocateBy idType = null;
		try{
			idType = locator.asWebLocateBy();
		} catch (Throwable e){
			throwUnsupportedIndentifierException(
					"WEBDRIVER_AUTOMATOR",
					"getFinderType",
					locator.asLocateBy().toString());
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
	
	
	protected void waitUntilPresent(GuiLocator locator) throws Exception {
		waiter.until(ExpectedConditions.presenceOfElementLocated(getBy(locator)));
	}
	
	protected void waitUntilVisible(GuiLocator locator) throws Exception {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(getBy(locator)));
	}
	
	protected void waitUntilClickable(GuiLocator locator) throws Exception {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(getBy(locator)));
	}
	
	private List<?> findElements(SearchContext context, GuiLocator locator) throws Exception {
		By finderType = getBy(locator);
		return context.findElements(finderType);
	}
	
	@SuppressWarnings("unchecked")
	protected List<E> findAllUsingAutomator(D driver, GuiLocator locator) throws Exception{
		return (List<E>) findElements(driver, locator);
	}
	
	@SuppressWarnings("unchecked")
	protected List<E> findAllUsingElement(E element, GuiLocator locator) throws Exception{
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
