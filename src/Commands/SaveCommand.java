package Commands;

import java.io.IOException;

public class SaveCommand implements CreateCommand{
    @Override
    public void create(String... args) throws IOException {
        System.out.println("Saved successfully!");
    }
}
