import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsCurrencyChecker {
    public static Matcher getMatcher(String usCurrencyString) {
        return Pattern.compile("dljf").matcher(usCurrencyString);
    }
}
