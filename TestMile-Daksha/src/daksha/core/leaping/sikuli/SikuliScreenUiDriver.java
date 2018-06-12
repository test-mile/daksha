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
package daksha.core.leaping.sikuli;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.sikuli.script.App;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.Daksha;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.ScreenIdentifyBy;
import daksha.core.leaping.enums.UiAutomatorPropertyType;
import daksha.core.leaping.interfaces.UiElementIdentifier;
import daksha.core.leaping.interfaces.Locator;
import daksha.core.leaping.lib.DefaultUiAutomator;
import daksha.core.leaping.lib.DefaultUiElement;
import daksha.core.leaping.sikuli.interfaces.SikuliElementProxy;
import daksha.core.leaping.sikuli.lib.base.DefaultSikuliElementProxy;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.interfaces.SikuliGuiAutomator;
import daksha.tpi.leaping.interfaces.GuiAutomator;
import daksha.tpi.leaping.interfaces.GuiElement;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public class SikuliScreenUiDriver extends DefaultUiAutomator implements GuiAutomator, SikuliGuiAutomator{
	
	public SikuliScreenUiDriver(){
		super(UiAutomationContext.SCREEN);
	}
	
	public SikuliScreenUiDriver(ElementLoaderType loaderType) {
		super(UiAutomationContext.SCREEN, loaderType);
	}
	
	private SikuliElementProxy createProxy(GuiElement element) throws Exception {
		return new DefaultSikuliElementProxy(this, element);
	}

	@Override
	public Object getUiDriverEngine() throws Exception {
		return super.getUiDriverEngine();
	}

	@Override
	public Object getIdentifierType(Map<ScreenIdentifyBy, String> map) throws Exception {
		return ScreenIdentifyBy.IMAGE;
	}

	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage, Double minScore) throws Exception {
	    boolean imagesMatch = false;
	    Double score = 0.0d;
		if (!leftImage.exists()){
			throwImageNotFoundException("areImagesSimilar", "Left", leftImage.getAbsolutePath());
		}
		
		if (!rightImage.exists()){
			throwImageNotFoundException("areImagesSimilar", "Right", rightImage.getAbsolutePath());
		}
	    BufferedImage bufferedLeftImage = ImageIO.read(leftImage);
	    BufferedImage bufferedRightImage = ImageIO.read(rightImage);
	    int leftImageHeight = bufferedLeftImage.getHeight();
	    int leftImageWidth = bufferedLeftImage.getWidth();
	    int rightImageHeight = bufferedRightImage.getHeight();
	    int rightImageWidth = bufferedRightImage.getWidth();
	
	    if (leftImageWidth >= rightImageWidth && leftImageHeight >= rightImageHeight) {
	        Finder finder = new Finder(leftImage.getAbsolutePath());
	        finder.find(new Pattern(rightImage.getAbsolutePath()));
	        if (finder.hasNext()) {
	            score = finder.next().getScore();
	            imagesMatch = true;
	        }
	    } else if (leftImageWidth <= rightImageWidth && rightImageHeight >= leftImageHeight) {
	        Finder finder = new Finder(rightImage.getAbsolutePath());
	        finder.find(new Pattern(leftImage.getAbsolutePath()));
	        if (finder.hasNext()) {
	            score = finder.next().getScore();
	            imagesMatch = true;
	        }
	    } else {
	    	throwImagesNotComparableException("areImagesSimilar", leftImage.getAbsolutePath(), rightImage.getAbsolutePath());
	    }
	
	    if (!imagesMatch){
	    	return false;
	    } else  if (score < minScore){
	    	return false;
	    } else {
	    	return true;
	    }
	}

	private double getDefaultMinScore() throws Exception {
		return Batteries.value(UiAutomatorPropertyType.SIKULI_COMPARISON_SCORE).asDouble();
	}

	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage) throws Exception {
		return areImagesSimilar(new File(leftImagePath), rightImage, getDefaultMinScore());
	}

	@Override
	public boolean areImagesSimilar(String leftImagePath, File rightImage, Double minScore) throws Exception {
		return areImagesSimilar(new File(leftImagePath), rightImage, minScore);
	}

	@Override
	public boolean areImagesSimilar(String leftImagePath, String rightImagePath) throws Exception {
		return areImagesSimilar(new File(leftImagePath), new File(rightImagePath), getDefaultMinScore());
	}

	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage) throws Exception {
		return areImagesSimilar(leftImage, rightImage, getDefaultMinScore());
	}

	protected void throwScreenAutomatorException(String action, String code, String message) throws Exception {
				throw new Problem(
						Batteries.getConfiguredName("COMPONENT_NAMES", "SIKULI_AUTOMATOR"),
				this.getClass().getSimpleName(),
				action,
				code,
				message
				);		
	}

	@Override
	public void throwImageNotFoundException(String methodName, String whichSide, String filePath) throws Exception {
		throwScreenAutomatorException(
				methodName,
				Daksha.problem.COMPARISON_IMAGE_NOT_FOUND,
				Batteries.getProblemText(
						Daksha.problem.COMPARISON_IMAGE_NOT_FOUND,
						whichSide,
						filePath
				)
		);
	}

	@Override
	public void throwImagesNotComparableException(String methodName, String leftImagePath, String rightImagePath) throws Exception {
		throwScreenAutomatorException(
				methodName,
				Daksha.problem.COMPARISON_NOT_POSSIBLE,
				Batteries.getProblemText(
						Daksha.problem.COMPARISON_NOT_POSSIBLE,
						FileSystemUtils.getCanonicalPath(leftImagePath),
						FileSystemUtils.getCanonicalPath(rightImagePath)
				)
		);
	}

	@Override
	public GuiElement declareElement(UiElementIdentifier  elementMetaData) throws Exception {
		GuiElement uiElement = createDefaultElementSkeleton(elementMetaData);
		// This needs to be done in a better way for image recognition based libraries.
		// Some equivalent of By needs to be coded for consistency.
		for (Locator id: elementMetaData.getLocators()){
			if (id.NAME.equals("IMAGE")){
				uiElement.setImagePath(
						FileSystemUtils.getCanonicalPath(Batteries.value(UiAutomatorPropertyType.DIRECTORY_PROJECT_UI_IMAGES).asString() + "/" + id.VALUE));
			}
		}
		SikuliElementProxy proxy = createProxy(uiElement);
		uiElement.setProxy(proxy);
		uiElement.setLoaderType(this.getElementLoaderType());
		proxy.setAutomatorName("Sikuli Automator");
		return uiElement;
	}


	@Override
	public void focusOnApp() throws Exception{
		App app = new App(this.getAppTitle());
		app.focus();
	}


	@Override
	public void click(String imagePath) throws Exception{
		waitForElementVisibility(imagePath);
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		screen.click(pattern);
	}


	@Override
	public void doubleClick(String imagePath)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		screen.doubleClick(pattern);
	}


	@Override
	public void rightClick(String imagePath)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		screen.rightClick(pattern);
	}
	

	@Override
	public void hover(String imagePath)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		screen.hover(pattern);
	}
	

	@Override
	public void hoverAndClick(String imagePath1) throws Exception{
		Screen screen = new Screen();
		Pattern pattern1 = new Pattern(imagePath1);
		screen.hover(pattern1);
		screen.click(pattern1);
	}
	

	@Override
	public void hoverAndClickElement(String imagePath1, String imagePath2) throws Exception{
		Screen screen = new Screen();
		Pattern pattern1 = new Pattern(imagePath1);
		Pattern pattern2 = new Pattern(imagePath1);
		screen.hover(pattern1);
		screen.click(pattern2);
	}
	

	@Override
	public void rightClickAndClickElement(String imagePath1, String imagePath2) throws Exception {
		Screen screen = new Screen();
		Pattern pattern1 = new Pattern(imagePath1);
		Pattern pattern2 = new Pattern(imagePath1);
		screen.rightClick(pattern1);
		screen.click(pattern2);
	}
	

	@Override
	public void setText(String imagePath, String text)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		screen.paste(pattern, text);
	}
	

	@Override
	public void enterText(String imagePath, String text)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		screen.type(pattern, text);
	}
	

	@Override
	public void dragAndDrop(String imagePathToDrag, String imagePathForDrop)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern1 = new Pattern(imagePathToDrag);
		Pattern pattern2 = new Pattern(imagePathForDrop);
		screen.dragDrop(pattern1, pattern2);
	}
	

	@Override
	public void mouseWheelDown(int downCount)  throws Exception{
		Screen screen = new Screen();
		screen.mouseDown(downCount);
	}
	
	

	@Override
	public void mouseWheelDown() throws Exception{
		mouseWheelDown(5);
	}
	

	@Override
	public void mouseWheelUp(int upCount)  throws Exception{
		Screen screen = new Screen();
		screen.mouseUp(upCount);
	}
	
	

	@Override
	public void mouseWheelUp()  throws Exception{
		mouseWheelUp(5);
	}
	

	@Override
	public void waitForElementVisibility(String imagePath)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		// Get the sleep time from configuration
		screen.wait(pattern, (double) (60 / 1000));
	}
	

	@Override
	public void waitForElementInvisibility(String imagePath) throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		// Get the sleep time from configuration
		screen.waitVanish(pattern, (double) (60 / 1000));
	}
	

	@Override
	public boolean isElementPresent(String imagePath)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		Match m = screen.exists(pattern);
		if (m != null) {
			return true;

		} else {
			return false;
		}
	}
	

	@Override
	public boolean isElementAbsent(String imagePath)  throws Exception{
		return !isElementPresent(imagePath);
	}
	

	@Override
	public Match findElement(String imagePath) throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
		return screen.find(pattern);
	}
	

	@Override
	public List<Match> findElements(String imagePath)  throws Exception{
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imagePath);
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
	

	@Override
	public void sendKeysToScreen(String text) throws Exception{
		Screen screen = new Screen();
		screen.type(text);
	}
	

	@Override
	public void waitForElementPresence(String imagePath) throws Exception{
		waitForElementVisibility(imagePath);
	}
	

	@Override
	public void waitForElementAbsence(String imagePath) throws Exception{
		waitForElementInvisibility(imagePath);
	}
	

	@Override
	public void clickIfPresent(String imagePath) throws Exception {
		click(imagePath);
	}
	

	@Override
	public void enterText(Match sikuliElement, String text) throws Exception{
		sikuliElement.type(text);
	}


	@Override
	public void setText(Match sikuliElement, String text) throws Exception{
		sikuliElement.paste(text);
	}


	@Override
	public Object getUnderlyingEngine() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public File takeScreenshot() throws Exception{
		return null;
       //throw new Exception("Not supported yet.");
	}
	

	@Override
	public int getWaitTime() throws Exception{
		return Batteries.value(UiAutomatorPropertyType.SIKULI_MAXWAIT).asInt();
	}
	
	/*
	 * Exceptions
	 */
	
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void clearText(String imagePath) throws Exception {
		this.setText(imagePath, "");
	}

	private GuiElement createDefaultElementSkeleton(UiElementIdentifier elementMetaData) throws Exception {
		return new DefaultUiElement(elementMetaData);
	}

}
