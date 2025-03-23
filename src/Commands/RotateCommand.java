package Commands;

public class RotateCommand implements CreateCommand{
    @Override
    public void create(String... args) {
        if (args.length != 1 || (!args[0].equals("left") && !args[0].equals("right"))) {
            throw new IllegalArgumentException("Use 'rotate left' or 'rotate right'.");
        }
        else System.out.println("Queued rotation for all images in the current session");
    }
}
