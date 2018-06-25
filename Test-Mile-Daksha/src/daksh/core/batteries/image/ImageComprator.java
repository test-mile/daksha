package daksh.core.batteries.image;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.sikuli.script.Finder;
import org.sikuli.script.Pattern;

public class ImageComprator {
	private double minScore;
	
	public ImageComprator(double minMatchScore) {
		this.minScore = minMatchScore;
	}
	
	
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

	private double getMinScore() throws Exception {
		return this.minScore;
	}

	
	public boolean areImagesSimilar(String leftImagePath, File rightImage) throws Exception {
		return areImagesSimilar(new File(leftImagePath), rightImage, getMinScore());
	}

	
	public boolean areImagesSimilar(String leftImagePath, File rightImage, Double minScore) throws Exception {
		return areImagesSimilar(new File(leftImagePath), rightImage, minScore);
	}

	
	public boolean areImagesSimilar(String leftImagePath, String rightImagePath) throws Exception {
		return areImagesSimilar(new File(leftImagePath), new File(rightImagePath), getMinScore());
	}

	
	public boolean areImagesSimilar(File leftImage, File rightImage) throws Exception {
		return areImagesSimilar(leftImage, rightImage, getMinScore());
	}

}
