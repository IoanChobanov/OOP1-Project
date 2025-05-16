package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import Sessions.Session;
import Sessions.SessionManager;

/**
 * Клас, който служи за принтиране на информация за текущата сесия.
 */
public class SessionInfoCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public SessionInfoCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който принтира нужната информация за текущата сесия.
     * @param args Аргументите, подадени от менюто (В този случай не ни трябват).
     * @throws CommandException При липсата на текуща сесия.
     */
    @Override
    public void execute(String... args) throws CommandException {
        Session activeSession = sessionManager.getValidatedActiveSession();

        System.out.println("Session ID: " + activeSession.getSessionId());
        System.out.println(activeSession.getTransformations().isEmpty() ?
                "No transformations queued." :
                "Transformations queued (" + activeSession.getTransformations().size() + "): " + activeSession.getTransformations());
        System.out.println("Images in session (" + activeSession.getImages().size() + "):");

        int imageCounter = 1;
        for (Image image : activeSession.getImages()) {
            System.out.printf("%d. %s (%s, %dx%d)%n",
                    imageCounter++,
                    image.getFile().getName(),
                    image.getFormat(),
                    image.getWidth(),
                    image.getHeight());
        }
    }
}
