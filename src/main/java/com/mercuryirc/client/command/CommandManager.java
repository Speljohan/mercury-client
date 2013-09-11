package com.mercuryirc.client.command;

import com.mercuryirc.client.misc.Util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: Johan
 * Date: 2013-09-11
 * Time: 17:56
 */
public class CommandManager {
    private static CommandManager INSTANCE;

    private HashMap<String, Command> commands;

    public static CommandManager getInstance() {
        return INSTANCE;
    }

    public void register(Command command) {
        commands.put(command.getCommand(), command);
    }

    public void unregister(String command) {
        commands.remove(command);
    }

    public Command getCommand(String command) {
        return commands.get(command);
    }

    public void handle(String input) {
        for (Command command : commands.values()) {
            if (command.accept(input)) {
                ArrayList<String> tokens = Util.parseCommand(input);
                String cmd = tokens.get(0);
                tokens.remove(0);
                command.handle(cmd, tokens.toArray(new String[tokens.size()]));
                return;
            }
        }
    }

    private CommandManager() {
        commands = new HashMap<>();

    }

    static {
        INSTANCE  = new CommandManager();

    }
}
