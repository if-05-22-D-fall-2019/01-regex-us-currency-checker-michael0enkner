import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(matcher.find());
        assertEquals(expectedMatch, matcher.group());
        assertFalse(matcher.find());
    }

    @Test
    public void testNoCurrency() {
        Matcher matcher = UsCurrencyChecker.getMatcher("This is text without any currency information");
        assertFalse(matcher.find());
    }

    @Test
    public void testNoBlankNoZero() {
        checkRegex("$149.5");
    }

    @Test
    public void testOnlyBlankBetween$AndNumber() {
        Matcher matcher = UsCurrencyChecker.getMatcher("$x14.5");
        assertFalse(matcher.find());
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