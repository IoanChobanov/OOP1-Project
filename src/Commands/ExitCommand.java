package Commands;

public class ExitCommand implements CreateCommand{
    @Override
    public void execute(String... args)  {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
