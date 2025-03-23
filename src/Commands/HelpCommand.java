package Commands;

public class HelpCommand implements CreateCommand{
    @Override
    public void create(String... args) {
        String helpMessage = "The following commands are supported:\n" +
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
                "session info - Displays current session details.\n" +
                "switch <session_id> - Switches to a different session.\n" +
                "collage <horizontal|vertical> <img1> <img2> <outimg> - Creates a collage.\n";

        System.out.println(helpMessage);
    }
}
