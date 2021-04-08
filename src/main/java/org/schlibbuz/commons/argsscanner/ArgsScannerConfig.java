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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ArgsScannerConfig {

    private static final Logger w = LogManager.getLogger(ArgsScannerConfig.class);

    private static final String OPT_JO_SHORT = "-jo";
    private static final String OPT_JO_LONG = "--jsoup-only";
    private static final String OPT_SO_SHORT = "-so";
    private static final String OPT_SO_LONG = "--selenium-only";
    private static final String RUNMODE_RUN = "run";
    private static final String RUNMODE_TRACE = "trace";
    private static final String TARGET_DEFAULT = "index.html";

    private Map<String, String> config;


    ArgsScannerConfig() {
        config = new HashMap<>();
        config.put("runmodes->default", RUNMODE_RUN);
        config.put("target-default", TARGET_DEFAULT);
    }

    public static ArgsScannerConfig fromJSON(final String file) {
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
        return new ArgsScannerConfig();
    }

    Map<String, String> loadOptions() {
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

    public String get(String key) {
        return config.get(key);
    }

    @Override
    public boolean equals(Object o) {

        if (o == null)
            return false;

        if (o == this)
            return true;

        if (o.getClass() == this.getClass())
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
}
