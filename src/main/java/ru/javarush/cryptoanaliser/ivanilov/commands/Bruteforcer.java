package ru.javarush.cryptoanaliser.ivanilov.commands;

import ru.javarush.cryptoanaliser.ivanilov.constants.Strings;
import ru.javarush.cryptoanaliser.ivanilov.entities.Result;
import ru.javarush.cryptoanaliser.ivanilov.entities.ResultCode;
import ru.javarush.cryptoanaliser.ivanilov.utilities.FileOperator;

public class Bruteforcer extends Encoder implements Action {

    private String bruteforcedText;

    @Override
    public Result execute(String[] parameters) {
        String text = FileOperator.getText(parameters[0]);
        String destination = parameters[1];
        String result = null;

        for (int i = 0; i < Strings.ALPHABET.length(); i++) {
            bruteforcedText = encode(text, i);
            double spacePattern = determineSpacePattern(bruteforcedText);
            if (isValidText(spacePattern)) {
                result = bruteforcedText;
                break;
            }
        }

        if (result != null) {
            FileOperator.exportToFile(destination, result);
            return new Result(ResultCode.OK, result);
        }
        else
            return new Result(ResultCode.ERROR, "Valid crypto key hasn't been found");
    }

    private boolean isValidText(double spacePattern) {
        double minShareOfSpaces = 0.09;
        double maxShareOfSpaces = 0.25;
        boolean commaPattern = determineCommaPattern();
        return spacePattern > minShareOfSpaces && spacePattern < maxShareOfSpaces && commaPattern;
    }

    private boolean determineCommaPattern() {
        for (int i = 0; i < bruteforcedText.length() - 1; i++) {
            char couldBeComma = bruteforcedText.charAt(i);
            char shouldBeSpace = bruteforcedText.charAt(i+1);
            if (couldBeComma == ',' && shouldBeSpace != ' ')
                return false;
        }
        return true;
    }

    private double determineSpacePattern(String text) {
        int numberOfSpaces = 0;

        for (char a : text.toCharArray()) {
            if (a == ' ') numberOfSpaces++;
        }

        return (double) numberOfSpaces / text.length();
    }
}
