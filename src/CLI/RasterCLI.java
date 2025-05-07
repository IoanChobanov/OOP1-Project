package CLI;

import Commands.*;
import Exceptions.CommandException;
import Sessions.SessionManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RasterCLI {
    private final Map<String, CreateCommand> commands = new HashMap<>();
    private final SessionManager sessionManager;

    public RasterCLI() {
        this.sessionManager = new SessionManager();
        InitializeCommands();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+");
            String command = parts[0].toLowerCase();
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);

            if (!commands.containsKey(command)) {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
                continue;
            }

            try {
                commands.get(command).execute(args);
            } catch (IOException | CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void InitializeCommands() {
        commands.put("load", new LoadCommand(sessionManager));
        commands.put("save", new SaveCommand(sessionManager));
        commands.put("saveas", new SaveAsCommand(sessionManager));
        commands.put("close", new CloseCommand(sessionManager));
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("grayscale", new GrayscaleCommand(sessionManager));
        commands.put("monochrome", new MonochromeCommand(sessionManager));
        commands.put("negative", new NegativeCommand(sessionManager));
        commands.put("rotate", new RotateCommand(sessionManager));
        commands.put("undo", new UndoCommand(sessionManager));
        commands.put("add", new AddCommand(sessionManager));
        commands.put("sessioninfo", new SessionInfoCommand(sessionManager));
        commands.put("switch", new SwitchSessionCommand(sessionManager));
        commands.put("collage", new CollageCommand(sessionManager));
    }
}
