package ru.javarush.cryptoanaliser.ivanilov.view;

import ru.javarush.cryptoanaliser.ivanilov.constants.Strings;
import ru.javarush.cryptoanaliser.ivanilov.toplevel.RequestHandler;
import ru.javarush.cryptoanaliser.ivanilov.entities.Result;
import ru.javarush.cryptoanaliser.ivanilov.controllers.MainController;
import ru.javarush.cryptoanaliser.ivanilov.utilities.PathFinder;

public class Runner {
    public static void main(String[] args) {
        args = new String[]{"encode", "5", PathFinder.getRoot() + "text.txt", PathFinder.getRoot() + "text_encrypted.txt"};
        MainController mainController = new MainController();
        RequestHandler requestHandler = new RequestHandler(mainController);
        Result result = requestHandler.run(args);
        System.out.println(result);
    }
}
