package Commands;

import Exceptions.CommandException;
import Sessions.Session;
import Sessions.SessionManager;

/**
 * Клас, който добавя функционалст за премахване на последната трансформация от колекцията с трансформации.
 */
public class UndoCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public UndoCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който премахва последно добавената трансформация от колекцията с трансформации.
     * @param args Аргументите, подадени от менюто.
     */
    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getActiveSession();

        if (activeSession == null) {
            throw new CommandException("No active session found.");
        }

        if (!activeSession.getTransformations().isEmpty()) {
            activeSession.removeLastTransformation();
            System.out.println("One change undone.");
        } else {
            throw new CommandException("No transformations to undo.");
        }
    }
}
