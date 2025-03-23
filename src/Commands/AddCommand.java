package Commands;

import Exceptions.CommandException;

public class AddCommand implements CreateCommand{
    @Override
    public void create(String... args) throws CommandException {
        if(args.length != 1) {
            throw new IllegalArgumentException("Expected 1 argument. Use 'help' for more information.");
        }
        else System.out.println("Added " + args[0] + " to the current session.");
    }
}
