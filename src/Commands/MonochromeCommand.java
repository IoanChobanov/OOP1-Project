package Commands;

import Sessions.SessionManager;

public class MonochromeCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public MonochromeCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args)  {
        sessionManager.getActiveSession().addTransformation("monochrome");
        System.out.println("Queued monochrome filter for all images in the session.");
    }
}
