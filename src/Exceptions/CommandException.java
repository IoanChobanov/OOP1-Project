package Exceptions;

/**
 * Клас, който добавя собствено обработване на грешки.
 */
public class CommandException extends Exception{
    /**
     * Конструктор, приемащ съобещение за грешка.
     * @param message String съобщение за грешка.
     */
    public CommandException(String message) {
        super(message);
    }
}
