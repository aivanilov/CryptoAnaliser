package ru.javarush.cryptoanaliser.ivanilov.controllers;

import ru.javarush.cryptoanaliser.ivanilov.commands.Action;
import ru.javarush.cryptoanaliser.ivanilov.commands.Actions;
import ru.javarush.cryptoanaliser.ivanilov.entities.Result;

public class MainController {
    public Result execute(String command, String[] parameters) {
        Action action = Actions.find(command);
        Result result = action.execute(parameters);
        return result;
    }
}
