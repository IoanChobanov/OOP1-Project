package Commands;

import Exceptions.CommandException;
import Sessions.SessionManager;

/**
 * Клас, който имплементира затваряне на текущата сесия.
 */
public class CloseCommand implements CreateCommand {
    private final SessionManager sessionManager;

    public CloseCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който проверява дали има активна сесия и ако има я премахва от колекцията със сесии.
     * @param args Аргумент, подаден от менюто.
     * @throws CommandException Обработва грешки, в случай, че няма валидна активна сесия.
     */
    @Override
    public void execute(String... args) throws CommandException {
        sessionManager.getValidatedActiveSession();
        sessionManager.removeActiveSession();

        System.out.println("Session closed successfully. All unsaved changes were discarded.");
        System.out.println("Remaining sessions: " + sessionManager.sessionSize());
        if(sessionManager.sessionSize()!=0){
            for (Integer sessionId : sessionManager.getSessions().keySet()){
                System.out.println("Available sessions (ID): " + sessionId);
            }
        }
    }
}

