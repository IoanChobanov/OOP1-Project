package Commands;

import Exceptions.CommandException;
import Sessions.SessionManager;

import java.io.IOException;
import java.util.ArrayList;

public class LoadCommand implements CreateCommand {
    private final SessionManager sessionManager;
    private final AddCommand addCommand;

    public LoadCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.addCommand = new AddCommand(sessionManager);
    }

    @Override
    public void execute(String... args) throws CommandException, IOException {
        if (args.length == 0) {
            throw new CommandException("You need to load at least 1 file! Use 'help' for more information.");
        }

        sessionManager.createSession(new ArrayList<>());

        for (String fileName : args) {
            addCommand.execute(fileName);
        }
    }
}
