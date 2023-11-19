import images.APImage;
import images.Pixel;

/**
 * ImageProcessor class
 * Edits images based on user input
 */
public class ImageProcessor {
	// Original image chosen by user
	private final APImage oldImage;
  
   /**
    * ImageProcessor constructor creates a new ImageProcessor object
	* @author - Ritali
	* @param name - Name of the image to be used
	*/
	public ImageProcessor(String name) {
		oldImage = new APImage(name);
	}

    /**
     * Returns the average of the RGB values in a pixel
	 * @author - Inaya
	 * @param p - Pixel to be used
	 * @return - Average of the RGB values in the pixel
	 */
	public int pixelAverage(Pixel p) {
		return (p.getRed() + p.getGreen() + p.getBlue()) / 3;
	}
  
	/**
	 * Converts the image to black and white
	 * @author - Ritali
	 * @return - The black and white image
	 */
	public APImage blackAndWhite() {
		APImage newImage = oldImage.clone();
		for(Pixel p : newImage) {
			if(pixelAverage(p) < 128) {
				p.setRed(0);;
				p.setGreen(0);
				p.setBlue(0);	
			}else {
				p.setRed(255);;
				p.setGreen(255);
				p.setBlue(255);
			}
		}
		return newImage;
	}

	/**
	 * Converts the image to a grayscale
	 * @author - Inaya
	 * @return - The grayscale image
	 */
	public APImage grayscale() {
		APImage newImage = oldImage.clone();
		for (int x = 0; x < newImage.getWidth(); x++) {
			for (int y = 0; y < newImage.getHeight(); y++) {
				Pixel p = newImage.getPixel(x, y);
				p.setRed(pixelAverage(p));
				p.setGreen(pixelAverage(p));
				p.setBlue(pixelAverage(p));
			}
		}
		return newImage;
	}

	/**
	 * Converts image to luminance grayscale
	 * @author - Inaya
	 * @return - The luminance grayscale image
	 */
	public APImage luminanceGrayscale() {
		APImage newImage = oldImage.clone();
		for (int x = 0; x < newImage.getWidth(); x++) {
			for (int y = 0; y < newImage.getHeight(); y++) {
				Pixel p = newImage.getPixel(x, y);
				int average = (int) ((p.getBlue() * 0.114) + 
									(p.getRed() * 0.299)+ 
									(p.getGreen() * 0.587)) / 3;
				p.setRed(average);
				p.setGreen(average);
				p.setBlue(average);
			}
		}
		return newImage;
	}
  
	/**
	 * Rotates the image a given number of degrees
	 * @author - Kaitlyn
	 * @param degrees - number of degrees to rotate
	 * @return - rotated APImage
	 */
	public APImage rotate(int degrees) {
		APImage newImage = new APImage(oldImage.getHeight(), oldImage.getWidth());
		if (degrees == 90) {
			for (int row = 0; row < oldImage.getHeight(); row++) {
				for (int col = 0; col < oldImage.getWidth(); col++) {
					newImage.setPixel(oldImage.getHeight() - 1 - row, col, oldImage.getPixel(col, row));
				}
			}
		}
		if (degrees == 180) {
			newImage = oldImage.clone();
			for (int row = 0; row < oldImage.getHeight(); row++) {
				for (int col = 0; col < oldImage.getWidth(); col++) {
					newImage.setPixel(oldImage.getWidth() - 1 - col, oldImage.getHeight() - 1 - row, oldImage.getPixel(col, row));
				}
			}
		}
		if (degrees == 270) {
			for (int row = 0; row < oldImage.getHeight(); row++) {
				for (int col = 0; col < oldImage.getWidth(); col++) {
					newImage.setPixel(row, oldImage.getWidth() - 1 - col, oldImage.getPixel(col, row));
				}
			}
		}
		return newImage;
	}

	/**
	 * Convert image to sepia
	 * @author - Ritali
	 * @return - The sepia image
	 */
	public APImage sepia() {
		APImage newImage = grayscale();
		Pixel p;
		int red;
		int blue;
		for(int x = 0; x < newImage.getWidth(); x++) {
			for(int y = 0; y < newImage.getHeight(); y++) {
				p = newImage.getPixel(x, y);
				red = p.getRed();
				blue = p.getBlue();
				if (red < 63) {
					red = (int) (red * 1.1);
					blue = (int) (blue * 0.9);
				} else if (red < 192) {
					red = (int) (red * 1.15);
					blue = (int) (blue * 0.85);
				} else {
					red = Math.min((int) (red * 1.08), 255);
					blue = (int) (blue * 0.93);
				}
				p.setRed(red);
				p.setBlue(blue);
			}
		}
		return newImage;
	}

	/**
	 * Brightens/darkens the image based on the factor
	 * @author - Kaitlyn
	 * @param factor - Factor by which to brighten/darken image
	 * @return - The brightened/darkened image
	 */
	public APImage brightAndDark(int factor) {
		return colorFilter(factor, factor, factor);
	}

	/**
	 * Color filters the image
	 * @author - Kaitlyn
	 * @param dRed - Factor by which red RGB value is changed
	 * @param dGreen - Factor by which green RGB value is changed
	 * @param dBlue - Factor by which blue RGB value is changed
	 * @return - The color filtered image
	 */
	public APImage colorFilter(int dRed, int dGreen, int dBlue) {
		APImage newImage = oldImage.clone();
		for (int row = 0; row < oldImage.getHeight(); row++) {
			for (int col = 0; col < oldImage.getWidth(); col++) {
				Pixel p = oldImage.getPixel(col,  row);
				int red = p.getRed() + dRed;
				int green = p.getGreen() + dGreen;
				int blue = p.getBlue() + dBlue;
				if (red > 255) {
					red = 255;
				}
				if (green > 255) {
					green = 255;
				}
				if (blue > 255) {
					blue = 255;
				}
				if (red < 0) {
					red = 0;
				}
				if (green < 0) {
					green = 0;
				}
				if (blue < 0) {
					blue = 0;
				}
				newImage.setPixel(col,  row, new Pixel(red, green, blue));
			}
		}
		return newImage;
	  }

