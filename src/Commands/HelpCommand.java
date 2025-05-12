package Commands;

/**
 * Клас, който принтира всички възможни команди пред потребителя.
 */
public class HelpCommand implements CreateCommand{
    /**
     * Метод, който принтира наличните команди и как да се използват.
     * @param args Аргументите, подадени от менюто.
     */
    @Override
    public void execute(String... args) {
        System.out.println("The following commands are supported:\n" +
                "load <file> - Starts a session and loads images.\n" +
                "save - Saves all images in the current session.\n" +
                "saveas <file> - Saves only the first image under a new name.\n" +
                "close - Closes the current session.\n" +
                "exit - Exits the program.\n" +
                "grayscale - Applies grayscale filter to images in session.\n" +
                "monochrome - Converts images to black/white.\n" +
                "negative - Applies negative filter.\n" +
                "rotate <left|right> - Rotates images 90° in the given direction.\n" +
                "undo - Undoes the last transformation.\n" +
                "add <image> - Adds an image to the session.\n" +
                "sessioninfo - Displays current session details.\n" +
                "switch <session_id> - Switches to a different session.\n" +
                "collage <horizontal|vertical> <img1> <img2> <outimg> - Creates a collage.\n");
    }
}
