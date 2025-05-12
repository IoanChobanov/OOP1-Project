package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import ImageHandling.ImageTransformer;
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

        ImageTransformer.applyTransformations(clone, activeSession.getTransformations());
        activeSession.getTransformations().clear();

        File outputFile = new File(fileName);
        clone.save(outputFile);
        System.out.println("Successfully saved as " + fileName + "\nTransformation queue reset.");
    }
}