package com.mercuryirc.client.script;

import com.mercuryirc.client.misc.Settings;
import org.jruby.CompatVersion;
import org.jruby.embed.LocalContextScope;
import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: Johan
 * Date: 2013-09-07
 * Time: 08:04
 */
public class ScriptManager {

    private static final ScriptManager INSTANCE;

    private static File DIRECTORY;

    private ArrayList<ScriptDescriptor> scripts;

    private ScriptingContainer container;

    private FilenameFilter rubyFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".rb");
        }
    };

    public static ScriptManager getInstance() {
        return INSTANCE;
    }

    public void reloadScripts() {
        scripts.clear();
        init();
        File files[] = DIRECTORY.listFiles(rubyFilter);
        for (int i = 0; files != null && i < files.length; i++) {
            container.runScriptlet(PathType.ABSOLUTE, files[i].getAbsolutePath());
        }
    }

    public void register(ScriptDescriptor descriptor) {
        this.scripts.add(descriptor);
    }

    public ScriptManager() {
        DIRECTORY = new File(Settings.APP_DIR, "scripts");
        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdir();
        }

        this.scripts = new ArrayList<>();
        this.container = new ScriptingContainer(LocalContextScope.SINGLETON);
    }

    public void init() {
        container.terminate();
        container.setLoadPaths(Arrays.asList("ruby"));
        container.setCompatVersion(CompatVersion.RUBY2_0);
        container.runScriptlet(PathType.CLASSPATH, "ruby/bootstrap.rb");
    }

    static {
        INSTANCE = new ScriptManager();
    }
}