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
    //C:\Temp\Programming5Lab\src\main\java\org\example\details\CommandScript.txt
    //"C:\Temp\Programming5Lab\src\main\java\org\example\details\Data.json";
    public static void main(String[] args) {
        String fileName = "";
        try{
            fileName = args[0];
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Кажется, вы забыли передать имя файла");
        }
        //Журнал КОД от яндекс, почитать
        System.out.println(fileName);
        Storage st = new Storage();
        FileSystem file = new FileSystem();
        UserInterface user = new UserInterface();
        CommandsManager manage = new CommandsManager();
        CollectionManager collection = new CollectionManager();
        StorageOfManagers storageOfManagers = new StorageOfManagers(collection, user, file, manage, st);
        LinkedHashMap<Integer, StudyGroup> map;
        if(!fileName.isEmpty()){
            map = user.readFile("Желаете инициализировать коллекцию из файла? \"Enter\" - Да; Another - Нет: ", fileName);
        }
        else{
            System.out.println("Будет использована пустая коллекция");
            map = new LinkedHashMap<>();
        }
        StorageOfManagers.storage.mapInit(map);
        user.writeln("Программа готова к работе");
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