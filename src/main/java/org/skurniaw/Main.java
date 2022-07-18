package org.skurniaw;

import java.util.Scanner;

public class Main {

    public static final Scanner clientInput = new Scanner(System.in);

    public static void main(String[] args) {
        Greeter greeter = new Greeter(clientInput);
        greeter.loop();


    }

}