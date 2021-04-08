package org.schlibbuz.commons.argsscanner;

import static org.testng.Assert.*;

import java.lang.reflect.Method;

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
    public void pre(Method method) {
        if (!method.getName().equals("fromJSON")) {
            w.trace("ran");
            inst = ArgsScannerConfig.fromJSON(AS_CONFIG_JSON);
        }
    }

    @AfterMethod
    public void post(Method method) {
        if (!method.getName().equals("fromJSON")) {
            inst = null;
        }
    }

    @DataProvider(name = "fromJSON")
    public static Object[][] fromJSON() {
        return new Object[][] {
            {"src/main/resources/as.config.json", new ArgsScannerConfig()},
        };
    }
    @Test(
        dataProvider = "fromJSON"
    )
    public void fromJSON(String file, ArgsScannerConfig expected) {
        var inst = ArgsScannerConfig.fromJSON(AS_CONFIG_JSON);
        assertEquals(inst, expected);
    }


    @DataProvider(
        name = "get"
    )
    public static Object[][] get() {
        return new Object[][] {
            {"stefan", "blaa -> stefan"},
        };
    }
    @Test(
        dataProvider = "get",
        dependsOnMethods = { "fromJSON" }
    )
    public void get(String key, String expected) {
        assertEquals(inst.get(key), expected);
    }
}
