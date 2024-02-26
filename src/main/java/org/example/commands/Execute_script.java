package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.NoSuchCommandException;

import java.io.*;

public class Execute_script extends Command{
    public Execute_script(){
        super("execute_script", "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        argumentCount = 1;
    }

    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        FileInputStream stream;
        try {
            stream = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            throw new NoSuchCommandException("Ошибка в имени файла");
        }
        StorageOfManagers.collectionManager.execute_script(stream);
        System.out.println("Выполнение скрипта завершено");
    }
}
