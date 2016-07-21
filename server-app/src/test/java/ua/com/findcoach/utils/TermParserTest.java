package ua.com.findcoach.utils;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TermParserTest {

    @Test
    public void parseWeeksIntoDays() throws Exception {
        assertThat(TermParser.parseTerm("1week"), equalTo(7));
        assertThat(TermParser.parseTerm("5weeks"), equalTo(35));
        assertThat(TermParser.parseTerm("52weeks"), equalTo(364));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfStringIsOnlyNumbers() {
        TermParser.parseTerm("33333");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfStringIsWrong() {
        TermParser.parseTerm("7huev");
    }
}