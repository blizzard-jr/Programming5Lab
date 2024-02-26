package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.NoSuchCommandException;

public class Remove_lower_key extends Command{
    public Remove_lower_key(){
        super("remove_lower_key", "Удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        argumentCount = 1;
    }
    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        int key;
        try{
            key = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            throw new NoSuchCommandException("Неверное значение ключа");
        }
        StorageOfManagers.collectionManager.remove_lower(key);
    }
}
