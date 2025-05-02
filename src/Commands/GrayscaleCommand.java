package Commands;

import Exceptions.CommandException;
import Sessions.Session;
import Sessions.SessionManager;

public class GrayscaleCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public GrayscaleCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();
        activeSession.addTransformation("grayscale");
        System.out.println("Queued grayscale filter for all images in the session.");
    }
}
