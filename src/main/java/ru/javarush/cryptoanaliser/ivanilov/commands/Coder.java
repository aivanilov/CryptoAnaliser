package ru.javarush.cryptoanaliser.ivanilov.commands;

import ru.javarush.cryptoanaliser.ivanilov.constants.Strings;

import java.util.Locale;

public abstract class Coder {

    abstract void extractParameters(String[] parameters);

    int normalizeOffset(int offset) {
        int normilizedOffset;

        if (offset < 0)
            while (offset < 0) {
                offset = offset + Strings.ALPHABET.length();
            }

        if (offset <= Strings.ALPHABET.length()) {
            normilizedOffset = offset;
        } else {
            int offsetDivider = Math.abs(offset) / Strings.ALPHABET.length();
            normilizedOffset = offset - offsetDivider * Strings.ALPHABET.length();
        }

        return normilizedOffset;
    }

    char encodeChar(char a, int offset) {
        String letter = String.valueOf(a).toLowerCase(Locale.ROOT);
        int position = Strings.ALPHABET.indexOf(letter);

        if (position == -1)
            return a;

        if ((position + offset) < Strings.ALPHABET.length())
            return Strings.ALPHABET.charAt(position + offset);
        else
            return Strings.ALPHABET.charAt(position - Strings.ALPHABET.length() + offset);
    }
}
