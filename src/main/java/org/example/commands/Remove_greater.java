package org.example.commands;

import org.example.classes.*;
import org.example.details.*;
import exception.NoSuchCommandException;

public class Remove_greater extends Command{
    public Remove_greater(){
        super("remove_greater", "Удалить из коллекции все элементы, превышающие заданный");
        argumentCount = 3;
    }
    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        String name = args[0];
        long studentsCount;
        long shouldBeExpelled;
        try{
            studentsCount = Long.parseLong(args[1]);
            shouldBeExpelled = Long.parseLong(args[2]);
        }catch(NumberFormatException e){
            throw new NoSuchCommandException("Проблема с аргументами команды");
        }
        StudyGroup group = StorageOfManagers.userInterface.studyGroupInit(name, studentsCount, shouldBeExpelled);
        StorageOfManagers.collectionManager.remove_greater(group);
    }
}
