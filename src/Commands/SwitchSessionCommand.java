package Commands;

import java.io.IOException;

import Exceptions.CommandException;
import Sessions.SessionManager;

public class SwitchSessionCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public SwitchSessionCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String... args) throws IOException, CommandException {
        if(args.length != 1){
            throw new CommandException("You need to input 1 session ID.");
        }

        try {
            int sessionId = Integer.parseInt(args[0]);
            sessionManager.switchSession(sessionId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid session ID. It must be a number.");
        }
    }
}
