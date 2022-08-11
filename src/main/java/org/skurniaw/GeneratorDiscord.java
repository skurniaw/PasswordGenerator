package org.skurniaw;

import java.util.List;

public class GeneratorDiscord {

    private boolean hasUpperCase;
    private boolean hasNumbers;
    private boolean hasSpecials;
    private boolean useCommonWords;
    private int numOfWords;
    private int passwordLength;
    private int numOfPasswords;

    private String answer;

    public GeneratorDiscord(boolean useCommonWords, int numOfWords, int numOfPasswords) {
        this.useCommonWords = useCommonWords;
        this.numOfWords = numOfWords;
        this.numOfPasswords = numOfPasswords;
        this.answer = "";
        generateEZPass();
    }

    public GeneratorDiscord(boolean hasUpperCase, boolean hasNumbers, boolean hasSpecials, int pwLength, int numOfPasswords) {
        this.hasUpperCase = hasUpperCase;
        this.hasNumbers = hasNumbers;
        this.hasSpecials = hasSpecials;
        this.passwordLength = pwLength;
        this.numOfPasswords = numOfPasswords;
        this.answer = "";
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
        this.answer = answer;
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
        this.answer = answer;
    }

    public String toString() {
        return
                "*** PASSWORDS *** \n \n" +
                this.answer +
                "\n *** PASSWORDS *** \n \n";
    }

}
