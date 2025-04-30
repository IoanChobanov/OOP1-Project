package Commands;

import Exceptions.CommandException;
import Sessions.SessionManager;

import java.io.File;
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

        for (String fileName : args) {
            File file = new File(fileName);
            if (!file.exists()) {
                file = new File("example_images/" + fileName);
            }
            if (!file.exists()) {
                throw new CommandException("File not found: " + fileName);
            }
        }

        sessionManager.createSession(new ArrayList<>());
        for (String fileName : args) {
            addCommand.execute(fileName);
        }
    }
}
