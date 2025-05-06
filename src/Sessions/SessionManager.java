package Sessions;

import ImageHandling.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Exceptions.CommandException;

public class SessionManager {
    private final Map<Integer, Session> sessions;
    private Session activeSession;

    public SessionManager() {
        this.sessions = new HashMap<>();
    }

    public Session getValidatedActiveSession() throws CommandException {
        if (activeSession == null) {
            throw new CommandException("No active session. Use 'load' to start a new session or switch to an existing one.");
        }
        return activeSession;
    }

    public Session getActiveSession() {
        return activeSession;
    }

    public void createSession(List<Image> images) {
        Session newSession = new Session(images);
        sessions.put(newSession.getSessionId(), newSession);
        activeSession = newSession;
        System.out.println("Session with ID: " + newSession.getSessionId() + " started");
    }

    public void switchSession(int sessionId) {
        if (sessions.containsKey(sessionId)) {
            activeSession = sessions.get(sessionId);
            System.out.println("Switched to session ID: " + sessionId);
        } else {
            System.out.println("Session with ID " + sessionId + " does not exist.");
        }
    }

    public void removeActiveSession() {
        if (activeSession != null) {
            sessions.remove(activeSession.getSessionId());
            activeSession = null;
        }
    }

}

