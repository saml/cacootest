package saml;

/**
 * author: saml
 */
public class Env {
    public static String asString(String key, String defaultValue) {
        final String value = System.getenv(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public static int asInt(String key, int defaultValue) {
        final String value = System.getenv(key);
        try {
            return Integer.parseInt(value, 10);
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        }
        return defaultValue;
    }
}
