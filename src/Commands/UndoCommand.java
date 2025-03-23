package Commands;

public class UndoCommand implements CreateCommand{
    @Override
    public void create(String... args)  {
        System.out.println("One change undone.");
    }
}
