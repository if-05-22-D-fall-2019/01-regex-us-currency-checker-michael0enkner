import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsCurrencyChecker {
    public static Matcher getMatcher(String usCurrencyString) {
        final String regex = "\\$\\s?\\d+(\\.\\d{1,2})?";
        final String checkString = usCurrencyString;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(checkString);

        return matcher;
    }
}
