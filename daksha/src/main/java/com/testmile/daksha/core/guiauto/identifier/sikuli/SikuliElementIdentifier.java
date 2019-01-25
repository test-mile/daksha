package com.testmile.daksha.core.guiauto.identifier.sikuli;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.testmile.daksha.core.guiauto.automator.ConcreteGuiAutomator;
import com.testmile.daksha.core.guiauto.element.proxy.GuiElementProxy;
import com.testmile.daksha.core.guiauto.element.proxy.GuiMultiElementProxy;
import com.testmile.daksha.core.guiauto.element.sikuli.SikuliElement;
import com.testmile.daksha.core.guiauto.element.sikuli.SikuliMultiElement;
import com.testmile.daksha.core.guiauto.identifier.BaseGuiElementIdentifier;
import com.testmile.daksha.core.guiauto.identifier.GuiElementLocator;
import com.testmile.daksha.tpi.guiauto.enums.GuiElementType;
import com.testmile.daksha.tpi.guiauto.gui.Gui;

public class SikuliElementIdentifier extends BaseGuiElementIdentifier<Screen,Match>{
	
	public SikuliElementIdentifier(ConcreteGuiAutomator<Screen,Match> automator) {
		super(automator);
	}
	
	@Override
	public GuiElementProxy elementBasedOnImage(String imagePath) throws Exception {
		return this.createProxy(this.createMetaDataObject("IMAGE", imagePath));
	}
	

	@Override
	public GuiMultiElementProxy elementsBasedOnImage(String imagePath) throws Exception {
		return this.createMultiProxy(this.createMetaDataObject("IMAGE", imagePath));
	}
	
	protected void waitUntilPresent(GuiElementLocator locator) throws Exception {
		this.waitUntilVisible(locator);
	}
	
	protected void waitUntilVisible(GuiElementLocator locator) throws Exception {
		Screen screen = new Screen();
		Pattern pattern = new Pattern(locator.getValue());
		// Get the sleep time from configuration
		screen.wait(pattern, (double) (60 / 1000));
	}
	
	protected void waitUntilClickable(GuiElementLocator locator) throws Exception {
		// sikuli does not have this feature
		return;
	}
	
	private List<Match> findElements(Screen screen, GuiElementLocator locator) throws Exception {
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
	
	protected List<Match> findAllUsingAutomator(Screen driver, GuiElementLocator locator) throws Exception{
		Screen screen = new Screen();
		return findElements(screen, locator);
	}

	protected List<Match> findAllUsingElement(Match element, GuiElementLocator locator) throws Exception{
		throw new Exception("Nested element finding is not supported by Sikuli");	
	}
	
	protected GuiElementType getElementType(WebElement wdElement) {
		return GuiElementType.GENERIC;
	}

	protected void setConcreteElement(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SikuliElement(this.getAutomator(), proxy));
	}

	protected void setConcreteElement(Gui gui, GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SikuliElement(this.getAutomator(), proxy));
	}
	
	
	protected void setConcreteElement(GuiMultiElementProxy proxy) {
		proxy.setConcreteElement(new SikuliMultiElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(Gui gui, GuiMultiElementProxy proxy) {
		proxy.setConcreteElement(new SikuliMultiElement(gui, this.getAutomator(), proxy));
	}
}
