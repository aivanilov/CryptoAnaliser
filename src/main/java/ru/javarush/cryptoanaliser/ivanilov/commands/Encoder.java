package ru.javarush.cryptoanaliser.ivanilov.commands;

import ru.javarush.cryptoanaliser.ivanilov.constants.Strings;
import ru.javarush.cryptoanaliser.ivanilov.entities.Result;
import ru.javarush.cryptoanaliser.ivanilov.entities.ResultCode;
import ru.javarush.cryptoanaliser.ivanilov.utilities.TextOperator;

public class Encoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        int offset = Integer.parseInt(parameters[0]);
        String text = TextOperator.getText(parameters[1]);
        String encryptedText = encode(text, offset);
        return new Result(ResultCode.OK, encryptedText);
    }

    private String encode(String text, int offset) {
        int normalizedOffset = normalizeOffset(offset);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            stringBuilder.append(encodeChar(symbol, normalizedOffset));
        }

        return stringBuilder.toString();
    }
    private int normalizeOffset(int offset) {
        int normilizedOffset;

        if (Math.abs(offset) <= Strings.ALPHABET.length()) {
            normilizedOffset = offset;
            return normilizedOffset;
        } else {
            int offsetDivider = Math.abs(offset) / Strings.ALPHABET.length();
            if (offset >= 0) {
                normilizedOffset = offset - offsetDivider * Strings.ALPHABET.length();
            } else {
                normilizedOffset = (offset + Strings.ALPHABET.length()) - offsetDivider * Strings.ALPHABET.length();
            }
        }
        return normilizedOffset;
    }

    private char encodeChar(char a, int offset) {
        int result = Strings.ALPHABET.indexOf(String.valueOf(a));

        if ((result + offset) < Strings.ALPHABET.length())
            return Strings.ALPHABET.charAt(result + offset);
        else
            return Strings.ALPHABET.charAt(result - Strings.ALPHABET.length() + offset);
    }
}
