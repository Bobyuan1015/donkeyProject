package yuancom.bob.myapplication.Modules;

/**
 * Created by bobyuan on 07/08/2017.
 */

public class StringJoin {



    private StringJoin() {}

    public static String join(char delim, String... parts) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i != 0) {
                result.append(delim);
            }
            result.append(parts[i]);
        }
        return result.toString();
    }

    public static String join(char delim, UrlValue... parts) {
        String[] strings = new String[parts.length];
        int i = 0;
        for (UrlValue part : parts) {
            strings[i++] = part.toString();
        }

        return join(delim, strings);
    }
}