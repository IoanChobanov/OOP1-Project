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
    public void create(String... args) {
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

        switch (parts[0]) {
            case "grayscale":
                break;
            case "monochrome":
                break;
            case "negative":
                break;
            case "rotate":
                applyRotation(parts[1]);
                break;
            case "collage":
                applyCollage(parts[1]);
                break;
            default:
                System.out.println("Unknown transformation: " + transformation);
        }
    }

    private void applyRotation(String direction) {
        if (direction.equals("left")) {
            System.out.println("Applying left rotation to all images.");
        } else if (direction.equals("right")) {
            System.out.println("Applying right rotation to all images.");
        }
    }

    private void applyCollage(String args) {
        String[] collageArgs = args.split(" ");
        if (collageArgs.length != 4) {
            System.out.println("Invalid collage arguments.");
            return;
        }

        String layout = collageArgs[0];
        String img1 = collageArgs[1];
        String img2 = collageArgs[2];
        String outimg = collageArgs[3];

        System.out.println("Applying collage with layout " + layout + " using images " + img1 + " and " + img2 + " to create " + outimg);
    }
}
