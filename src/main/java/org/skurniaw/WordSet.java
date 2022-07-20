package org.skurniaw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class WordSet {

    private boolean useCommonWords;
    private Scanner fileScan;
    private List<String> wordList;

    public WordSet(boolean useCommonWords) {
        this.useCommonWords = useCommonWords;
        this.wordList = new ArrayList<>();
        build();
    }

    private void build() {
        boolean isValid = true;

        try {
            if (!useCommonWords) {
                fileScan = new Scanner(new File("words_alpha.txt"));
            } else {
                fileScan = new Scanner(new File("common_words_alpha.txt"));
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception.toString());
            isValid = false;
        }

        if (isValid) {
            while (fileScan.hasNextLine()) {
                wordList.add(fileScan.nextLine());
            }
        }

    }

    public List<String> getWordSet() {
        return wordList;
    }

}