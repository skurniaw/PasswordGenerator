package org.skurniaw;

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

    public Generator(boolean useCommonWords, int numOfWords, int numOfPasswords) {
        this.useCommonWords = useCommonWords;
        this.numOfWords = numOfWords;
        this.numOfPasswords = numOfPasswords;
        generateEZPass();
    }

    public Generator(boolean hasUpperCase, boolean hasNumbers, boolean hasSpecials, int pwLength, int numOfPasswords) {
        this.hasUpperCase = hasUpperCase;
        this.hasNumbers = hasNumbers;
        this.hasSpecials = hasSpecials;
        this.passwordLength = pwLength;
        this.numOfPasswords = numOfPasswords;
        generateComplexPass();
    }

    private void generateEZPass() {
        List<String> wordList = new WordSet(useCommonWords).getWordSet();

        String answer = "";

        for (int i = 0; i < numOfPasswords; i++) {
            String pass = "";

            for (int j = 0; j < numOfWords; j++) {
                pass += wordList.get((int) (Math.random() * wordList.size())) + "_";
            }
            pass = pass.substring(0, pass.length() - 1);
            answer += "Password #" + (i + 1) + ": " + pass + "\n";
        }
        System.out.println(answer);
    }

    private void generateComplexPass() {
        String charSet = new CharSet(hasUpperCase, hasNumbers, hasSpecials).getChars();

        String answer = "";

        for (int i = 0; i < numOfPasswords; i++) {
            String pass = "";

            for (int j = 0; j < passwordLength; j++) {
                pass += charSet.charAt((int) (Math.random() * charSet.length()));
            }

            answer += "Password #" + (i + 1) + ": " + pass + "\n";
        }
        System.out.println(answer);
    }

}
