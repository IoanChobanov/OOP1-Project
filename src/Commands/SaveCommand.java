package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import Sessions.Session;
import Sessions.SessionManager;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public SaveCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws IOException, CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();

        for (Image image : activeSession.getImages()) {
            for (String transformation : activeSession.getTransformations()) {
                applyTransformation(image, transformation);
            }
            File outputFile = new File(image.getFile().getAbsolutePath());
            image.save(outputFile);
        }

        System.out.println("Saved successfully!");
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
                break;
        }
    }

}
