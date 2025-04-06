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

        for (String fileName : args) {
            File file = new File(fileName);

            if (!file.exists()) {
                throw new IOException("File not found: " + fileName);
            }

            Image image = ImageLoader.loadImage(file);
            image.load();

            if (sessionManager.getActiveSession() == null) {
                sessionManager.createSession(image);
            } else {
                sessionManager.getActiveSession().addImage(image);
            }

            System.out.println("Loaded image: " + fileName);
            System.out.println("Session started.");
        }
    }
}
