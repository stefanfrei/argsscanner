package org.schlibbuz.commons.argsscanner;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class ArgsConfig {

    private static final Logger w = LogManager.getLogger(ArgsConfig.class);

    private static final String OPT_JO_SHORT = "-jo";
    private static final String OPT_JO_LONG = "--jsoup-only";
    private static final String OPT_SO_SHORT = "-so";
    private static final String OPT_SO_LONG = "--selenium-only";
    private static final String RUNMODE_RUN = "run";
    private static final String RUNMODE_TRACE = "trace";
    private static final String TARGET_DEFAULT = "index.html";

    private final String runMode;
    private final String target;
    private final Map<String, String> optionDefinitions;
    private final Map<String, String> optionAliases;


    ArgsConfig() {
        runMode = RUNMODE_RUN;
        target = TARGET_DEFAULT;
        optionDefinitions = loadOptionDefinitions();
        optionAliases = loadOptionAliases();
    }

    public static ArgsConfig fromJSON(final String file) {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(file));

            // convert JSON file to map
            Map<?, ?> map = gson.fromJson(reader, Map.class);

            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                w.trace("{}={}", entry.getKey(), entry.getValue());
            }

            // close reader
            reader.close();

        } catch(IOException e) {
            w.error(e.getMessage());
        }
        return new ArgsConfig();
    }

    Map<String, String> loadOptionDefinitions() {
        var m = new HashMap<String, String>();
        m.put(OPT_JO_LONG, OPT_JO_SHORT);
        m.put(OPT_JO_SHORT, OPT_JO_SHORT);
        m.put(OPT_SO_LONG, OPT_SO_SHORT);
        m.put(OPT_SO_SHORT, OPT_SO_SHORT);
        return m;
    }

    Map<String, String> loadOptionAliases() {
        Map<String, String> m = new HashMap<>();
        m.put(OPT_JO_LONG, OPT_JO_SHORT);
        m.put(OPT_SO_LONG, OPT_SO_SHORT);
        return m;
    }

    List<String> getRunModes() {
        List<String> l = new ArrayList<>();
        l.add(RUNMODE_RUN);
        l.add(RUNMODE_TRACE);
        return l;
    }

    String getRunMode() {
        return runMode;
    }

    String getTarget() {
        return target;
    }

    String getOption(String key) {
        return optionDefinitions.get(key);
    }

    String getOptionShort(String option) {
        return optionAliases.get(option);
    }

    boolean isOption(final String arg) {
        return  !arg.isBlank()
                &&
                arg.startsWith("-");
    }

    boolean isOptionLong(final String arg) {
        return  arg.length() > 1
                &&
                arg.startsWith("--");
    }

    boolean isOptionValid(final String option) {
        return optionDefinitions.containsKey(option);
    }

    boolean isRunModeValid(final String arg) {
        for(var m : getRunModes()) {
            if (m.equalsIgnoreCase(arg))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof ArgsConfig)) 
            return false;

        ArgsConfig other = (ArgsConfig) o;

        return  Objects.equals(runMode, other.runMode)
                &&
                Objects.equals(target, other.target)
                &&
                Objects.equals(optionDefinitions, other.optionDefinitions)
                &&
                Objects.equals(optionAliases, other.optionAliases);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.runMode);
        hash = 17 * hash + Objects.hashCode(this.target);
        hash = 17 * hash + Objects.hashCode(this.optionDefinitions);
        hash = 17 * hash + Objects.hashCode(this.optionAliases);
        return hash;
    }

}
