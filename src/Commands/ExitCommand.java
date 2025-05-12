package Commands;

/**
 * Клас, който имплементира излизане от програма.
 */
public class ExitCommand implements CreateCommand{
    /**
     * Метод, който изпълнява командата за излизанет от програмата.
     * @param args Аргументите, подадени от менюто (В този случай не ни трябват).
     */
    @Override
    public void execute(String... args)  {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
