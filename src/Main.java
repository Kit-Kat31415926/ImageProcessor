import java.util.Scanner;

/**
 * Main class
 * Loops through editing until user quits
 */
public class Main {
	/**
	 * User chooses an image to edit based on given options
	 * Default image is "smokey.jpg"
	 * @author - April
	 * @param args - String[] array
	 */
	public static void main(String[] args) {
	  System.out.println("Hello! I'm Pixellina, the image-editing fairy\nଘ(੭*ˊᵕˋ)੭* ੈ♡‧₊˚");
	  System.out.println("♡With my magic, I can transform images in many ways♡");
    // Set user's image
	Menu myMenu = new Menu();
		ImageProcessor i = new ImageProcessor("images/" + myMenu.getUserImage());
		/**
		 * User is prompted to edit image until entering "q"
		 * @author - Kaitlyn
		 */
		Scanner scan = new Scanner(System.in);
		boolean keepEditing = true;
		while (keepEditing) {
			System.out.println("♡Please enter an option to edit the image♡: ");
			System.out.println(myMenu);
			String userIn = scan.nextLine();
			if (myMenu.checkInput(userIn)) {
				/**
				 * Call specified method
				 * @author - April
				 */
				switch (userIn.toUpperCase()) {
					case "Q":
						keepEditing = false;
						break;
			        case "1":
			            i.printImages(i.blackAndWhite());
			            break;
			        case "2":
			            i.printImages(i.grayscale());
			            break;
			        case "3":
			            i.printImages(i.luminanceGrayscale());
			            break;
			        case "4":
              i.printImages(i.rotate(myMenu.rotateInput()));
			            break;
			        case "5":
			            i.printImages(i.sepia());
			            break;
			        case "6":
			            i.printImages(i.brightAndDark(myMenu.brightAndDarkInput()));
			            break;
			        case "7":
            int[] colors = myMenu.colorInput();
			            i.printImages(i.colorFilter(colors[0], colors[1], colors[2]));
			            break;
			        case "8":
			            i.printImages(i.posterize());
			            break;
			        case "9":
			            i.printImages(i.negative());
			            break;
			        case "10":
			         int[] sharp = myMenu.sharpenInput(); i.printImages(i.sharpen(sharp[0], sharp[1]));
			            break;
			        case "11":
			            i.printImages(i.blur());
			            break;
			        case "12":
			            i.printImages(i.shrink(myMenu.shrinkInput()));
			            break;
			        case "13":
			            i.printImages(i.enlarge(myMenu.enlargeInput()));
			            break;
			        case "14":
			        	i = new ImageProcessor("images/" + myMenu.getUserImage());
			            break;
				}
			}
		}
	System.out.println("♡Have an enchanting day!♡");
	}
}