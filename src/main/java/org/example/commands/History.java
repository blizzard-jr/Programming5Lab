package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.NoSuchCommandException;
public class History extends Command{
    public History(){
        super("history","Вывести последние 14 команд (без их аргументов)");
    }
    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        StorageOfManagers.collectionManager.history();
    }
}
