package Commands;

import Exceptions.CommandException;
import Sessions.SessionManager;
import java.io.IOException;

public class CloseCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public CloseCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws IOException, CommandException {
        sessionManager.getValidatedActiveSession();
        sessionManager.removeActiveSession();

        System.out.println("Session closed successfully. All unsaved changes were discarded.");
        System.out.println("Remaining sessions: " + sessionManager.sessionSize());
    }
}

