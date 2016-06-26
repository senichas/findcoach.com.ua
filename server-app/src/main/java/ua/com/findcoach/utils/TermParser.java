package ua.com.findcoach.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TermParser {
    private static final Pattern WEEK_PATTERN = Pattern.compile("^([0-9]+)(week)s?$");
    private static final int DAYS_IN_WEEK = 7;

    private TermParser() {
    }

    public static Integer parseTerm(String term) {
        Matcher matcher = WEEK_PATTERN.matcher(term);
        if (matcher.find()) {
            Integer weeksCount = Integer.parseInt(matcher.group(1));
            return weeksCount * DAYS_IN_WEEK;
        }

        throw new IllegalArgumentException("Provided term is not valid. Term = " + term);
    }
}
