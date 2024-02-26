package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.NoSuchCommandException;
public class Save extends Command{
    public Save(){
        super("save", "Сохранить коллекцию в файл");
    }
    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        StorageOfManagers.collectionManager.save();
        System.out.println("Коллекция сохранена в файл");
    }
}
