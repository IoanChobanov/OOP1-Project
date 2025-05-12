package Commands;

import Exceptions.CommandException;
import Sessions.Session;
import Sessions.SessionManager;

/**
 * Клас, който добавя grayscale към колекцията с трансформации.
 */
public class GrayscaleCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public GrayscaleCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който добавя grayscale към колекцията с трансформации.
     * @param args Аргументите, подадени от менюто.
     * @throws CommandException При несъществуваща сесия.
     */
    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();
        activeSession.addTransformation("grayscale");
        System.out.println("Queued grayscale filter for all images in the session.");
    }
}
