package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.NoSuchCommandException;

import java.io.Console;

public class Show extends Command{

    public Show(){
        super("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }
    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        StorageOfManagers.collectionManager.stringValue();
    }
}
