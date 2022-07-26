package ru.javarush.cryptoanaliser.ivanilov.constants;

import java.util.Locale;

public class Strings {
    private static final String RUSSIAN_ALPHABET = "йцукенгшщзхъфывапролджэёячсмитьбю";
    private static final String NUMBERS = "1234567890";
    private static final String SYMBOLS = " ,./\\'@\"!?:;_=-+[]{}()";
    public static final String ALPHABET = (RUSSIAN_ALPHABET + NUMBERS + SYMBOLS).toLowerCase(Locale.ROOT);
}
