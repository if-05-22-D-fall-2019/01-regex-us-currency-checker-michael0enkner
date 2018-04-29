import org.junit.Test;

import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UsCurrencyCheckerTest {
    @Test
    public void testValidCurrencyString() {
        checkRegex("$ 149.50");
    }

    private void checkRegex(final String stringToBeChecked) {
        checkRegex(stringToBeChecked, stringToBeChecked);
    }

    private void checkRegex(final String stringToBeChecked, final String expectedMatch) {
        Matcher matcher = UsCurrencyChecker.getMatcher(stringToBeChecked);
        assertThat(matcher.find(), is(true));
        assertThat(matcher.group(), is(expectedMatch));
        assertThat(matcher.find(), is(false));
    }

    @Test
    public void testNoCurrency() {
        Matcher matcher = UsCurrencyChecker.getMatcher("This is text without any currency information");
        assertThat(matcher.find(), is(false));
    }

    @Test
    public void testNoBlankNoZero() {
        checkRegex("$149.5");
    }

    @Test
    public void testOnlyBlankBetween$AndNumber() {
        Matcher matcher = UsCurrencyChecker.getMatcher("$x149.5");
        assertThat(matcher.find(), is(false));
    }

    @Test
    public void testNoDecimals() {
        checkRegex("$ 200");
    }

    @Test
    public void testCurrencyEmbeddedInAText() {
        checkRegex("This makes $ 149.30 in total", "$ 149.30");
    }

    @Test
    public void testMoreThanTwoDecimals() {
        checkRegex("$1.214", "$1.21");
    }

    @Test
    public void testShortestCurrencyPossible() {
        checkRegex("$3");
    }

    @Test
    public void testDecimalPointButNoDecimals() {
        checkRegex("$ 3468.", "$ 3468");
    }

    @Test
    public void testCommaInsteadOfPoint() {
        checkRegex("$ 3,14", "$ 3");
    }}