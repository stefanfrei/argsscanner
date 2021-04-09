package org.schlibbuz.commons.argsscanner;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public final class Arg {
    
    private final ArgType type;
    private final String name;
    private final List<String> params;
    private final Set<String> incompats;

    Arg(ArgType type, String name) {
        this(type, name, new LinkedList<>(), new HashSet<>());
    }

    Arg(ArgType type, String name, LinkedList<String> params) {
        this(type, name, params, new HashSet<>());
    }

    Arg(ArgType type, String name, Set<String> incompats) {
        this(type, name, new LinkedList<>(), incompats);
    }

    Arg(ArgType type, String name, LinkedList<String> params, Set<String> incompats) {
        this.type = type;
        this.name = name;
        this.params = params;
        this.incompats = incompats;
    }

    ArgType getType() {
        return type;
    }
    String getName() {
        return name;
    }

    List<String> getParams() {
        return params;
    }

    Set<String> getIncompats() {
        return incompats;
    }

    boolean isNullParam() {
        return params.isEmpty();
    }

    boolean isMultiParam() {
        return params.size() > 1;
    }
}
