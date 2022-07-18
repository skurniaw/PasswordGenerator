package org.skurniaw;

import java.util.Locale;
import java.util.Scanner;

public class PasswordGenerator {

    private Scanner clientInput;
    private boolean isAdvanced;
    private boolean hasUpperCase;
    private boolean hasNumbers;
    private boolean hasSpecials;
    private int passwordLength;
    private int numOfPasswords;

    public PasswordGenerator (Scanner scanner) {
        this.clientInput = scanner;
    }

    public void getInput() {
        System.out.print("Would you a [S]imple or [A]dvanced password? ");
        isAdvanced = clientInput.nextLine().toLowerCase().equals("a");

        System.out.print("Would you like your password to include uppercase letters [Y / N]? ");
        hasUpperCase = clientInput.nextLine().toLowerCase().equals("y");

        System.out.print("Would you like your password to include numbers [Y / N]? ");
        hasNumbers = clientInput.nextLine().toLowerCase().equals("y");

        System.out.print("Would you like your password to include special characters [Y / N]? ");
        hasSpecials = clientInput.nextLine().toLowerCase().equals("y");

        System.out.print("How many characters would you like the password to be? Input a number: ");
        passwordLength = Integer.parseInt(clientInput.nextLine());

        System.out.print("How many passwords would you like generated? Input a number: ");
        numOfPasswords = Integer.parseInt(clientInput.nextLine());

    }

    public void printOptions() {
        System.out.println("isAdvanced: " + isAdvanced);
        System.out.println("hasUppercase: " + hasUpperCase);
        System.out.println("hasNumbers: " + hasNumbers);
        System.out.println("hasSpecials: " + hasSpecials);
        System.out.println("pass length: " + passwordLength);
        System.out.println("desired # of pws: " + numOfPasswords);
    }

}
