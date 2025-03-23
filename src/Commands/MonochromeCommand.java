package Commands;

public class MonochromeCommand implements CreateCommand{
    @Override
    public void create(String... args)  {
        System.out.println("Queued monochrome filter for all images in the session.");
    }
}
