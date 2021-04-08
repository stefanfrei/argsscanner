package org.schlibbuz.commons.argsscanner;

import static org.testng.Assert.*;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ArgsScannerConfigTest {
    
    private static final Logger w = LogManager.getLogger(ArgsScannerConfigTest.class);
    private static final String AS_CONFIG_JSON = "src/main/resources/as.config.json";

    private ArgsScannerConfig inst;

    @BeforeMethod
    public void pre() {
        w.trace(new File("").getAbsolutePath());
        inst = ArgsScannerConfig.fromJSON(AS_CONFIG_JSON);
    }

    @AfterMethod
    public void post() {
        inst = null;
    }

    @DataProvider(name = "fromJSON")
    public static Object[][] fromJSON() {
        return new Object[][] {
            {"src/main/resources/as.config.json", new ArgsScannerConfig()},
        };
    }
    @Test(dataProvider = "fromJSON")
    public void fromJSON(String file, ArgsScannerConfig expected) {
        assertEquals(inst, expected);
    }

    @DataProvider(name = "get")
    public static Object[][] get() {
        return new Object[][] {
            {"stefan", "blaa -> stefan"},
        };
    }
    @Test(dataProvider = "get")
    public void get(String key, String expected) {
        var inst = ArgsScannerConfig.fromJSON(AS_CONFIG_JSON);
        assertEquals(inst.get(key), expected);
    }
}
