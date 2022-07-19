package org.skurniaw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Greeter {

    private Scanner clientInput;


    public Greeter() {
        this.clientInput = new Scanner(System.in);
    }

    public void loop() {
        printWelcome();

        String optionChosen = "-1";

        while (!optionChosen.equals("3")) {

            printMenu();

            optionChosen = clientInput.nextLine();

            if (optionChosen.equals("1")) {
                buildPassword();
            } else if (optionChosen.equals("2")) {
                // go to password checker.
            } else if (optionChosen.equals("3")){
                System.out.println("Closing out the program...");
            } else { // it's none of them
                System.out.println(" Please choose a valid option!");
            }

        }

        System.out.println("*** PROGRAM EXITED ***");

    }

    private void printWelcome() {
        System.out.println(
                " Welcome to skurniaw's Password Generator!" +
                "\n Here you can generate a simple or advanced password." +
                "\n For a simple password, multiple words from the English language will be strung together. " +
                "\n For an advanced password, you will select what type of characters you'd like in" +
                "\n your password, as well as the desired password length." +
                "\n You can also choose to test a strength of a password!" +
                "\n Let's get started! \n "
        );
    }

    private void printMenu() {
        System.out.println(
            " Please choose an option by inputting a number: " +
            "\n 1 - Generate password(s)" +
            "\n 2 - Check the strength of a password" +
            "\n 3 - Exit the program"
        );
    }

    private void buildPassword() {

        boolean isAdvanced = yesNoQuestion("Would you like to generate an advanced password [Y / N]? ");

        if (isAdvanced) {
            System.out.println("You've chosen to generate a complex password. You'll have to answer some additional \n" +
                    "questions.");
            boolean hasUpperCase = yesNoQuestion("Would you like your password to include uppercase letters [Y / N]? ");
            boolean hasNumbers = yesNoQuestion("Would you like your password to include numbers [Y / N]? ");
            boolean hasSpecials = yesNoQuestion("Would you like your password to include special characters [Y / N]? ");
            int passwordLength = numberQuestion("How long would you like the password to be? Enter a number: ");
            int numOfPasswords = numberQuestion("How many passwords would you like generated? Enter a number: ");
            Generator complexGen = new Generator(hasUpperCase, hasNumbers, hasSpecials, passwordLength, numOfPasswords);
        } else {
            System.out.println("You've chosen to generate a simple password of English words strung together.");
            boolean useCommonWords = yesNoQuestion("Would you like your password to use common English words?");
            int numOfWords = numberQuestion("How many words would you like in your password?");
            int numOfPasswords = numberQuestion("How many passwords would you like generated? Enter a number: ");

        }

    }

    private boolean yesNoQuestion(String q) {
        boolean validInput = false;
        String errorMsg = "Something went wrong... Please input a valid answer (Y or N).";

        String ans = "";
        System.out.print(q);

        while (!validInput) {
            ans = clientInput.nextLine().toLowerCase();

            if (ans.equals("y") || ans.equals("n")) {
                validInput = true;
            } else {
                System.out.println(errorMsg + "\n");
            }
        }

        return ans.equals("y");
    }

    private int numberQuestion(String q) {
        boolean validInput = false;
        String errorMsg = "Something went wrong... please input a valid number greater than 0.";

        int ans = -1;

        while (!validInput) {
            System.out.print(q);
            try {
                ans = clientInput.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(errorMsg);
                clientInput.next();
            } finally {
                if (ans > 0) {
                    validInput = true;
                }
            }
        }

        return ans;

    }

}
