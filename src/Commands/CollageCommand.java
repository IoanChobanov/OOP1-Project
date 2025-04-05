package Commands;

import Sessions.SessionManager;

public class CollageCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public CollageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void create(String... args) {
        if (args.length != 4) {
            System.out.println("Invalid arguments. Expected 4 arguments for collage: layout, img1, img2, outimg.");
            return;
        }

        String collageCommand = String.join(" ", args);
        sessionManager.getActiveSession().addTransformation("collage " + collageCommand);

        System.out.println("Queued collage with layout " + args[0] + " for images " + args[1] + " and " + args[2]);
    }
}
