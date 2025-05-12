package Commands;

import java.io.IOException;

import Exceptions.CommandException;
import Sessions.SessionManager;

/**
 * Клас, който имплементира прехвърляне от една сесия към друга.
 */
public class SwitchSessionCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public SwitchSessionCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който прехвърля потребителя от една сесия към друга според номер (ID) на сесия.
     * @param args Аргументите, подадени от менюто.
     * @throws CommandException При невалидни аргументи и невалидно (ID) на сесията.
     */
    @Override
    public void execute(String... args) throws CommandException {
        if(args.length != 1){
            throw new CommandException("You need to input 1 session ID.");
        }

        try {
            int sessionId = Integer.parseInt(args[0]);
            sessionManager.switchSession(sessionId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid session ID. It must be a number.");
        }
    }
}
