package org.schlibbuz.commons.argsscanner;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ArgsScannerConfig {

    private static final Logger w = LogManager.getLogger(ArgsScannerConfig.class);


    ArgsScannerConfig() {}

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

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArgsScannerConfig();
    }

    public String get(String key) {
        return "blaa -> " + key;
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
