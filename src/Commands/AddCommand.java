package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import ImageHandling.ImageLoader;
import Sessions.Session;
import Sessions.SessionManager;

import java.io.File;
import java.io.IOException;

public class AddCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public AddCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws CommandException, IOException {
        if (args.length != 1) {
            throw new CommandException("Expected 1 argument. Use 'help' for more information.");
        }

        Session activeSession = sessionManager.getActiveSession();
        if (activeSession == null) {
            throw new CommandException("No active session. Use 'load' to start a new session.");
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            file = new File("example_images/" + args[0]);
        }

        String imagePath = file.getCanonicalPath();
        boolean alreadyLoaded = activeSession.getImages().stream()
                .anyMatch(img -> {
                    try {
                        return img.getFile().getCanonicalPath().equals(imagePath);
                    } catch (IOException e) {
                        return false;
                    }
                });

        if (alreadyLoaded) {
            System.out.println("Image " + args[0] + " already exists in the session. Skipping.");
            return;
        }

        Image image = ImageLoader.loadImage(file);
        image.load();
        activeSession.getImages().add(image);
        System.out.println("Added " + args[0] + " to the current session.");
    }
}

