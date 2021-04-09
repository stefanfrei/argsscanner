package org.schlibbuz.commons.argsscanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Sets;


final class ArgRules {

    private final Map<String, Arg> rules;

    ArgRules() {
        Set<String> joIncompats = Sets.newHashSet("-so");
        Set<String> soIncompats = Sets.newHashSet("-jo");
        rules = new HashMap<>();

        rules.put(
            "runmode",
            new Arg(
                ArgType.RUNMODE,
                "runmode"
            )
        );
        rules.put(
            "-if",
            new Arg(
                ArgType.OPTION,
                "-if"
            )
        );
        rules.put(
            "-jo",
            new Arg(
                ArgType.OPTION,
                "-jo",
                joIncompats
            )
        );
        rules.put(
            "-so",
            new Arg(
                ArgType.OPTION,
                "-so",
                soIncompats
            )
        );
        rules.put(
            "target",
            new Arg(
                ArgType.TARGET,
                "target"
            )
        );
    }

    ArgRules(HashMap<String, Arg> rules) {
        this.rules = rules;
    }
}
