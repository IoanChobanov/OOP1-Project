package Commands;

import java.io.IOException;

public class CloseCommand implements CreateCommand{
    @Override
    public void create(String... args) throws IOException {
        System.out.println("Successfully closed session.");
    }
}
