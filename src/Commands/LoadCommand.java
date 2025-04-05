package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import ImageHandling.ImageLoader;
import Sessions.SessionManager;
import java.io.File;
import java.io.IOException;


public class LoadCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public LoadCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void create(String... args) throws CommandException, IOException {
        if(args.length == 0){
            throw new CommandException("You need to load at least 1 file! Use 'help' for more information.");
        }
        System.out.println("Session with ID: " + args[0] + " started");

        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IOException("File not found: " + args[0]);
        }

        Image image = ImageLoader.loadImage(file);
        image.load();

        sessionManager.createSession(image);
    }
}
