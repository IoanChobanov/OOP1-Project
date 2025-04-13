package Commands;

public class NegativeCommand implements CreateCommand{
    @Override
    public void execute(String... args)  {
        System.out.println("Queued negative filter for all images in the session.");
    }
}
