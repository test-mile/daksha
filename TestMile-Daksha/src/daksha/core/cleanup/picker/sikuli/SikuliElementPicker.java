package daksha.core.cleanup.picker.sikuli;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import daksha.core.cleanup.automator.ConcreteUiAutomator;
import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.element.proxy.UiMultiElementProxy;
import daksha.core.cleanup.element.sikuli.SikuliElement;
import daksha.core.cleanup.element.sikuli.SikuliMultiElement;
import daksha.core.cleanup.picker.BaseUiElementPicker;
import daksha.core.cleanup.picker.UiElementLocator;
import daksha.tpi.cleanup.enums.UiElementType;
import daksha.tpi.cleanup.ui.UI;

public class SikuliElementPicker extends BaseUiElementPicker<Screen,Match>{
	
	public SikuliElementPicker(ConcreteUiAutomator<Screen,Match> automator) {
		super(automator);
	}
	
	@Override
	public UiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		return this.createProxy(this.createMetaDataObject("IMAGE", imagePath));
	}
	

	@Override
	public UiMultiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("IMAGE", imagePath));
	}
	
	protected void waitUntilPresent(UiElementLocator locator) throws Exception {
		this.waitUntilVisible(locator);
	}
	
	protected void waitUntilVisible(UiElementLocator locator) throws Exception {
		Screen screen = new Screen();
		Pattern pattern = new Pattern(locator.getValue());
		// Get the sleep time from configuration
		screen.wait(pattern, (double) (60 / 1000));
	}
	
	protected void waitUntilClickable(UiElementLocator locator) throws Exception {
		// sikuli does not have this feature
		return;
	}
	
	private List<Match> findElements(Screen screen, UiElementLocator locator) throws Exception {
		String imgPath = null;
		try {
			locator.asVisualLocateBy();
			imgPath = locator.getValue();
		} catch (Exception e) {
			throw new Exception("Unsupported locator");
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
	
	protected List<Match> findAllUsingAutomator(Screen driver, UiElementLocator locator) throws Exception{
		Screen screen = new Screen();
		return findElements(screen, locator);
	}

	protected List<Match> findAllUsingElement(Match element, UiElementLocator locator) throws Exception{
		throw new Exception("Nested element finding is not supported by Sikuli");	
	}
	
	protected UiElementType getElementType(WebElement wdElement) {
		return UiElementType.GENERIC;
	}

	protected void setConcreteElement(UiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SikuliElement(this.getAutomator(), proxy));
	}

	protected void setConcreteElement(UI ui, UiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SikuliElement(this.getAutomator(), proxy));
	}
	
	
	protected void setConcreteElement(UiMultiElementProxy proxy) {
		proxy.setConcreteElement(new SikuliMultiElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(UI ui, UiMultiElementProxy proxy) {
		proxy.setConcreteElement(new SikuliMultiElement(ui, this.getAutomator(), proxy));
	}
}
