package com.gteam.ide;

/**
 * Created by nagarajan on 18/12/14.
 */
public final class LanguageProcessorFactory {

    public static LanguageProcessor get(String lang) {
        if (lang != null && lang.trim().length() > 0) {
            lang = lang.trim().toLowerCase();
            switch (lang.toLowerCase()) {
                case "java":
                    return new JavaProcessor();

                default:
                    throw new UnsupportedLanguageException();

            }
        }
        throw new IllegalArgumentException("Invalid language Value");

    }
}
