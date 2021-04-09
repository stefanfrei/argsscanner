package org.schlibbuz.commons.argsscanner;


public enum ArgType {

    RUNMODE("runmode"),
    OPTION("option"),
    SOURCE("source"),
    TARGET("target");

    private final String name;


    ArgType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ArgType fromName(String name) {
        for(ArgType t : values()) {
            if(t.name.equals(name)) return t;
        }
        return null;
    }
}
