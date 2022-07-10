package ru.javarush.cryptoanaliser.ivanilov;

import ru.javarush.cryptoanaliser.ivanilov.app.Application;
import ru.javarush.cryptoanaliser.ivanilov.app.Result;
import ru.javarush.cryptoanaliser.ivanilov.controller.MainController;

public class Runner {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        Application application = new Application(mainController);
        Result result = application.run(args);
        System.out.println(result);
    }
}
