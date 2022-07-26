package ru.javarush.cryptoanaliser.ivanilov.commands;

import ru.javarush.cryptoanaliser.ivanilov.entities.Result;
import ru.javarush.cryptoanaliser.ivanilov.entities.ResultCode;
import ru.javarush.cryptoanaliser.ivanilov.exceptions.ApplicationException;
import ru.javarush.cryptoanaliser.ivanilov.utilities.FileOperator;

public class Encoder extends Coder implements Action {

    private String text;
    private String destination;
    private int offset;

    @Override
    public Result execute(String[] parameters) {
        extractParameters(parameters);
        String encryptedText = encode(text, offset);
        FileOperator.exportToFile(destination, encryptedText);
        return new Result(ResultCode.OK, encryptedText);
    }

    void extractParameters(String[] parameters) {
        text = FileOperator.getText(parameters[0]);
        destination = parameters[1];
        offset = Integer.parseInt(parameters[2]);
    }

    String encode(String text, int offset) {
        int normalizedOffset = normalizeOffset(offset);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            stringBuilder.append(encodeChar(symbol, normalizedOffset));
        }
        return stringBuilder.toString();
    }
}
