package org.schlibbuz.commons.argsscanner;

import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ArgsScannerTest {


    @DataProvider(
        name = "buildAppSettings"
    )
    public static Object[][] buildAppSettings() {
        var args0 = new String[]{};
        var exp0 = new LinkedHashMap<String, Object>();
        exp0.put("runmode", "run");
        exp0.put("target", "index.html");

        var args1 = new String[]{"run"};
        var exp1 = new LinkedHashMap<String, Object>();
        exp1.put("runmode", "run");
        exp1.put("target", "index.html");

        var args2 = new String[]{"default.html"};
        var exp2 = new LinkedHashMap<String, Object>();
        exp2.put("runmode", "run");
        exp2.put("target", "default.html");

        var args3 = new String[]{"--jsoup-only"};
        var exp3 = new LinkedHashMap<String, Object>();
        exp3.put("runmode", "run");
        exp3.put("-jo", 1);
        exp3.put("target", "index.html");

        var args4 = new String[]{"run", "www.google.ch"};
        var exp4 = new LinkedHashMap<String, Object>();
        exp4.put("runmode", "run");
        exp4.put("target", "www.google.ch");

        var args5 = new String[]{"trace", "--selenium-only", "wwwroot"};
        var exp5 = new LinkedHashMap<String, Object>();
        exp5.put("runmode", "trace");
        exp5.put("-so", 1);
        exp5.put("target", "wwwroot");

        return new Object[][] {
            {args0, exp0},
            {args1, exp1},
            {args2, exp2},
            {args3, exp3},
            {args4, exp4},
            {args5, exp5},
        };
    }
    @Test(
        dataProvider = "buildAppSettings"
    )
    public void buildAppSettings(String[] args, Map<String, Object> expected) {
        var inst = new ArgsScanner(args);
        assertEquals(inst.buildAppSettings(args), expected);
    }


    @DataProvider(
        name = "normalizeArgs"
    )
    public static Object[][] normalizeArgs() {
        return new Object[][] {
            {new String[]{}, new LinkedList<String>(Arrays.asList("run", "index.html"))},
            {new String[]{"-jo"}, new LinkedList<String>(Arrays.asList("run", "-jo", "index.html"))},
            {new String[]{"run"}, new LinkedList<String>(Arrays.asList("run", "index.html"))},
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


    @DataProvider(
        name = "normalizeOptions"
    )
    public static Object[][] normalizeOptions() {
        return new Object[][] {
            {new LinkedList<String>(), new LinkedList<String>()},
            {new LinkedList<String>(Arrays.asList("-jo", "-jo", "--jsoup-only")), new LinkedList<String>(Arrays.asList("-jo", "-jo", "-jo"))},
            {new LinkedList<String>(Arrays.asList("-jo", "--selenium-only", "-so")), new LinkedList<String>(Arrays.asList("-jo", "-so", "-so"))},
            {new LinkedList<String>(Arrays.asList("-jo", "-if", "--selenium-only")), new LinkedList<String>(Arrays.asList("-jo", "-if", "-so"))},
        };
    }
    @Test(
        dataProvider = "normalizeOptions"
    )
    public void normalizeOptions(LinkedList<String> options, List<?> expected) {
        var inst = new ArgsScanner(new String[]{});
        assertEquals(inst.normalizeOptions(options), expected);
    }
}
