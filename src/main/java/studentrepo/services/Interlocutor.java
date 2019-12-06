package studentrepo.services;

import java.util.List;
import java.util.Scanner;

/**
 * Makes dialogs with a user to get an appropriate answers
 */
public class Interlocutor {
    private Scanner scanner;

    /**
     * Create an instance of the class
     */
    public Interlocutor(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Makes a dialog with a user to get a string
     *
     * @param objectToAsk a string to make a text-question
     * @return the given string
     */
    public String askString(String objectToAsk) {
        System.out.print("Enter " + objectToAsk.toLowerCase() + " ->");
        return scanner.next();
    }

    /**
     * Makes a dialog with a user to get an appropriate string from a given list
     *
     * @param objectToAsk a string to make a text-question
     * @param fromObjects list to chose
     * @return an appropriate string
     */
    public String askAndCheckString(String objectToAsk, List<String> fromObjects) {
        String objectName;
        boolean isContains;
        do {
            objectName = askString(objectToAsk);
            if (isContains = fromObjects.contains(objectName)) {
                return objectName;
            }
            System.out.println("'" + objectName + "' is wrong. Try again.");
        } while (!isContains);
        return null;
    }

    /**
     * Makes a dialog with a user to get an appropriate int number
     *
     * @param objectToAsk a string to make a text-question
     * @return an appropriate number
     */
    public int askAndCheckInt(String objectToAsk) {
        System.out.println("Enter " + objectToAsk.toLowerCase() + " ->");
        while (!scanner.hasNextInt()) {
            System.out.println("Digits, please!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Makes a dialog with a user to get an appropriate float number
     *
     * @param objectToAsk a string to make a text-question
     * @return an appropriate number
     */
    public float askAndCheckFloat(String objectToAsk) {
        System.out.println("Enter " + objectToAsk.toLowerCase() + " ->");
        while (!scanner.hasNextFloat()) {
            System.out.println("Digits, please!");
            scanner.next();
        }
        return scanner.nextFloat();
    }
}
