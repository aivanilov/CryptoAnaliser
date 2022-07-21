package ru.javarush.cryptoanaliser.ivanilov.commands;

import ru.javarush.cryptoanaliser.ivanilov.exceptions.ApplicationException;

public enum Actions {
    // лучше сделать map (имя команды, сама команда)
    // можно сделать контейнер, собирающий все команды

    ENCODE (new Encoder()), DECODE(new Decoder());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        try {
            Action action = Actions.valueOf(command.toUpperCase()).action;
            return action;
        } catch (RuntimeException e) {
            throw new ApplicationException("The command has not been found");
        }
    }
}
