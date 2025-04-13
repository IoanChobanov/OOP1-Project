package Sessions;

import ImageHandling.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionManager {
    private final Map<Integer, Session> sessions;
    private Session activeSession;

    public SessionManager() {
        this.sessions = new HashMap<>();
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
            System.out.println("Session ID " + sessionId + " does not exist.");
        }
    }

    public void removeActiveSession() {
        if (activeSession != null) {
            sessions.remove(activeSession.getSessionId());
            activeSession = null;
        }
    }
}

