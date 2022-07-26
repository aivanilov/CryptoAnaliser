package ru.javarush.cryptoanaliser.ivanilov.commands;

import ru.javarush.cryptoanaliser.ivanilov.exceptions.ApplicationException;
public enum Actions {

    ENCODE (new Encoder()),
    BRUTEFORCE (new Bruteforcer()),
    ANALYZE (new Analyzer());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        try {
            return Actions.valueOf(command.toUpperCase()).action;
        } catch (RuntimeException e) {
            throw new ApplicationException("The command has not been found");
        }
    }
}
