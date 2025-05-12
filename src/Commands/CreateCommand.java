package Commands;

import Exceptions.CommandException;

import java.io.IOException;

/**
 * Интерфейс за командите от менюто.
 */
public interface CreateCommand {
    /**
     * Метод, който всички класове, които наследяват този клас ще трябва да реализират.
     * @param args Аргументите, подадени от менюто.
     * @throws IOException Обработва грешки свързани с файлова обработка.
     * @throws CommandException Обработва грешки по време на изпълнение.
     */
    void execute(String... args) throws IOException, CommandException;
}
