package org.schlibbuz.commons.argsscanner;


public class ArgError {

    private final int index;
    private final String source;
    private final String description;


    ArgError(int index, String source, String description) {
        this.index = index;
        this.source = source;
        this.description = description;
    }

    int getIndex() {
        return index;
    }

    String getSource() {
        return source;
    }

    String getDescription() {
        return description;
    }
}
