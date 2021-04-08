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
}
