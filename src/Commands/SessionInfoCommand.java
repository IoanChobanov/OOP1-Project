package Commands;

import Exceptions.CommandException;
import Sessions.Session;
import Sessions.SessionManager;

public class SessionInfoCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public SessionInfoCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();

        System.out.println("Session ID: " + activeSession.getSessionId());
        System.out.println("Number of images: " + activeSession.getImages().size());
        System.out.println("Transformations queued: " + activeSession.getTransformations());
    }
}
