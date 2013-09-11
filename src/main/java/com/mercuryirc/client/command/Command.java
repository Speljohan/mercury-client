package com.mercuryirc.client.command;

/**
 * User: Johan
 * Date: 2013-09-11
 * Time: 17:53
 */
public abstract class Command {

    private String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public boolean accept(String input) {
        return input.matches(command);
    }

    public abstract void handle(String command, String[] args);
}
