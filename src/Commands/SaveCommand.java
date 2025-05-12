package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import ImageHandling.ImageTransformer;
import Sessions.Session;
import Sessions.SessionManager;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public SaveCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws IOException, CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();

        if (activeSession.getImages().isEmpty()) {
            throw new CommandException("No images to save in current session");
        }

        for (Image image : activeSession.getImages()) {
            ImageTransformer.applyTransformations(image, activeSession.getTransformations());
        }

        activeSession.getTransformations().clear();

        for (Image image : activeSession.getImages()) {
            image.save(new File(image.getFile().getAbsolutePath()));
        }

        System.out.println("Saved all images successfully! Transformation queue reset.");
    }
}