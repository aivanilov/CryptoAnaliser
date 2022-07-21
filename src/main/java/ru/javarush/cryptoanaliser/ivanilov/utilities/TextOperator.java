package ru.javarush.cryptoanaliser.ivanilov.utilities;

import ru.javarush.cryptoanaliser.ivanilov.constants.Strings;
import ru.javarush.cryptoanaliser.ivanilov.exceptions.ApplicationException;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TextOperator {
    public static String getText(String file) {
        Path path = Path.of(file);
        try {
            return Files.readString(path);
        } catch (Exception e) {
            throw new ApplicationException(String.format("Couldn't read the file %s", path));
        }
    }
}
