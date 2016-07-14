package saml;

/**
 * author: saml
 */
public class Env {
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
