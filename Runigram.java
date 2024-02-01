// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		// Color[][] tinypic = read("tinypic.ppm");
		// print(tinypic);

		// // Creates an image which will be the result of various 
		// // image processing operations:
		// Color[][] imageOut;

		// // Tests the horizontal flipping of an image:
		// imageOut = flippedHorizontally(tinypic);
		// System.out.println();
		// print(imageOut);

		// System.out.println();
		// print(tinypic); 

		// Color[][] thor = read("thor.ppm");
		// Color[][] thorGray = grayScaled(thor);
		// // displayImage(thorGray);
		// Color[][] thorScaled = scaled(thor, 1000, 1000);
		// // displayImage(thorScaled);

		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
	}
	//helper function to setcanvas and display image for testing purposes
	public static void displayImage(Color[][] image) {
	    setCanvas(image);
	    display(image);
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i < numRows; i++) {
		    for (int j = 0; j < numCols; j++) {
		        int r = in.readInt();
		        int g = in.readInt();
		        int b = in.readInt();
		        image[i][j] = new Color(r, g, b);
		    }
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		for (int i = 0; i < image.length; i++) {
		    for (int j = 0; j < image[0].length; j++) {
		        print(image[i][j]);
		    }
		    System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		Color[][] horizontalImage = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
		    for (int j = 0; j < image[0].length / 2; j++) {
		        Color temp = image[i][j];
		        horizontalImage[i][j] = image[i][image[0].length - j - 1];
		        horizontalImage[i][image[0].length - j - 1] = temp;
		    }
		}
		return horizontalImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		Color[][] verticalImage = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length / 2; i++) {
		    for (int j = 0; j < image[0].length; j++) {
		        Color temp = image[i][j];
		        verticalImage[i][j] = image[image.length - i - 1][j];
		        verticalImage[image.length - i - 1][j] = temp;
		    }
		}
		return verticalImage;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		int lum = (int) (0.299 * pixel.getRed() + 0.587 * pixel.getGreen() + 0.114 * pixel.getBlue());
		return new Color(lum, lum, lum);
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		Color[][] grayImage = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
		    for (int j = 0; j < image[0].length; j++) {
		        grayImage[i][j] = luminance(image[i][j]);
		    }
		}
		return grayImage;
	}	
	//Helper function to getScaledPosition using algorithm from the Homework 6 pdf
	public static Color getScaledPosition(Color[][] image, int targetWidth, int targetHeight, int i, int j) {
		int scaledRow = (int) (i * (image.length / (double) targetHeight));
	    int scaledCol = (int) (j * (image[0].length / (double) targetWidth));
	    return image[scaledRow][scaledCol];
	}
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		Color[][] scaledImage = new Color[height][width];
		for (int i = 0; i < height; i++) {
		    for (int j = 0; j < width; j++) {
		        scaledImage[i][j] = getScaledPosition(image, width, height, i, j);
		    }
		}
		return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		Integer r = (int)( (alpha) * (double)c1.getRed()   + ((1 - alpha) * (double) c2.getRed()));
		Integer g = (int)( (alpha) * (double)c1.getGreen() + ((1 - alpha) * (double) c2.getGreen()));
		Integer b = (int)( (alpha) * (double)c1.getBlue()  + ((1 - alpha) * (double) c2.getBlue()));
		return new Color(r,g,b);
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] sourceImage, Color[][] targetImage, double alpha) {
		//// Replace the following statement with your code
		Color[][] blendedImage = new Color[sourceImage.length][sourceImage[0].length];
		for (int i = 0; i < sourceImage.length; i++) {
		    for (int j = 0; j < sourceImage[0].length; j++) {
		        blendedImage[i][j] = blend(sourceImage[i][j], targetImage[i][j], alpha);
		    }
		}
		return blendedImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		for (int i =0; i <= n; i++) {
		    Color[][] scaledTarget = scaled(target, source[0].length, source.length);
		    Color[][] blendedImage = blend(source, scaledTarget, (double) ((n-i) / (double) n));
		    display(blendedImage);
		    // StdDraw.pause(500);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

