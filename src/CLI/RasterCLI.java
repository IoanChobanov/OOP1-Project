package CLI;

import Commands.*;
import Exceptions.CommandException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RasterCLI {
    private final Map<String, CreateCommand> commands = new HashMap<>();

    public RasterCLI()
    {
        AddCommands(commands);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");
            if (parts.length == 0) continue;

            String command = parts[0].toLowerCase();
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);

            CreateCommand cmd = commands.get(command);
            if (cmd != null)  {
                try {
                    cmd.create(args);
                } catch (IOException | CommandException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }
    }

    public void AddCommands(Map<String, CreateCommand> commands) {
        commands.put("load", new LoadCommand());
        commands.put("save", new SaveCommand());
        commands.put("saveas", new SaveAsCommand());
        commands.put("close", new CloseCommand());
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("grayscale", new GrayscaleCommand());
        commands.put("monochrome", new MonochromeCommand());
        commands.put("negative", new NegativeCommand());
        commands.put("rotate", new RotateCommand());
        commands.put("undo", new UndoCommand());
        commands.put("add", new AddCommand());
        commands.put("session info", new SessionInfoCommand());
        commands.put("switch", new SwitchSessionCommand());
        commands.put("collage", new CollageCommand());
    }
}
