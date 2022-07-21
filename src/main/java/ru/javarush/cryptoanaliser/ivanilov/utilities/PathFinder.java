package ru.javarush.cryptoanaliser.ivanilov.utilities;

import java.io.File;

public class PathFinder {
    public static String getRoot() {
        String root = System.getProperty("user.dir");
        return root + File.separator + "texts" + File.separator;
    }
}
