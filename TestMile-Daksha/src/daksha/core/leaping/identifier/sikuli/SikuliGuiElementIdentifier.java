package daksha.core.leaping.identifier.sikuli;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import daksha.core.leaping.automator.ConcreteGuiAutomator;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.core.leaping.element.sikuli.SikuliElement;
import daksha.core.leaping.element.sikuli.SikuliMultiElement;
import daksha.core.leaping.identifier.BaseGuiElementIdentifier;
import daksha.core.leaping.identifier.GuiLocator;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.pageobject.Page;

public class SikuliGuiElementIdentifier extends BaseGuiElementIdentifier<Screen,Match>{
	
	public SikuliGuiElementIdentifier(ConcreteGuiAutomator<Screen,Match> automator) {
		super(automator);
	}

	@Override
	public By getFinderType(String identifier, String idValue) throws Exception {
		return null;
	}
	
	private List<Match> findElements(Screen screen, GuiLocator locator) throws Exception {
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
	
	protected List<Match> findAllUsingAutomator(Screen driver, GuiLocator locator) throws Exception{
		Screen screen = new Screen();
		return findElements(screen, locator);
	}

	protected List<Match> findAllUsingElement(Match element, GuiLocator locator) throws Exception{
		throw new Exception("Nested element finding is not supported by Sikuli");	
	}
	
	protected GuiElementType getElementType(WebElement wdElement) {
		return GuiElementType.GENERIC;
	}

	protected void setConcreteElement(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SikuliElement(this.getGuiAutomator(), proxy));
	}

	protected void setConcreteElement(Page page, GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SikuliElement(this.getGuiAutomator(), proxy));
	}
	
	
	protected void setConcreteElement(MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new SikuliMultiElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(Page page, MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new SikuliMultiElement(page, this.getGuiAutomator(), proxy));
	}
}
