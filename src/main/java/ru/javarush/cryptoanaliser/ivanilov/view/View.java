package ru.javarush.cryptoanaliser.ivanilov.view;

import ru.javarush.cryptoanaliser.ivanilov.controllers.MainController;
import ru.javarush.cryptoanaliser.ivanilov.entities.Result;

public interface View {
    Result execute(MainController mainController, String[] args);
}
