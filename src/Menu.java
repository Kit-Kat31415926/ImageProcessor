import java.util.Scanner;

/**
 * Menu class
 * Collects user input
 */
public class Menu {
	private String userIn;
	private Scanner scan;

	/**
	* Constructor for Menu class
	* @author - April
	*/
	public Menu() {
		scan = new Scanner(System.in);
	}
	
	/**
	 * Formats the menu
	 * @author - Kaitlyn
	 * @return - The formatted menu as a String
	 */
	public String toString() {
		return "1. Black and White\n"
				+ "2. Grayscale\n"
				+ "3. Luminance Grayscale\n"
				+ "4. Rotate\n"
				+ "5. Sepia\n"
				+ "6. BrightAndDark\n"
				+ "7. Color Filter\n"
				+ "8. Posterize\n"
				+ "9. Negative\n"
				+ "10. Sharpen\n"
				+ "11. Blur\n"
				+ "12. Shrink\n"
				+ "13. Enlarge\n"
				+ "14. Change image\n"
				+ "Enter q to quit";
	}

	/**
	 * Gets the user's chosen image
	 * @author - April
	 * @return - The name of the chosen image
	 */
	public String getUserImage() {
		System.out.println("♡I have a few images I collected on my travels. You can choose one to edit, or use your own♡");
		System.out.println("1. Use your own?");
		System.out.println("2. Use Pixellina's images?");
		String choice = scan.nextLine();
		String imageName = "";
		boolean valid = false;
		while (!valid) {
			if (choice.equals("1")) {
				try {
					System.out.println("♡Please enter your image file. For example, 'fairy.jpg'♡");
					imageName = scan.nextLine();
					new ImageProcessor("images/" + imageName);
					valid = true;
				} catch (Exception e) {
					valid = false;
					System.out.println("Your image could not be found. "
							+ "Please make sure it is spelled correctly and located in the correct folder.");
				}
			} else if (choice.equals("2")) {
				imageName = imageMenu();
				valid = true;
			} else {
				System.out.println("♡Sorry, that's not a valid option. Please enter 1 or 2♡");
				choice = scan.nextLine();
			}
		}
		return imageName;
	}

	/**
	 * Formats the menu for available images
	 * @author - April
	 * @return - The formatted menu of images as a String
	*/
	public String imageMenu() {
		System.out.println("♡‧₊˚Please choose one of these lovely images♡‧₊˚");
		System.out.println("1. Rose-covered arch of the ancient palace\n"
							+ "2. Butterfly, winged guide to lost travelers\n"
							+ "3. Koala, elusive tree hugger\n"
							+ "4. Red motorcycle of swift voyage\n"
							+ "5. Seagull, brave strider of the emerald waves\n"
							+ "6. Smokey, feline guardian of the forest\n"
							+ "7. Swan, cursed princess of the lake");
		userIn = scan.nextLine();
		if (userIn.equals("1")) {
			return "arch.jpg";
		} else if (userIn.equals("2")) {
			return "butterfly1.jpg";
		} else if (userIn.equals("3")) {
			return "koala.jpg";
		} else if (userIn.equals("4")) {
			return "redMotorcycle.jpg";
		} else if (userIn.equals("5")) {
			return "seagull.jpg";
		} else if (userIn.equals("6")) {
			return "smokey.jpg";
		} else if (userIn.equals("7")) {
			return "swan.jpg";
		} else {
			System.out.println("♡‧₊˚That's not a valid choice, so let's just use Smokey's image♡‧₊˚");
			return "smokey.jpg";
		}
	}

