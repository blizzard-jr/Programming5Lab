package org.example.commands;

import org.example.details.*;
import exception.NoSuchCommandException;

public class Clear extends Command{
    public Clear(){
        super("clear", "Очистить коллекцию");
    }
    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        StorageOfManagers.collectionManager.clear();
        System.out.println("Коллекция очищена");
    }
}
