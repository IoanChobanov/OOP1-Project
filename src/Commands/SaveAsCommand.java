package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import Sessions.Session;
import Sessions.SessionManager;

import java.io.File;
import java.io.IOException;

public class SaveAsCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public SaveAsCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws IOException, CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();

        if (args.length != 1) {
            throw new CommandException("Expected 1 argument. Use 'help' for more information.");
        }

        Image original = activeSession.getImages().get(0);
        Image clone = original.cloneImage();

        String fileName = args [0];
        String expectedExtension = "." + clone.getFormat();
        if(!fileName.endsWith(expectedExtension)){
            throw new CommandException("Please input the correct file type.");
        }

        for (String transformation : activeSession.getTransformations()) {
            applyTransformation(clone, transformation);
        }

        activeSession.getTransformations().clear();

        File outputFile = new File(args[0]);
        clone.save(outputFile);
        System.out.println("Successfully saved as " + args[0] + "\n Transformation queue reset.");
    }

    private void applyTransformation(Image image, String transformation) {
        String[] parts = transformation.split("_", 2);
        String action = parts[0];
        String arguments = (parts.length > 1) ? parts[1] : "";

        switch (action) {
            case "grayscale":
                image.applyGrayscale();
                break;
            case "monochrome":
                image.applyMonochrome();
                break;
            case "negative":
                image.applyNegative();
                break;
            case "rotate":
                image.applyRotation(arguments);
                break;
            case "collage":
                String[] collageArgs = arguments.split(" ");
                if (collageArgs.length == 4) {
                    //to do
                } else {
                    System.out.println("Invalid collage arguments.");
                }
                break;
            default:
                System.out.println("Unknown transformation: " + action);
        }
    }
}