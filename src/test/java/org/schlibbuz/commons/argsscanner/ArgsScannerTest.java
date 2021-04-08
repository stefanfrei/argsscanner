package org.schlibbuz.commons.argsscanner;

import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ArgsScannerTest {


    @DataProvider(
        name = "dumpArgs"
    )
    public static Object[][] dumpArgs() {
        return new Object[][] {
            {new String[]{"a", "b", "c"}, "a b c"},
            {new String[]{"run", "-jo", "index2.html"}, "run -jo index2.html"},
        };
    }
    @Test(
        dataProvider = "dumpArgs"
    )
    public void dumpArgs(String[] args, String expected) {
        var inst = new ArgsScanner(args);
        assertEquals(inst.dumpArgs(args), expected);
    }


    @DataProvider(
        name = "normalizeArgs"
    )
    public static Object[][] normalizeArgs() {
        return new Object[][] {
            {new String[]{}, new LinkedList<String>(Arrays.asList("run", "index.html"))},
            {new String[]{"-jo"}, new LinkedList<String>(Arrays.asList("run", "-jo", "index.html"))},
            {new String[]{"--jsoup-only"}, new LinkedList<String>(Arrays.asList("run", "-jo", "index.html"))},
            {new String[]{"run", "index.html"}, new LinkedList<String>(Arrays.asList("run", "index.html"))},
            {new String[]{"run", "-jo", "index.html"}, new LinkedList<String>(Arrays.asList("run", "-jo", "index.html"))},
        };
    }
    @Test(
        dataProvider = "normalizeArgs"
    )
    public void normalizeArgs(String[] args, List<?> expected) {
        var inst = new ArgsScanner(args);
        assertEquals(inst.normalizeArgs(args), expected);
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
    public void isOption(String option, boolean expected) {
        var inst = new ArgsScanner(new String[]{});
        assertEquals(inst.isOption(option), expected);
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
    public void isOptionLong(String option, boolean expected) {
        var inst = new ArgsScanner(new String[]{});
        assertEquals(inst.isOptionLong(option), expected);
    }
}
