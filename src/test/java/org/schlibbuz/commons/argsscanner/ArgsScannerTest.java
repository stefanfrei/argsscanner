package org.schlibbuz.commons.argsscanner;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ArgsScannerTest {


    @DataProvider(name = "dumpArgs")
    public static Object[][] dumpArgs() {
        return new Object[][] {
            {new String[]{"a", "b", "c"}, "a b c"},
            {new String[]{"run", "-jo", "index2.html"}, "run -jo index2.html"},
        };
    }
    @Test(dataProvider = "dumpArgs")
    public void dumpArgs(String[] args, String expected) {
        var inst = new ArgsScanner(args);
        assertEquals(inst.dumpArgs(args), expected);
    }
}
