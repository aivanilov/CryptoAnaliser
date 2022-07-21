package ru.javarush.cryptoanaliser.ivanilov.toplevel;

import ru.javarush.cryptoanaliser.ivanilov.controllers.MainController;
import ru.javarush.cryptoanaliser.ivanilov.entities.Result;

import java.util.Arrays;

public class RequestHandler {
    private final MainController mainController;

    public RequestHandler(MainController mainController) {
        this.mainController = mainController;
    }

    public Result run(String[] args) {
        String command = args[0];
        String[] parameters = Arrays.copyOfRange(args,1, args.length);
        return mainController.execute(command, parameters);
    }
}
