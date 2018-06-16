package daksha.core.leaping.identifier.sikuli;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import daksha.core.batteries.config.Batteries;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.core.leaping.element.selenium.DefaultSeleniumElementProxy;
import daksha.core.leaping.element.selenium.SeleniumDropdownElement;
import daksha.core.leaping.element.selenium.SeleniumGenericElement;
import daksha.core.leaping.element.selenium.SeleniumMultiElement;
import daksha.core.leaping.element.sikuli.SikuliElement;
import daksha.core.leaping.element.sikuli.SikuliMultiElement;
import daksha.core.leaping.enums.WebIdentifyBy;
import daksha.core.leaping.element.selenium.BaseSeleniumDropdownElement;
import daksha.core.leaping.identifier.BaseIdentifier;
import daksha.core.leaping.identifier.Locator;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.element.MultiGuiElement;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.pageobject.Page;

public class SikuliIdentifier extends BaseIdentifier<Screen,Match>{
	
	public SikuliIdentifier(GuiAutomator<Screen,Match> automator) {
		super(automator);
	}

	@Override
	public By getFinderType(String identifier, String idValue) throws Exception {
		return null;
	}
	
	private List<Match> findElements(Screen screen, Locator locator) throws Exception {
		String imgPath = null;
		if (locator.NAME.equals("IMAGE")) {
			throw new Exception("Unsupported identifier");
		} else {
			imgPath = locator.VALUE;
		}
		
		
		Pattern pattern = new Pattern(imgPath);
		Iterator<Match> matches = screen.findAll(pattern);
		if (matches != null){
			List<Match> matchedElements = new ArrayList<Match>();
			while(matches.hasNext()){
				matchedElements.add(matches.next());
			}
			return matchedElements;
		} else {
			return null;
		}
	}
	
	protected List<Match> findAllUsingAutomator(Screen driver, Locator locator) throws Exception{
		Screen screen = new Screen();
		return findElements(screen, locator);
	}

	protected List<Match> findAllUsingElement(Match element, Locator locator) throws Exception{
		throw new Exception("Nested element finding is not supported by Sikuli");	
	}
	
	protected GuiElementType getElementType(WebElement wdElement) {
		return GuiElementType.GENERIC;
	}

	protected void setConcreteElement(GuiElementProxy proxy, GuiElementType type) throws Exception {
		proxy.setConcreteElement(new SikuliElement(this.automator, proxy));
	}
	
	protected void setConcreteElement(Page page, GuiElementProxy proxy, GuiElementType type) throws Exception {
		proxy.setConcreteElement(new SikuliElement(this.automator, proxy));
	}
	
	
	protected void setConcreteElement(MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new SikuliMultiElement(this.automator, proxy));
	}
	
	protected void setConcreteElement(Page page, MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new SikuliMultiElement(page, this.automator, proxy));
	}
}