	/**
	 * Posterize the image
	 * @author - Ritali
	 * @return - The posterized image
	 */
	public APImage posterize() {
		APImage newImage = oldImage.clone();

		Pixel color1 = new Pixel((int) (Math.random() * 256),
				(int) (Math.random() *256), 
				(int) (Math.random() * 256));
		Pixel color2 = new Pixel((int) (Math.random() * 256),
				(int) (Math.random() * 256), 
				(int) (Math.random() * 256));

		for (int x = 0; x < newImage.getWidth(); x++) {
			for (int y = 0 ; y < newImage.getHeight(); y++) {
				if (pixelAverage(newImage.getPixel(x, y)) < 128) {
					newImage.setPixel(x, y, color1);	
				} else {
					newImage.setPixel(x, y, color2);	
				}
			}
		}
		return newImage;
	}

	/**
	 * Converts image to photographic negative
	 * @author - Ritali
	 * @return - The negative photo
	 */
	public APImage negative() {
		APImage newImage = grayscale();
		Pixel p;
		for (int x = 0; x < newImage.getWidth(); x++) {
			for (int y = 0; y < newImage.getHeight(); y++) {
				p = newImage.getPixel(x,  y);
				p.setRed(255 - p.getRed());
				p.setGreen(255 - p.getGreen());
				p.setBlue(255 - p.getBlue());
			}
		}
		return newImage;
	}

	/**
	 * Sharpens the image
	 * @author - Ritali
	 * @param dx - The degree to which image is sharpened
	 * @param threshold - The threshold to which image is sharpened
	 * @return - The sharpened image
	 */
	public APImage sharpen(int dx, int threshold){
		APImage newImage = oldImage.clone();
		for (int x = 1; x < oldImage.getWidth(); x++) {
			for (int y = 0; y < oldImage.getHeight() - 1; y++) {
				int left = pixelAverage(oldImage.getPixel(x-1, y));
				int bottom = pixelAverage(oldImage.getPixel(x, y+1));
				int curr = pixelAverage(oldImage.getPixel(x, y));
				if (Math.abs(curr - bottom) >= threshold || Math.abs(curr - left) >= threshold) {
					Pixel p = newImage.getPixel(x, y);
					int red = p.getRed() - dx;
					int green = p.getGreen() - dx;
					int blue = p.getBlue() - dx;
					if(red < 0) {
						red = 0;
					}
					if(green < 0) {
						green = 0;
					}
					if(blue < 0) {
						blue = 0;
					}
					p.setRed(red);
					p.setGreen(green);
					p.setBlue(blue);			
				}
			}
		}
		return newImage;
	}

	/**
	 * Blurs the image
	 * @author - Inaya
	 * @return - The blurred image
	 */
	public APImage blur() {
		APImage newImage = oldImage.clone();
		for (int x = 1; x < newImage.getWidth() - 1; x++) {
			for (int y = 1; y < newImage.getHeight() - 1; y++) {
				Pixel p = newImage.getPixel(x, y);
				Pixel right = newImage.getPixel(x + 1, y);
				Pixel left = newImage.getPixel(x - 1, y);
				Pixel top = newImage.getPixel(x, y + 1);
				Pixel bottom = newImage.getPixel(x, y - 1);
				int redAverage = (right.getRed() + 
									left.getRed() + 
									top.getRed() + 
									bottom.getRed()) / 4;
				int greenAverage = (right.getGreen() + 
									left.getGreen() + 
									top.getGreen() + 
									bottom.getGreen()) / 4;
				int blueAverage = (right.getBlue() + 
									left.getBlue() + 
									top.getBlue() + 
									bottom.getBlue()) / 4;
				p.setRed(redAverage);
				p.setGreen(greenAverage);
				p.setBlue(blueAverage);
			}
		}
		return newImage;
	}

	/**
	 * Shrinks the image
	 * @author - Ritali
	 * @param factor - Factor by which the image is shrunk
	 * @return - Shrunken image
	 */
	public APImage shrink(int factor) {
		APImage newImage = new APImage(oldImage.getWidth() / factor, oldImage.getHeight() / factor);
		for (int x = 0; x < newImage.getWidth(); x++) {
			for (int y = 0; y < newImage.getHeight(); y++) {
				newImage.setPixel(x, y, oldImage.getPixel(x * factor, y * factor));
			}
		}
		return newImage;
	}

  	/**
  	 * Enlarges the image
	 * @author - Inaya
	 * @param factor - Factor by which the image is enlarged
	 * @return - The enlarged image
	 */
	public APImage enlarge(int factor) {
    	APImage newImage = new APImage(factor * oldImage.getWidth(), factor * oldImage.getHeight());
		for (int x = 0; x < newImage.getWidth(); x++) {
			for (int y = 0; y < newImage.getHeight(); y++) {
				newImage.setPixel(x, y, oldImage.getPixel(x / factor, y / factor));
			}
		}
    	return newImage;
	}
	
	/**
	 * Prints the original and new (given) image
	 * @author - Ritali
	 * @param newImage - The image to be printed with the original
	 */
	public void printImages(APImage newImage) {
		oldImage.draw();
		newImage.draw();
	}
}