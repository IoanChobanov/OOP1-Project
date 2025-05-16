package Commands;

import Exceptions.CommandException;
import Sessions.Session;
import Sessions.SessionManager;

/**
 * Клас, който добавя rotate_(left/right) към колекцията с трансформации.
 */
public class RotateCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public RotateCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който добавя rotate_(left/right) към колекцията с трансформации.
     * @param args Аргументите, подадени от менюто.
     * @throws CommandException При липсата на текуща сесия и невалидни аргументи.
     */
    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();

        if (args.length != 1 || (!args[0].equals("left") && !args[0].equals("right"))) {
            throw new CommandException("Invalid arguments. Use 'rotate left' or 'rotate right'.");
        }

        String transformation = "rotate " + args[0];
        activeSession.addTransformation(transformation);

        System.out.println("Queued " + args[0] + " rotation for all images in the session.");
    }
}
