package Commands;

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
    public void execute(String... args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected 1 argument. Use 'help' for more information.");
        }

        Session session = sessionManager.getActiveSession();
        if (session == null || session.getImages().isEmpty()) {
            System.out.println("No active session or no images loaded.");
            return;
        }

        Image original = session.getImages().get(0);
        Image clone = original.cloneImage();

        for (String transformation : session.getTransformations()) {
            applyTransformation(clone, transformation);
        }

        File outputFile = new File(args[0]);
        clone.save(outputFile);
        System.out.println("Successfully saved as " + args[0]);
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
                String[] collageArgs = arguments.split("");
                if (collageArgs.length == 4) {
                    image.applyCollage(collageArgs[0], collageArgs[1], collageArgs[2], collageArgs[3]);
                } else {
                    System.out.println("Invalid collage arguments.");
                }
                break;
            default:
                System.out.println("Unknown transformation: " + action);
        }
    }
}
