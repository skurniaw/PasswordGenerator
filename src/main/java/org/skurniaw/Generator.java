package org.skurniaw;

import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.List;

public class Generator {

    private boolean hasUpperCase;
    private boolean hasNumbers;
    private boolean hasSpecials;
    private boolean useCommonWords;
    private int numOfWords;
    private int passwordLength;
    private int numOfPasswords;

    public Generator(boolean hasUpperCase, boolean hasNumbers, boolean hasSpecials, int pwLength, int numOfPasswords) {
        this.hasUpperCase = hasUpperCase;
        this.hasNumbers = hasNumbers;
        this.hasSpecials = hasSpecials;
        this.passwordLength = pwLength;
        this.numOfPasswords = numOfPasswords;
        buildComplexPass();
    }

    public Generator(boolean useCommonWords, int numOfWords, int numOfPasswords) {
        this.useCommonWords = useCommonWords;
        this.numOfWords = numOfWords;
        this.numOfPasswords = numOfPasswords;
        buildEZPass();
    }

    private void buildEZPass() {
        List<String> wordList = new WordSet(useCommonWords).getWordSet();
        for (int i = 0; i < numOfPasswords; i++) {
            String pass = "";
            for (int j = 0; j < numOfWords; j++) {
                pass += wordList.get((int) (Math.random() * wordList.size())) + "_";
            }

            pass = pass.substring(0, pass.length() - 1);

            System.out.println("Password #" + (i + 1) + ": " + pass);
        }
    }

    private void buildComplexPass() {
        String charSet = new CharSet(hasUpperCase, hasNumbers, hasSpecials).getChars();

        for (int i = 0; i < numOfPasswords; i++) {
            String pass = "";
            for (int j = 0; j < passwordLength; j++) {
                pass += charSet.charAt((int) (Math.random() * charSet.length()));
            }
            System.out.println("Password #" + (i + 1) + ": " + pass);
        }

    }

}
