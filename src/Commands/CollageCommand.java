package Commands;

public class CollageCommand implements CreateCommand{
    @Override
    public void create(String... args) throws IllegalArgumentException {
        if(args.length != 4){
            throw new IllegalArgumentException("Expected 4 arguments. Use 'help' for more information.");
        }
    }
}
