package ru.javarush.cryptoanaliser.ivanilov.app;

import ru.javarush.cryptoanaliser.ivanilov.controller.MainController;

import java.util.Arrays;

public class Application {
    private final MainController mainController;

    public Application(MainController mainController) {
        this.mainController = mainController;
    }

    public Result run(String[] args) {
        String command = args[0];
        String[] parameters = Arrays.copyOfRange(args,1, args.length); //лучше сделать мапу ключ-значение
        return mainController.execute(command, parameters);
    }
}
