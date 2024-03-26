package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.IllegalValueException;
import exception.NoSuchCommandException;

public class Remove_key extends Command{
    public Remove_key(){
        super("remove", "Удалить элемент из коллекции по его ключу");
        argumentCount = 1;
    }
    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        int key;
        try{
            key = Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            throw new IllegalValueException("Ошибка в аргументе команды");
        }
        StorageOfManagers.collectionManager.remove(key);
    }
}
