package Commands;

public class GrayscaleCommand implements CreateCommand{
    @Override
    public void create(String... args) {
        System.out.println("Queued grayscale filter for all images in the current session.");
    }
}
