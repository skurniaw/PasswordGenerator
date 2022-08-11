package org.skurniaw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GreeterDiscord {

    private State state;
    private String response;
    private boolean isAdvanced;
    private int numOfQs;
    private int qNum;

    private Queue<String> ansQueue;

    private final List<String> complexQuestionList = new ArrayList<>() {
        {
            add("Would you like your password to include uppercase letters [Y / N]? ");
            add("Would you like your password to include numbers [Y / N]? ");
            add("Would you like your password to include special characters [Y / N]? ");
            add("How long would you like the password to be? Enter a number of characters: ");
            add("How many passwords would you like generated? Enter a number: ");
        }
    };

    private final List<String> simpleQuestionList = new ArrayList<>() {
        {
            add("Would you like your password to use only common English words? ");
            add("How many words would you like in your password? ");
            add("How many passwords would you like generated? Enter a number: ");
        }
    };

    public GreeterDiscord() {
        this.ansQueue = new LinkedList<>();
        response = welcomeString() + "\n" + menuString();
        this.state = State.MAIN_MENU;
    }

    public void takeCommand(String msg) {
        switch (state) {
            case MAIN_MENU -> {
                switch (msg) {
                    case "1" -> {
                        state = State.ASK_ADVANCED;
                        response = "Would you like to generate a complex password [Y / N]? ";
                    }
                    case "2" -> {
                        response = tipsString() + "\n" + menuString();
                        state = State.MAIN_MENU;
                    }
                    case "3" -> state = State.CLOSE;
                    default -> response = "Something went wrong... please try again.";
                }
            }
            case ASK_ADVANCED -> {
                if (checkYesNo(msg)) {
                    isAdvanced = msg.equals("y");
                    numOfQs = isAdvanced ? complexQuestionList.size() : simpleQuestionList.size();
                    qNum = 0;
                    state = State.ASK_QUESTIONS;
                    response = isAdvanced ? complexQuestionList.get(qNum) : simpleQuestionList.get(qNum);
                } else {
                    response = "Something went wrong... Please input a valid answer (Y or N). \n \n" + response;
                }
            }
            case ASK_QUESTIONS -> {
                // check if input is good
                if (isAdvanced) {
                    if (qNum < 3 && checkYesNo(msg)) {
                        ansQueue.add(msg);
                        qNum++;
                    } else if (checkValidInteger(msg)) {
                        ansQueue.add(msg);
                        qNum++;
                    }
                } else {
                    if (qNum < 1 && checkYesNo(msg)) {
                        ansQueue.add(msg);
                        qNum++;
                    } else if (checkValidInteger(msg)) {
                        ansQueue.add(msg);
                        qNum++;
                    }
                }

                if (qNum < numOfQs) {
                    response = isAdvanced ? complexQuestionList.get(qNum) : simpleQuestionList.get(qNum);
                } else {
                    buildPass();
                    state = State.MAIN_MENU;
                }
            }
        }
    }

    public String getResponse() {
        return
            "``` \n" + response +
            "\n ```";
    }

    private String welcomeString() {
        return """
            Welcome to skurniaw's Password Generator!
            Here you can generate a simple or advanced password.
            For a simple password, multiple words from the English language will be strung together.
            For an advanced password, you will select what type of characters you'd like in
            your password, as well as the desired password length.
            Let's get started! \n""";
    }

    private String menuString() {
        return """
            Please choose an option by inputting a number:
            1 - Generate password(s)
            2 - View tips to build a strong password
            3 - Exit the program
            
            Enter a choice:""";
    }

    private String tipsString() {
        return """
            TIPS FOR CREATING A STRONG PASSWORD (select tips from security.org):
            * Password should be 16 characters or more
            * Password should contain a combination of letters, numbers, and special characters
            * Password should NOT contain any of the user's personal information (like address or phone number)
            * Password should NOT contain any consecutive letters or numbers.
            * Password should NOT contain the word 'password' or a single character or number repeated.
            """;
    }

    private boolean checkYesNo(String s) {
        return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n");
    }

    private boolean checkValidInteger(String s) {
        boolean isValid = true;
        try {
            if (Integer.parseInt(s) < 0) {
                isValid = false;
            }
        } catch (NumberFormatException e) {
            isValid = false;
            System.err.println("Couldn't parse String as int: " + e.toString());
        }

        return isValid;
    }

    private void buildPass() {
        if (isAdvanced) {
            boolean hasUpperCase = ansQueue.poll().equals("y");
            boolean hasNumbers = ansQueue.poll().equals("y");
            boolean hasSpecials = ansQueue.poll().equals("y");
            int passLength = Integer.parseInt(ansQueue.poll());
            int numOfPasswords = Integer.parseInt(ansQueue.poll());
            response = new GeneratorDiscord(hasUpperCase, hasNumbers, hasSpecials, passLength, numOfPasswords).toString();
        } else {
            boolean useCommonWords = ansQueue.poll().equals("y");
            int numOfWords = Integer.parseInt(ansQueue.poll());
            int numOfPasswords = Integer.parseInt(ansQueue.poll());
            response = new GeneratorDiscord(useCommonWords, numOfWords, numOfPasswords).toString();
        }

        response += menuString();
    }

}
