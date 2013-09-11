package com.mercuryirc.client.script;

import org.jruby.RubyBoolean;
import org.jruby.RubyProc;
import org.jruby.RubyString;
import org.jruby.RubySymbol;

import java.util.ArrayList;

/**
 * User: Johan
 * Date: 2013-03-14
 * Time: 12:22
 */
public class EventManager {

    private static EventManager ourInstance = new EventManager();

    public static EventManager getInstance() {
        return ourInstance;
    }

    private final ArrayList<Trigger> triggers;

    public EventManager() {
        this.triggers = new ArrayList<Trigger>();
    }

    public void register(RubyString id, RubySymbol trigger, RubyBoolean threaded, RubyProc proc) {
        Trigger t = new Trigger(trigger.asJavaString(), id.asJavaString(), proc);
        if (threaded.isFalse()) {
            t.setThreaded(false);
        }
        triggers.add(t);
    }

    public boolean trigger(String trigger, Object... args) {
        for (Trigger t : triggers) {
            if (trigger.equals(t.getTrigger())) {
                if (args != null) {
                    t.setArgs(args);
                }
                if (!t.isThreaded()) {
                    ScriptManager.getInstance().container.callMethod(t.getBlock(), "call", t.getArgs());
                    return true;
                }
            }
        }
        return false;
    }

}
