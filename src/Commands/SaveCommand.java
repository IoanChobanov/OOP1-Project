package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import ImageHandling.ImageTransformer;
import Sessions.Session;
import Sessions.SessionManager;

import java.io.File;
import java.io.IOException;

/**
 * Клас, който прилага всички трансформации до момента на всички изображения в сесията.
 */
public class SaveCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public SaveCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който прилага всички трансформации до момента на всички изображения в сесията.
     * @param args Аргументите, подадени от менюто.
     * @throws IOException При грешки в обработката на файлове.
     * @throws CommandException При грешки по време на изпълнение.
     */
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