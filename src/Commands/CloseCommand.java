package Commands;

import Sessions.SessionManager;
import java.io.IOException;

public class CloseCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public CloseCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws IOException {
        if (sessionManager.getActiveSession() == null) {
            System.out.println("No active session to close.");
            return;
        }

        sessionManager.removeActiveSession();
        System.out.println("Session closed successfully. All unsaved changes were discarded.");
    }
}

