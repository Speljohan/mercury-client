package com.mercuryirc.client.script;

/**
 * User: Johan
 * Date: 2013-09-07
 * Time: 09:41
 */
public class ScriptDescriptor {

    private String id, name, description;
    private int version;

    public ScriptDescriptor(String id, String name, String description, int version) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
