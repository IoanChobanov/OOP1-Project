package Commands;

import Exceptions.CommandException;
import Sessions.SessionManager;
import ImageHandling.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, който създава уникална сесия с едно или повече изображния.
 */
public class LoadCommand implements CreateCommand {
    private final SessionManager sessionManager;
    private final AddCommand addCommand;

    public LoadCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.addCommand = new AddCommand(sessionManager);
    }

    /**
     * Метод, който създава сесия с уникален номер и добавя изображения към нея.
     * @param args Аргументите, подадени от менюто.
     * @throws CommandException При невалидни аргументи и несъществуващи изображения в директорията с изображения.
     * @throws IOException При грешка при добавянето на изображение.
     */
    @Override
    public void execute(String... args) throws CommandException, IOException {
        if (args.length == 0) {
            throw new CommandException("You need to load at least 1 file! Use 'help' for more information.");
        }

        List<File> validFiles = new ArrayList<>();
        for (String fileName : args) {
            File file = new File(fileName);
            if (!file.exists()) {
                file = new File("example_images/" + fileName);
            }
            if (!file.exists()) {
                throw new CommandException("File not found: " + fileName);
            }

            try {
                ImageLoader.loadImage(file);
                validFiles.add(file);
            } catch (IOException e) {
                throw new CommandException("Failed to load image " + fileName + ": " + e.getMessage());
            }
        }

        sessionManager.createSession(new ArrayList<>());

        for (File file : validFiles) {
            addCommand.execute(file.getPath());
        }
    }
}
