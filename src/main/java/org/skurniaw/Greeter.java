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
                printTips();
            } else if (optionChosen.equals("3")){
                System.out.println("Closing out the program...");
                clientInput.close();
            } else { // it's none of them
                System.out.println(" Please choose a valid option!");
            }
        }

        System.out.println("*** PROGRAM EXITED ***");
    }

    private void printWelcome() {
        System.out.println("""
            Welcome to skurniaw's Password Generator!
            Here you can generate a simple or advanced password.
            For a simple password, multiple words from the English language will be strung together.
            For an advanced password, you will select what type of characters you'd like in
            your password, as well as the desired password length.
            Let's get started!""");
    }

    private void printMenu() {
        System.out.print("""
            Please choose an option by inputting a number: 
            1 - Generate password(s)
            2 - View tips to build a strong password
            3 - Exit the program
            
            Enter a choice: """ + " ");
    }

    private void printTips() {
        System.out.print("""
            TIPS FOR CREATING A STRONG PASSWORD (select tips from security.org):
            * Password should be 16 characters or more
            * Password should contain a combination of letters, numbers, and special characters
            * Password should NOT contain any of the user's personal information (like address or phone number)
            * Password should NOT contain any consecutive letters or numbers.
            * Password should NOT contain the word 'password' or a single character or number repeated.
            
            Hit ENTER to return to the main menu. """ + " ");

        clientInput.nextLine();
    }

    private void buildPassword() {

        boolean isAdvanced = yesNoQuestion("Would you like to generate a complex password [Y / N]? ");

        if (isAdvanced) {
            System.out.println("You've chosen to generate a complex password. You'll have to answer some additional \n" +
                    "questions.");
            boolean hasUpperCase = yesNoQuestion("Would you like your password to include uppercase letters [Y / N]? ");
            boolean hasNumbers = yesNoQuestion("Would you like your password to include numbers [Y / N]? ");
            boolean hasSpecials = yesNoQuestion("Would you like your password to include special characters [Y / N]? ");
            int passwordLength = numberQuestion("How long would you like the password to be? Enter a number of characters: ");
            int numOfPasswords = numberQuestion("How many passwords would you like generated? Enter a number: ");
            Generator complexGen = new Generator(hasUpperCase, hasNumbers, hasSpecials, passwordLength, numOfPasswords);
        } else {
            System.out.println("You've chosen to generate a simple password of English words strung together.");
            boolean useCommonWords = yesNoQuestion("Would you like your password to use only common English words? ");
            int numOfWords = numberQuestion("How many words would you like in your password? ");
            int numOfPasswords = numberQuestion("How many passwords would you like generated? Enter a number: ");
            Generator simpleGen = new Generator(useCommonWords, numOfWords, numOfPasswords);
        }
    }

    private boolean yesNoQuestion(String question) {
        boolean validInput = false;
        String errorMsg = "Something went wrong... Please input a valid answer (Y or N).";
        String ans = "";

        while (!validInput) {
            System.out.print(question);
            ans = clientInput.nextLine().toLowerCase();

            if (ans.equals("y") || ans.equals("n")) {
                validInput = true;
            } else {
                System.out.println(errorMsg);
            }

        }

        return ans.equals("y");
    }

    private int numberQuestion(String question) {
        boolean validInput = false;
        String errorMsg = "Something went wrong... please input a valid number greater than 0.";
        int ans = -1;

        while (!validInput) {
            System.out.print(question);
            try {
                ans = clientInput.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(errorMsg);
                clientInput.next();
            } finally {
                if (ans > 0) {
                    validInput = true;
                    clientInput.nextLine();
                }
            }
        }

        return ans;
    }

}
