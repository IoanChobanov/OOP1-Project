package Commands;

import Sessions.Session;
import Sessions.SessionManager;

public class UndoCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public UndoCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void create(String... args) {
        Session activeSession = sessionManager.getActiveSession();

        if (activeSession == null) {
            System.out.println("No active session found.");
            return;
        }

        if (!activeSession.getTransformations().isEmpty()) {
            activeSession.getTransformations().remove(activeSession.getTransformations().size() - 1);
            System.out.println("One change undone.");
        } else {
            System.out.println("No transformations to undo.");
        }
    }
}
