package Commands;

import Exceptions.CommandException;
import Sessions.Session;
import Sessions.SessionManager;
import java.io.IOException;

public class CloseCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public CloseCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws IOException, CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();

        sessionManager.removeActiveSession();
        System.out.println("Session closed successfully. All unsaved changes were discarded.");
    }
}

