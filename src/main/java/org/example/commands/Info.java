package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.NoSuchCommandException;

public class Info extends Command{
    public Info() {
        super("info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }
    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        StorageOfManagers.collectionManager.info();
    }
}
