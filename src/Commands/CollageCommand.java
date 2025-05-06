package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import Sessions.Session;
import Sessions.SessionManager;

public class CollageCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public CollageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();

        if (args.length != 4) {
            System.out.println("Invalid arguments. Expected 4 arguments for collage: layout, img1, img2, outimg.");
            return;
        }

        String collageCommand = String.join(" ", args);
        activeSession.addTransformation("collage " + collageCommand);

        System.out.println("Queued collage with layout " + args[0] + " for images " + args[1] + " and " + args[2]);
    }
}
