package ru.javarush.cryptoanaliser.ivanilov.commands;

import ru.javarush.cryptoanaliser.ivanilov.entities.Result;
import ru.javarush.cryptoanaliser.ivanilov.entities.ResultCode;
import ru.javarush.cryptoanaliser.ivanilov.utilities.FileOperator;

import java.util.*;

public class Analyzer extends Coder implements Action {

    private String text;
    private String destination;
    private TreeMap<Character, Double> dictionaryLetterOccurance;

    @Override
    public Result execute(String[] parameters) {
        extractParameters(parameters);
        String analizedText = analizeText(text.toLowerCase(), dictionaryLetterOccurance);
        FileOperator.exportToFile(destination, analizedText);
        return new Result(ResultCode.OK, analizedText);
    }

    void extractParameters(String[] parameters) {
        String dictionary = FileOperator.getText(parameters[0]);
        text = FileOperator.getText(parameters[1]).toLowerCase();
        destination = parameters[2];
        dictionaryLetterOccurance = getLetterOccuranceFrequency(dictionary.toLowerCase());
    }

    private TreeMap<Character, Double> getLetterOccuranceFrequency(String text) {
        Map<Character, Integer> letterOccuranceQuantity = new HashMap<>();
        TreeMap<Character, Double> letterOccuranceFrequency = new TreeMap<>();

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);

            if (letterOccuranceQuantity.containsKey(letter)) {
                int quantity = letterOccuranceQuantity.get(letter);
                letterOccuranceQuantity.put(letter, quantity + 1);
            } else {
                letterOccuranceQuantity.put(letter, 0);
            }
        }

        for (var entry : letterOccuranceQuantity.entrySet()) {
            letterOccuranceFrequency.put(entry.getKey(), (double) entry.getValue() / text.length());
        }

        return letterOccuranceFrequency;
    }

    private String analizeText(String text, TreeMap<Character, Double> dictionaryLetterOccurance) {
        var textLetterOccurance = getLetterOccuranceFrequency(text.toLowerCase());
        TreeMap<Double, Character> textLetterOccuranceByDouble = swapKeysAndValuesInTreeMap(textLetterOccurance);
        TreeMap<Double, Character> dictionaryLetterOccuranceByDouble = swapKeysAndValuesInTreeMap(dictionaryLetterOccurance);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            double letterOccurance = textLetterOccurance.get(letter);
            char decryptedLetter = findClosestKeyInDictionaryMap(letterOccurance, dictionaryLetterOccuranceByDouble);
            stringBuilder.append(decryptedLetter);
        }

        return stringBuilder.toString();
    }

    private char findClosestKeyInDictionaryMap(double letterOccurance, TreeMap<Double, Character> dictionaryLetterOccuranceByDouble) {
        boolean hasCeiling = dictionaryLetterOccuranceByDouble.ceilingKey(letterOccurance) != null;
        boolean hasFloor = dictionaryLetterOccuranceByDouble.floorKey(letterOccurance) != null;

        if (hasCeiling && hasFloor)  {
            double ceiling = dictionaryLetterOccuranceByDouble.ceilingKey(letterOccurance);
            double floor = dictionaryLetterOccuranceByDouble.floorKey(letterOccurance);
            if ((ceiling - letterOccurance) < (floor - letterOccurance))
                return dictionaryLetterOccuranceByDouble.get(ceiling);
            else
                return dictionaryLetterOccuranceByDouble.get(floor);
        }

        if (hasCeiling) {
            double ceiling = dictionaryLetterOccuranceByDouble.ceilingKey(letterOccurance);
            return dictionaryLetterOccuranceByDouble.get(ceiling);
        }

        if (hasFloor) {
            double floor = dictionaryLetterOccuranceByDouble.floorKey(letterOccurance);
            return dictionaryLetterOccuranceByDouble.get(floor);
        }

        return 0;
    }

    private TreeMap<Double, Character> swapKeysAndValuesInTreeMap(TreeMap<Character, Double> treeMap) {
        TreeMap<Double, Character> swappedMap = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o2, o1);
            }
        });

        for (var entry : treeMap.entrySet()) {
            swappedMap.put(entry.getValue(), entry.getKey());
        }

        return swappedMap;
    }
}