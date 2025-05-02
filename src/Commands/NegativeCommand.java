package Commands;

import Exceptions.CommandException;
import Sessions.Session;
import Sessions.SessionManager;

public class NegativeCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public NegativeCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();
        activeSession.addTransformation("negative");
        System.out.println("Queued negative filter for all images in the session.");
    }
}
