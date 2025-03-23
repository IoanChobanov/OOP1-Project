package Commands;

import java.io.IOException;

public class SwitchSessionCommand implements CreateCommand{
    @Override
    public void create(String... args) throws IOException {
        if(args.length != 1){
            throw new IllegalArgumentException("You need to input 1 session ID.");
        }
    }
}
