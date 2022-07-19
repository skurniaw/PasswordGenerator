package org.skurniaw;

public class CharSet {

    private final String LOWERCASE_ALPHAS = "abcdefghijklmnopqrstuvwxyz";
    private final String UPPERCASE_ALPHAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String NUMBERS = "0123456789";
    private final String SPECIAL_CHARS = "!@#$%^&*()_+-=,./<>?;':\"[]{}\\";

    private StringBuilder allowedChars;

    public CharSet(boolean hasUpperCase, boolean hasNumbers, boolean hasSpecials) {

        allowedChars = new StringBuilder();
        allowedChars.append(LOWERCASE_ALPHAS);

        if (hasUpperCase) {
            allowedChars.append(UPPERCASE_ALPHAS);
        }
        if (hasNumbers) {
            allowedChars.append(NUMBERS);
        }
        if (hasSpecials) {
            allowedChars.append(SPECIAL_CHARS);
        }

    }

    public String getChars() {
        return allowedChars.toString();
    }
}
