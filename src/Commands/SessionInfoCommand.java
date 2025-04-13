package Commands;

import Sessions.Session;
import Sessions.SessionManager;

public class SessionInfoCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public SessionInfoCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) {
        Session session = sessionManager.getActiveSession();
        if (session == null) {
            System.out.println("No active session.");
            return;
        }

        System.out.println("Session ID: " + session.getSessionId());
        System.out.println("Number of images: " + session.getImages().size());
        System.out.println("Transformations queued: " + session.getTransformations());
    }
}
