package Commands;

import Exceptions.CommandException;
import Sessions.Session;
import Sessions.SessionManager;
import TransformationHandling.MonochromeTransformation;

/**
 * Клас, който добавя monochrome към колекцията с трансформации.
 */
public class MonochromeCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public MonochromeCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който добавя monochrome към колекцията с трансформации.
     * @param args Аргументите, подадени от менюто.
     * @throws CommandException При липсата на текуща сесия.
     */
    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();
        activeSession.addTransformation(new MonochromeTransformation());
        System.out.println("Queued monochrome filter for all images in the session.");
    }
}
