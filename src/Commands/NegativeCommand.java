package Commands;

import Exceptions.CommandException;
import Sessions.Session;
import Sessions.SessionManager;
import TransformationHandling.NegativeTransformation;
import com.sun.org.apache.xpath.internal.operations.Neg;

/**
 * Клас, който добавя negative към колекцията с трансформации.
 */
public class NegativeCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public NegativeCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който добавя negative към колекцията с трансформации.
     * @param args Аргументите, подадени от менюто.
     * @throws CommandException При липсата на текуща сесия.
     */
    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();
        activeSession.addTransformation(new NegativeTransformation());
        System.out.println("Queued negative filter for all images in the session.");
    }
}
