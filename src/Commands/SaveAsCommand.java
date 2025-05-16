package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import ImageHandling.ImageTransformations;
import Sessions.Session;
import Sessions.SessionManager;

import java.io.File;
import java.io.IOException;

/**
 * Клас който прилага всички трансформации добавени в сесията към първото изображение в сесията и го записва под ново име.
 */
public class SaveAsCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public SaveAsCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който създава копие на първото изображение от текущата сесия и прилага трансформациите върху него.
     * @param args Аргументите, подадени от менюто.
     * @throws IOException При грешки в обработката на файлове.
     * @throws CommandException При грешки по време на изпълнение.
     */
    @Override
    public void execute(String... args) throws IOException, CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();

        if (args.length != 1) {
            throw new CommandException("Expected exactly 1 argument: output filename");
        }

        if (activeSession.getImages().isEmpty()) {
            throw new CommandException("No images in session to save");
        }

        Image original = activeSession.getImages().get(0);
        Image clone = original.cloneImage();

        String fileName = args[0];
        String expectedExtension = "." + clone.getFormat();
        if (!fileName.endsWith(expectedExtension)) {
            throw new CommandException("Output filename must end with " + expectedExtension +
                    " to match image format");
        }

        ImageTransformations.applyTransformations(clone, activeSession.getTransformations());
        if(!activeSession.getTransformations().isEmpty()) {
            activeSession.getTransformations().clear();
        }

        File outputFile = new File(fileName);
        clone.save(outputFile);
        System.out.println("Successfully saved as " + fileName + "\nTransformation queue reset.");
    }
}