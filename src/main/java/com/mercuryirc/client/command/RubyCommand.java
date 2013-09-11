package com.mercuryirc.client.command;

import com.mercuryirc.client.script.ScriptManager;
import org.jruby.RubyProc;

/**
 * User: Johan
 * Date: 2013-09-11
 * Time: 18:00
 */
public class RubyCommand extends Command {

    private RubyProc proc;

    public RubyCommand(String command, RubyProc proc) {
        super(command);
        this.proc = proc;
    }

    public RubyProc getProc() {
        return proc;
    }

    public void handle(String command, String[] args) {
        ScriptManager.getInstance().container.callMethod(proc, "call", args);
    }

}
