package Commands;

import Exceptions.CommandException;

import java.io.IOException;

public interface CreateCommand {
    void execute(String... args) throws IOException, CommandException;
}