	/**
	 * Check the validity of the user's input
	 * @author - April
	 * @param userIn - The user's choice
	 * @return - True if the user's input is valid; false otherwise
	 */
	public boolean checkInput(String userIn) {
		if (userIn.equals("1") || userIn.equals("2") || userIn.equals("3") || userIn.equals("4") || userIn.equals("5") || userIn.equals("6") || userIn.equals("7") || userIn.equals("8") || userIn.equals("9") || userIn.equals("10") || userIn.equals("11") || userIn.equals("12") || userIn.equals("13") || userIn.equals("14") || userIn.equalsIgnoreCase("q")) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of degrees to rotate by
	 * @author - April
	 * @return - 90, 180, or 270 degrees
	 */
	public int rotateInput() {
		System.out.println("♡How do you want to rotate your image?♡");
		System.out.println("1. 90 degrees to the left");
		System.out.println("2. 90 degrees to the right");
		System.out.println("3. 180 degrees");
		boolean valid = false;
		int deg = 0;
		while (!valid) {
			String userIn = scan.nextLine();
			if (userIn.equals("1")) {
				deg = 270;
				valid = true;
			} else if (userIn.equals("2")) {
				deg = 90;
				valid = true;
			} else if (userIn.equals("3")) {
				deg = 180;
				valid = true;
			} else {
				System.out.println("♡Sorry, that's an invalid number of degrees. Try again!♡");
			}
		}
		return deg;
	}

	/**
	 * Returns the factor by which the image should be brightened/darkened
	 * @author - April
	 * @return - An integer value; positive for brightening and negative for darkening
	 */
	public int brightAndDarkInput() {
		System.out.println("♡How much do you want to brighten/darken your image?\n"
							+ "A positive number will brighten your image and a negative number will darken your image.\n"
							+ "Please enter a whole number♡");
		int result = 0;
		boolean valid = false;
		while (!valid) {
			if (scan.hasNextInt()) {
				result = scan.nextInt();
				valid = true;
			}
			else {
				System.out.println("♡Sorry, that's an invalid input. Try again!♡");
			}
		}
		return result;
	}

	/**
	 * Returns the factor by which the RGB values of the image should be changed
	 * @author - April
	 * @return - An array containing the three different values for red, green, and blue changes
	 */
	public int[] colorInput() {
		int red = 0;
		int green = 0;
		int blue = 0;
		System.out.println("♡How much do you want to change your image red? Enter a whole number♡");
		boolean valid = false;
		while (!valid) {
			if (scan.hasNextInt()) {
				red = scan.nextInt();
				valid = true;
			}
			else {
				System.out.println("♡Sorry, that's an invalid input. Try again!♡");
			}
		}
		System.out.println("♡How much do you want to change your image green? Enter a whole number♡");
		valid = false;
		while (!valid) {
			if (scan.hasNextInt()) {
				green = scan.nextInt();
				valid = true;
			}
			else {
				System.out.println("♡Sorry, that's an invalid input. Try again!♡");
			}
		}
		System.out.println("♡How much do you want to change your image blue? Enter a whole number♡");
		valid = false;
		while (!valid) {
			if (scan.hasNextInt()) {
				blue = scan.nextInt();
				valid = true;
			}
			else {
				System.out.println("♡Sorry, that's an invalid input. Try again!♡");
			}
		}
		int[] colors = new int[3];
		colors[0] = red;
		colors[1] = green;
		colors[2] = blue;
		return colors;
	}

	/**
	 * Returns the factor by which the image should be sharpened
	 * @author - April
	 * @return - An array containing the two different values for degree and threshold for sharpening
	 */
	public int[] sharpenInput() {
		int dx = 0;
		int threshold = 0;
		System.out.println("♡How much do you want to sharpen your image? Enter a whole number♡");
		boolean valid = false;
		while (!valid) {
			if (scan.hasNextInt()) {
				dx = scan.nextInt();
				valid = true;
			}
			else {
				System.out.println("♡Sorry, that's an invalid input. Try again!♡");
			}
		}
		System.out.println("♡What's the threshold for the sharpening? Enter a whole number♡");
		valid = false;
		while (!valid) {
			if (scan.hasNextInt()) {
				threshold = scan.nextInt();
				valid = true;
			}
			else {
				System.out.println("♡Sorry, that's an invalid input. Try again!♡");
			}
		}
		int[] sharp = new int[2];
		sharp[0] = dx;
		sharp[1] = threshold;
		return sharp;
	}

	/**
	 * Asks the user for the factor by which the image should be shrunk
	 * @author - April
	 * @return - An integer factor by which image will be shrunk
	 */
	public int shrinkInput() {
		System.out.println("♡How much do you want to shrink your image? Enter a whole number♡");
		int result = 0;
		boolean valid = false;
		while (!valid) {
			if (scan.hasNextInt()) {
				result = scan.nextInt();
				valid = true;
			}
			else {
				System.out.println("♡Sorry, that's an invalid input. Try again!♡");
			}
		}
		return result;
	}

	/**
	 * Asks the user for the factor by which the image should be enlarged
	 * @author - April
	 * @return - An integer factor by which image will be enlarged
	 */
	public int enlargeInput() {
		System.out.println("♡How much do you want to enlarge your image? Enter a whole number♡");
		int result = 0;
		boolean valid = false;
		while (!valid) {
			if (scan.hasNextInt()) {
				result = scan.nextInt();
				valid = true;
			}
			else {
				System.out.println("♡Sorry, that's an invalid input. Try again!♡");
			}
		}
		return result;
	}
}