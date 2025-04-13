package Commands;

import ImageHandling.Image;
import Sessions.Session;
import Sessions.SessionManager;

public class SaveCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public SaveCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) {
        Session activeSession = sessionManager.getActiveSession();

        if (activeSession == null) {
            System.out.println("No active session found.");
            return;
        }

        for (Image image : activeSession.getImages()) {
            for (String transformation : activeSession.getTransformations()) {
                applyTransformation(image, transformation);
            }
        }

        System.out.println("Saved successfully!");
    }

    private void applyTransformation(Image image, String transformation) {
        String[] parts = transformation.split(" ", 2);
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
                    image.applyCollage(collageArgs[0], collageArgs[1], collageArgs[2], collageArgs[3]);
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
