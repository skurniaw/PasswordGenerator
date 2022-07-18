package org.skurniaw;

import java.util.Scanner;

public class Greeter {

    private Scanner clientInput;


    public Greeter(Scanner scanner) {
        this.clientInput = scanner;
    }

    public void loop() {
        printWelcome();

        String optionChosen = "-1";

        while (!optionChosen.equals("3")) {

            printMenu();

            optionChosen = clientInput.nextLine();

            if (optionChosen.equals("1")) {
                PasswordGenerator gen = new PasswordGenerator(clientInput);
                gen.getInput();
                gen.printOptions();
            } else if (optionChosen.equals("2")) {
                System.out.println("Go to pass checker");
            } else if (optionChosen.equals("3")){
                System.out.println("Close out the program");
            } else { // it's none of them
                System.out.println("Please choose a valid option!");
            }

        }

        System.out.println("PROGRAM EXITED");

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

}
