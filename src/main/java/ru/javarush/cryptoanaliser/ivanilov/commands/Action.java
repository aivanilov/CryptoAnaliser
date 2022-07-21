package ru.javarush.cryptoanaliser.ivanilov.commands;

import ru.javarush.cryptoanaliser.ivanilov.entities.Result;

public interface Action {
    Result execute(String[] parameters);
}
