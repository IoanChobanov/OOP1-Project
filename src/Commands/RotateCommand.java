package Commands;

import Exceptions.CommandException;

public class RotateCommand implements CreateCommand{
    @Override
    public void create(String... args) throws CommandException{
        if (args.length != 1 || (!args[0].equals("left") && !args[0].equals("right"))) {
            throw new CommandException("Use 'rotate left' or 'rotate right'.");
        }
        else System.out.println("Queued " + args[0] + " rotation for all images in the current session");
    }
}
