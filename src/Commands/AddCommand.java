package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import ImageHandling.ImageLoader;
import Sessions.Session;
import Sessions.SessionManager;

import java.io.File;
import java.io.IOException;

/**
 * Клас, който имплементира добавяне на изображения в активна сесия.
 */
public class AddCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public AddCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който добавя изображение към текущата сесия.
     * @param args Един или повече аргументи подадени от менюто.
     * @throws CommandException Обработва грешки при неправилно въведени аргументи.
     * @throws IOException Обработва грешки, свързани с файлова обработка.
     */
    @Override
    public void execute(String... args) throws CommandException, IOException {
        Session activeSession = sessionManager.getValidatedActiveSession();
        
        if (args.length != 1) {
            throw new CommandException("Expected 1 argument. Use 'help' for more information.");
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            file = new File("example_images/" + args[0]);
            if (!file.exists()) {
                throw new CommandException("File not found: " + args[0]);
            }
        }

        String fileName = file.getName();
        for (Image img : activeSession.getImages()) {
            if (img.getFile().getName().equals(fileName)) {
                throw new CommandException("Image '" + fileName +
                        "' already exists in current session");
            }
        }

        Image image = ImageLoader.loadImage(file);
        image.load();
        activeSession.getImages().add(image);
        System.out.println("Added " + args[0] + " to the current session.");
    }
}

