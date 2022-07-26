package ru.javarush.cryptoanaliser.ivanilov.utilities;

import ru.javarush.cryptoanaliser.ivanilov.exceptions.ApplicationException;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileOperator {
    public static String getText(String file) {
        Path path = Path.of(file);
        try {
            return Files.readString(path);
        } catch (Exception e) {
            throw new ApplicationException(String.format("Couldn't read the file %s", path));
        }
    }

    public static void exportToFile(String destination, String text) {
        Path path = Path.of(destination);
        try {
            Files.write(path, text.getBytes());
        } catch (Exception e) {
            throw new ApplicationException(String.format("Couldn't write to the file %s", path), e);
        }
    }
}
