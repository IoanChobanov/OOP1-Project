package Sessions;

import java.util.HashMap;
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

    public void createSession(String image) {
        Session newSession = new Session(image);
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
}

