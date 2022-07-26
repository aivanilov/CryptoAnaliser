package ru.javarush.cryptoanaliser.ivanilov.view;

import ru.javarush.cryptoanaliser.ivanilov.controllers.MainController;
import ru.javarush.cryptoanaliser.ivanilov.entities.Result;

import java.util.Scanner;

public class SwitcherView implements Runnable {
    boolean isRunning = false;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SwitcherView switcherView = new SwitcherView();
        switcherView.run();
    }

    @Override
    public void run() {
        isRunning = true;
        String[] parameters = {""};

        while (isRunning) {
            parameters = switch (inputCommand()) {
                case 1 -> inputEncryptParameters();
                case 2 -> inputDecryptParameters();
                case 3 -> inputBruteForceParameters();
                case 4 -> inputParametersForAnalysis();
                case 0 -> exitProgram();
                default -> unknownInputParameters();
            };
        }

        MainController mainController = new MainController();
        Result result = mainController.execute(parameters);
        System.out.println(result);
    }

    private String[] exitProgram() {
        isRunning = false;
        return new String[]{null};
    }

    private String[] unknownInputParameters() {
        System.out.println("Unknown command, please, provide correct command");
        isRunning = true;
        return new String[]{null};
        }

    private String[] inputParametersForAnalysis() {
        throw new UnsupportedOperationException();
    }

    private String[] inputBruteForceParameters() {
        String command = "bruteforce";

        System.out.println("""
                Please, provide the following arguments:\s
                1. Path to the encrypted text\s
                2. Path to the decrypted text\s
                """);

        System.out.println("Full path to the encrypted text: ");
        String text = scanner.nextLine();

        System.out.println("Full path to the decrypted text: ");
        String encryptedText = scanner.nextLine();

        isRunning = false;

        return new String[]{command, text, encryptedText};
    }

    private String[] inputDecryptParameters() {
        String command = "encode";

        System.out.println("""
                Please, provide the following arguments:\s
                1. Path to the initial text\s
                2. Path to the encrypted text\s
                3. Offset for cypher\s
                """);

        System.out.println("Full path to the encrypted text: ");
        String text = scanner.nextLine();

        System.out.println("Full path to the decrypted text: ");
        String encryptedText = scanner.nextLine();

        System.out.println("Offset for cypher (any integer): ");
        String offset = scanner.nextLine();

        isRunning = false;

        return new String[]{command, text, encryptedText, offset};
    }

    private int inputCommand() {
        System.out.println("""
                Please, select a command:\s
                1. Encrypt from file to file using key\s
                2. Decrypt from file to file using key\s
                3. Bruteforce from file to file\s
                4. Analyse sample pattern and decrypt text using pattern\s
                0. Exit program""");
        String command = scanner.nextLine();
        return Integer.parseInt(command);
    }

    private String[] inputEncryptParameters() {
        String command = "encode";

        System.out.println("""
                Please, provide the following arguments:\s
                1. Path to the initial text\s
                2. Path to the encrypted text\s
                3. Offset for cypher\s
                """);

        System.out.println("Full path to the initial text: ");
        String text = scanner.nextLine();

        System.out.println("Full path to the encrypted text: ");
        String encryptedText = scanner.nextLine();

        System.out.println("Offset for cypher (any integer): ");
        String offset = scanner.nextLine();

        isRunning = false;

        return new String[]{command, text, encryptedText, offset};
    }
}
