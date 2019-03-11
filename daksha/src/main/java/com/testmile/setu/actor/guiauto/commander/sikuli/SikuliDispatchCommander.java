/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
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

package com.testmile.setu.actor.guiauto.commander.sikuli;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.sikuli.script.Finder;
import org.sikuli.script.Pattern;

import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.setu.actor.SetuActorConfig;

public class SikuliDispatchCommander {
	
	private static void compareImageFiles(SetuActorConfig config, File leftImage, File rightImage) throws Exception {
		double minScore = config.value(SetuOption.IMAGE_COMPARISON_MIN_SCORE).asDouble();
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
	
	    if ((!imagesMatch) || (score < minScore)){
	    	throw new Exception(String.format("Images not similar: Left:%s Right:%s", leftImage.getAbsolutePath(), rightImage.getAbsolutePath()));
	    }
	}
	
	public static void compareImagesLocatedAt(SetuActorConfig config, String leftImagePath, String rightImagePath) throws Exception {
		compareImageFiles(config, new File(leftImagePath), new File(rightImagePath));
	}

}