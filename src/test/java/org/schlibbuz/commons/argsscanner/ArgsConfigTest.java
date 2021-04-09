package org.schlibbuz.commons.argsscanner;

import static org.testng.Assert.*;

import java.lang.reflect.Method;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ArgsConfigTest {
    
    private static final Logger w = LogManager.getLogger(ArgsConfigTest.class);
    private static final String AS_CONFIG_JSON = "src/main/resources/as.config.json";

    private ArgsConfig inst;


    @BeforeMethod
    public void pre(Method method) {
        if (!method.getName().equals("fromJSON")) {
            w.trace("ran");
            inst = ArgsConfig.fromJSON(AS_CONFIG_JSON);
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
            {"src/main/resources/as.config.json", new ArgsConfig()},
        };
    }
    @Test(
        dataProvider = "fromJSON"
    )
    public void fromJSON(String file, ArgsConfig expected) {
        var inst = ArgsConfig.fromJSON(AS_CONFIG_JSON);
        assertEquals(inst, expected);
    }

    @DataProvider(name = "isOption")
    public static Object[][] isOption() {
        return new Object[][] {
            {"", false},
            {"-", true},
            {"-jo", true},
            {"--jsoup-only", true},
            {"---hasta-la-vista-baby", true},
            {"jojo", false},
        };
    }
    @Test(dataProvider = "isOption")
    public void isOption(String arg, boolean expected) {
        assertEquals(inst.isOption(arg), expected);
    }

    @DataProvider(name = "isOptionLong")
    public static Object[][] isOptionLong() {
        return new Object[][] {
            {"", false},
            {" ", false},
            {"- ", false},
            {"--", true},
            {"-jo", false},
            {"--jsoup-only", true},
            {"--selenium-only", true},
            {"---hasta-la-vista-baby", true},
            {"jojo", false},
        };
    }
    @Test(dataProvider = "isOptionLong")
    public void isOptionLong(String arg, boolean expected) {
        assertEquals(inst.isOptionLong(arg), expected);
    }

    @DataProvider(name = "isRunModeValid")
    public static Object[][] isRunModeValid() {
        return new Object[][] {
            {"brumm", false},
            {"-jo", false},
            {"--selenuim-only", false},
            {"", false},
            {"-jo", false},
            {"run", true},
            {"trace", true},
            {"TRACE", true},
            {"TrAcE", true},
        };
    }
    @Test(dataProvider = "isRunModeValid")
    public void isRunModeValid(String arg, boolean expected) {
        assertEquals(inst.isRunModeValid(arg), expected);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(ArgsConfig.class).verify();
    }
}
