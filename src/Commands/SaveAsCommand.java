package Commands;

import java.io.IOException;

public class SaveAsCommand implements CreateCommand{
    @Override
    public void create(String... args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected 1 argument. Use 'help' for more information.");
        }

        System.out.println("Successfully saved as " + args[0]);
    }
}
