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
package daksha.core.leaping.automator.sikuli;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.sikuli.script.App;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import daksha.core.batteries.config.TestContext;
import daksha.core.leaping.automator.AbstractGuiAutomator;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.OSType;
import daksha.core.leaping.enums.UiDriverEngine;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.leaping.enums.GuiAutomationContext;

public class SikuliScreenUiDriver extends AbstractGuiAutomator<Screen,Match>{
	
	public SikuliScreenUiDriver(TestContext testContext, ElementLoaderType loaderType) {
		super(testContext, UiDriverEngine.SIKULI, GuiAutomationContext.SCREEN, loaderType);
	}
	
	public SikuliScreenUiDriver(TestContext testContext){
		this(testContext, ElementLoaderType.AUTOMATOR);
	}

	@Override
	public boolean areImagesSimilar(File leftImage, File rightImage, Double minScore) throws Exception {
	    boolean imagesMatch = false;
	    Double score = 0.0d;
		if (!leftImage.exists()){
			throw new Exception("Left comparison image does not exist at path: " + leftImage.getAbsolutePath());
		}
		
		if (!rightImage.exists()){
			throw new Exception("Right comparison image does not exist at path: " + rightImage.getAbsolutePath());
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
	    	throw new Exception(String.format("Images are not comparable: Left:%s Right:%s", leftImage.getAbsolutePath(), rightImage.getAbsolutePath()));
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
		return this.getTestContext().getConfig().value(DakshaOption.SIKULI_COMPARISON_SCORE).asDouble();
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


	@Override
	public void focusOnApp() throws Exception{
		App app = new App(this.getAppTitle());
		app.focus();
	}


	@Override
	public void scrollDown(int downCount)  throws Exception{
		Screen screen = new Screen();
		screen.mouseDown(downCount);
	}
	
	

	@Override
	public void scrollDown() throws Exception{
		scrollDown(5);
	}
	

	@Override
	public void scrollUp(int upCount)  throws Exception{
		Screen screen = new Screen();
		screen.mouseUp(upCount);
	}
	
	@Override
	public void scrollUp() throws Exception{
		scrollUp(5);
	}

	@Override
	public void sendKeysToScreen(String text) throws Exception{
		Screen screen = new Screen();
		screen.type(text);
	}
	
	/*
	 * Exceptions
	 */
	
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWaitTime(int waitTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOSType(OSType platformType) {
		// TODO Auto-generated method stub
		
	}

}
