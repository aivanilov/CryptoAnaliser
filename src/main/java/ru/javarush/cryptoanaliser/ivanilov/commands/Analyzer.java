package ru.javarush.cryptoanaliser.ivanilov.commands;

import ru.javarush.cryptoanaliser.ivanilov.entities.Result;
import ru.javarush.cryptoanaliser.ivanilov.entities.ResultCode;
import ru.javarush.cryptoanaliser.ivanilov.utilities.FileOperator;

import java.util.*;

public class Analyzer extends Coder implements Action {

    private String encryptedText;
    private String destination;
    private Map<Character, Double> dictLetterStats;

    @Override
    public Result execute(String[] parameters) {
        extractParameters(parameters);
        String analizedText = analizeText(encryptedText.toLowerCase(), dictLetterStats);
        FileOperator.exportToFile(destination, analizedText);
        return new Result(ResultCode.OK, analizedText);
    }

    void extractParameters(String[] parameters) {
        String dict = FileOperator.getText(parameters[0]);
        encryptedText = FileOperator.getText(parameters[1]).toLowerCase();
        destination = parameters[2];
        dictLetterStats = getLetterStats(dict.toLowerCase());
    }

    private Map<Character, Double> getLetterStats(String text) {
        Map<Character, Integer> letterQuantity = new HashMap<>();
        Map<Character, Double> letterStats = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);

            if (letterQuantity.containsKey(letter)) {
                int quantity = letterQuantity.get(letter);
                letterQuantity.put(letter, quantity + 1);
            } else {
                letterQuantity.put(letter, 0);
            }
        }

        for (var entry : letterQuantity.entrySet()) {
            letterStats.put(entry.getKey(), (double) entry.getValue() / text.length());
        }

        return letterStats;
    }

    private String analizeText(String text, Map<Character, Double> dictLetterStats) {
        Map<Character, Character> letterMapping = new HashMap<>();
        var textLetterStats = getLetterStats(text.toLowerCase());

        List<Map.Entry<Character, Double>> textLetterByOccurance = new ArrayList<>(textLetterStats.entrySet());
        List<Map.Entry<Character, Double>> dictLetterByOccurance = new ArrayList<>(dictLetterStats.entrySet());

        textLetterByOccurance.sort((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()));
        System.out.println(textLetterByOccurance);
        dictLetterByOccurance.sort((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()));
        System.out.println(dictLetterByOccurance);

        for (int i = 0; i < textLetterByOccurance.size() && i < dictLetterByOccurance.size(); i++) {
            letterMapping.put(textLetterByOccurance.get(i).getKey(), dictLetterByOccurance.get(i).getKey());
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            stringBuilder.append(letterMapping.get(letter));
        }

        return stringBuilder.toString();
    }
}