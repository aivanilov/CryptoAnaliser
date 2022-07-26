package ru.javarush.cryptoanaliser.ivanilov.view;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;
import ru.javarush.cryptoanaliser.ivanilov.controllers.MainController;
import ru.javarush.cryptoanaliser.ivanilov.entities.Result;
import ru.javarush.cryptoanaliser.ivanilov.entities.ResultCode;

@Command(name = "CryptoAnaliserPicocliView",
        subcommands = {CommandLine.HelpCommand.class },
        description = "Caesar cypher commands")

public class CryptoAnaliserPicocliView implements Runnable, View {
    @Spec CommandSpec spec;
    @Command(name = "encrypt", description = "Encrypt from file to file using key")
    void encrypt(
            @Parameters(paramLabel = "<source file>", description = "source path with text to encrypt") String source,
            @Parameters(paramLabel = "<destination file>", description = "destination path which should have encrypted text") String destination,
            @Parameters(paramLabel = "<key>", description = "key for encryption") int key) {
        //{"encode", "text.txt", "encryptedText.txt", "55"};
    }

    @Command(name = "decrypt", description = "Decrypt from file to file using key")
    void decrypt(
            @Parameters(paramLabel = "<source path>", description = "source path with encrypted text") String source,
            @Parameters(paramLabel = "<destination path>", description = "destination path which should have decrypted text") String destination,
            @Parameters(paramLabel = "<key>", description = "key for decryption") int key) {
    }

    @Command(name = "bruteforce", description = "Decrypt from file to file using brute force")
    void bruteForce(
            @Parameters(paramLabel = "<source path>", description = "source path with encrypted text") String source,
            @Parameters(paramLabel = "<destination path>", description = "destination path which should have decrypted text") String destination) {
                //{"bruteforce", "encryptedText.txt", "bruteforcedText.txt"};
    }

    @Command(name = "analyze", description = "Decrypt from file to file using statistical analysis")
    void statisticalDecrypt(
            @Parameters(paramLabel = "<dictionary path>", description = "source ") String dictionary,
            @Parameters(paramLabel = "<source path>", description = "source ") String source,
            @Parameters(paramLabel = "<destination path>", description = "destination path which should have decrypted text") String destination){
                //"analyze", "dictionary.txt", "textForAnalysis.txt", "analizedText.txt"};
    }

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Specify a subcommand");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CryptoAnaliserPicocliView()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Result execute(MainController mainController, String[] args) {
        int validateCode = new CommandLine(this).execute(args);
        if (validateCode == 0) {
            return mainController.execute(args);
        }
        return new Result(ResultCode.ERROR, "Incorrect command");
    }
}
