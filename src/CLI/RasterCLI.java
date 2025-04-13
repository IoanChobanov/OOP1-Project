package CLI;

import Commands.*;
import Exceptions.CommandException;
import Sessions.SessionManager;

import java.io.IOException;
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
            String command = null;
            String[] args = new String[0];

            for (int i = parts.length; i > 0; i--) {
                String candidate = String.join(" ", java.util.Arrays.copyOfRange(parts, 0, i)).toLowerCase();
                if (commands.containsKey(candidate)) {
                    command = candidate;
                    args = java.util.Arrays.copyOfRange(parts, i, parts.length);
                    break;
                }
            }

            if (command == null) {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
                continue;
            }

            CreateCommand cmd = commands.get(command);
            try {
                cmd.execute(args);
            } catch (IOException | CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void InitializeCommands() {
        commands.put("load", new LoadCommand(sessionManager));
        commands.put("save", new SaveCommand(sessionManager));
        commands.put("saveas", new SaveAsCommand());
        commands.put("close", new CloseCommand(sessionManager));
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("grayscale", new GrayscaleCommand(sessionManager));
        commands.put("monochrome", new MonochromeCommand(sessionManager));
        commands.put("negative", new NegativeCommand());
        commands.put("rotate", new RotateCommand(sessionManager));
        commands.put("undo", new UndoCommand(sessionManager));
        commands.put("add", new AddCommand(sessionManager));
        commands.put("session info", new SessionInfoCommand(sessionManager));
        commands.put("switch", new SwitchSessionCommand(sessionManager));
        commands.put("collage", new CollageCommand(sessionManager));
    }
}
