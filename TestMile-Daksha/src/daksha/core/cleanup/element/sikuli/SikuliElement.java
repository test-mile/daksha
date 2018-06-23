/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package daksha.core.cleanup.element.sikuli;

import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import daksha.core.cleanup.automator.ConcreteGuiAutomator;
import daksha.core.cleanup.element.BaseConcreteSingleGuiElement;
import daksha.core.cleanup.element.proxy.GuiElementProxy;
import daksha.core.cleanup.picker.GuiElementMetaData;
import daksha.tpi.cleanup.gui.Gui;

public class SikuliElement extends BaseConcreteSingleGuiElement<Screen,Match>{
	
	public SikuliElement(Gui page, ConcreteGuiAutomator<Screen,Match> automator, GuiElementProxy eProxy) throws Exception{
		super(page, automator, eProxy);
	}	
	
	public SikuliElement(ConcreteGuiAutomator<Screen,Match> automator, GuiElementProxy eProxy) throws Exception{
		this(null, automator, eProxy);
	}
	
	
	private Match asMatch() throws Exception {
		return this.getToolElementWithRetry();
	}
	
	@Override
	public void click() throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(this.getImagePath());
		screen.click(pattern);
	}
	
	@Override
	public void doubleClick()  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(this.getImagePath());
		screen.doubleClick(pattern);
	}
	
	@Override
	public void rightClick()  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(this.getImagePath());
		screen.rightClick(pattern);
	}
	

	@Override
	public void hover()  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(this.getImagePath());
		screen.hover(pattern);
	}
	

	@Override
	public void hoverAndClick() throws Exception{
		Screen screen = new Screen();
		Pattern pattern1 = new Pattern(this.getImagePath());
		screen.hover(pattern1);
		screen.click(pattern1);
	}
	

	private String getImagePathForSecondElement(String name) throws Exception {
		GuiElementMetaData emd = this.getPage().getPageDef().getMetaData(name);
		GuiElementProxy proxy = this.getIdentifier().createProxy(emd);
		return proxy.getImagePath();
	}
	
	@Override
	public void hoverAndClickElement(String name) throws Exception{
		String imgPathForElement = getImagePathForSecondElement(name);
		Screen screen = new Screen();
		Pattern pattern1 = new Pattern(this.getImagePath());
		Pattern pattern2 = new Pattern(imgPathForElement);
		screen.hover(pattern1);
		screen.click(pattern2);
	}
	

	@Override
	public void rightClickAndClickElement(String name) throws Exception {
		String imgPathForElement = getImagePathForSecondElement(name);
		Screen screen = new Screen();
		Pattern pattern1 = new Pattern(this.getImagePath());
		Pattern pattern2 = new Pattern(imgPathForElement);
		screen.rightClick(pattern1);
		screen.click(pattern2);
	}	

	@Override
	public void enterText(String text)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(this.getImagePath());
		screen.type(pattern, text);
	}
	
	@Override
	public void setText(String text)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(this.getImagePath());
		screen.paste(pattern, text);
	}
	
	@Override
	public void clearText() throws Exception {
		this.setText("");
	}

	@Override
	public void dragAndDrop(String name)  throws Exception{
		String imgPathForElement = getImagePathForSecondElement(name);
		Screen screen = new Screen();
		Pattern pattern1 = new Pattern(this.getImagePath());
		Pattern pattern2 = new Pattern(imgPathForElement);
		screen.dragDrop(pattern1, pattern2);
	}

	@Override
	public void waitUntilClickable() throws Exception {
		//do nothing
	}

	@Override
	public boolean isClickable() throws Exception {
		return true;
	}

}
