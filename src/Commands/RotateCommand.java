package Commands;

import Sessions.SessionManager;

public class RotateCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public RotateCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void create(String... args) {
        if (args.length != 1 || (!args[0].equals("left") && !args[0].equals("right"))) {
            System.out.println("Invalid arguments. Use 'rotate left' or 'rotate right'.");
            return;
        }

        String transformation = "rotate_" + args[0];
        sessionManager.getActiveSession().addTransformation(transformation);

        System.out.println("Queued " + args[0] + " rotation for all images in the session.");
    }
}
