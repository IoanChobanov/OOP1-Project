package Commands;

import java.io.IOException;

public class LoadCommand implements CreateCommand{
    @Override
    public void create(String... args) throws IOException {
        if(args.length == 0){
            throw new IllegalArgumentException("You need to load at least 1 file! Use 'help' for more information.");
        }
        else System.out.println("Successfully loaded " + args[0]);
    }
}
