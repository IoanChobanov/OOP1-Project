package Commands;

import Exceptions.CommandException;

import java.io.IOException;

public interface CreateCommand {
    void create(String... args) throws IOException, CommandException;
}
