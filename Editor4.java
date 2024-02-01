import java.awt.Color;

/**
 * Demonstrates the morphing operation featured by Runigram.java. 
 * The program recieves two command-line arguments: a string representing the name
 * of the PPM file of a source image, and a Int representing the number N times
 * Of how mahy steps the source image will turn into the grayscale image.
 * 
 *  
 * */
public class Editor4 {

	public static void main (String[] args) {
		String source = args[0];
		int n = Integer.parseInt(args[1]);
		Color[][] sourceImage = Runigram.read(source);
		Color[][] targetGrayScale = Runigram.grayScaled(sourceImage);
		// Color[][] targetImage = Runigram.read(target);
		Runigram.setCanvas(sourceImage);
		Runigram.morph(sourceImage, targetGrayScale, n);
	}
}
