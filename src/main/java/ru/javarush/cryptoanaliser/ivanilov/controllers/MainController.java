package ru.javarush.cryptoanaliser.ivanilov.controllers;

import ru.javarush.cryptoanaliser.ivanilov.commands.Action;
import ru.javarush.cryptoanaliser.ivanilov.commands.Actions;
import ru.javarush.cryptoanaliser.ivanilov.entities.Result;
import ru.javarush.cryptoanaliser.ivanilov.entities.ResultCode;
import ru.javarush.cryptoanaliser.ivanilov.exceptions.ApplicationException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class MainController {
    public Result execute(String[] args) {
        if (args[0] == null)
            return new Result(ResultCode.OK, "Programm closed.");

        try {
            String command = args[0];
            String[] parameters = Arrays.copyOfRange(args, 1, args.length);
            Action action = Actions.find(command);
            return action.execute(parameters);
        } catch (RuntimeException e) {
            return new Result(ResultCode.ERROR, e.getMessage());
        }
    }
}
