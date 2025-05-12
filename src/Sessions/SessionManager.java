package Sessions;

import ImageHandling.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Exceptions.CommandException;

/**
 * Клас за управление на сесии с изображения.
 */
public class SessionManager {
    private final Map<Integer, Session> sessions;
    private Session activeSession;

    public SessionManager() {
        this.sessions = new HashMap<>();
    }

    /**
     * Връща валидна текуща сесия.
     * @return Текущата активна сесия.
     * @throws CommandException Ако няма активна сесия.
     */
    public Session getValidatedActiveSession() throws CommandException {
        if (activeSession == null) {
            throw new CommandException("No active session. Use 'load <file...>' to start a new session or switch to an existing one.");
        }
        return activeSession;
    }

    public int sessionSize(){
        return this.sessions.size();
    }

    public Map<Integer, Session> getSessions() {
        return sessions;
    }

    public Session getActiveSession() {
        return activeSession;
    }

    /**
     * Създава нова сесия с подадените изображения.
     * @param images Колекция с изображения за новата сесия.
     */
    public void createSession(List<Image> images) {
        Session newSession = new Session(images);
        sessions.put(newSession.getSessionId(), newSession);
        activeSession = newSession;
        System.out.println("Session with ID: " + newSession.getSessionId() + " started");
    }

    /**
     * Превключва към сесия с дадено ID.
     * @param sessionId ID на сесията.
     */
    public void switchSession(int sessionId) {
        if (sessions.containsKey(sessionId)) {
            activeSession = sessions.get(sessionId);
            System.out.println("Switched to session ID: " + sessionId);
        } else {
            System.out.println("Session with ID " + sessionId + " does not exist.");
        }
    }

    /**
     * Премахва текущата активна сесия от колекцията със сесии.
     */
    public void removeActiveSession() {
        if (activeSession != null) {
            sessions.remove(activeSession.getSessionId());
            activeSession = null;
        }
    }

}

