package org.example;

import org.example.classes.*;
import org.example.commands.*;
import org.example.details.*;
import exception.IllegalValueException;
import exception.NoSuchCommandException;
import java.util.*;

/**
 * Главный класс программы, отвечающий за инициализацию используемых менеджеров, коллекции и цикл исполнения.
 */

public class Main {
    //C:\\Users\\Кот\\IdeaProjects\\ProgLab5\\src\\details\\CommandScript.txt
    //"C:\\Users\\Кот\\IdeaProjects\\ProgLab5\\src\\details\\Data.json");
    public static void main(String[] args) {
        Storage st = new Storage();
        FileSystem file = new FileSystem();
        UserInterface user = new UserInterface();
        CommandsManager manage = new CommandsManager();
        CollectionManager collection = new CollectionManager();
        StorageOfManagers storageOfManagers = new StorageOfManagers(collection, user, file, manage, st);
        LinkedHashMap<Integer, StudyGroup> map = user.readFile("Перед началом работы введите имя файла для инициализации коллекции: ");
        StorageOfManagers.storage.mapInit(map);
        user.writeln("Файл обработан, коллекция инициализирована. Программа готова к работе");
        while(user.hasNextLine()){
            try {
                manage.executeCommand(user.readWithMessage(""));
            }catch(NoSuchCommandException | IllegalValueException e){
                user.writeErr(e.getMessage());
            }catch(NoSuchElementException e){
                System.err.println("Программа завершена без сохранения данных");
                System.exit(0);
            }
        }
    }
}