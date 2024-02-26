package org.example.commands;

import org.example.classes.*;
import org.example.details.StorageOfManagers;
import exception.IllegalValueException;
import exception.NoSuchCommandException;
import java.util.Arrays;
public class InsertNull extends Command{
    public InsertNull(){
        super("insert", "Добавить новый элемент с заданным ключом");
        argumentCount = 4;
    }

    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        String[] fields = Arrays.copyOfRange(args, 1, args.length);
        String name = fields[0];
        long studentsCount;
        long studentsShouldBeExpelled;
        try{
            studentsCount = Long.parseLong(fields[1]);
            studentsShouldBeExpelled = Long.parseLong(fields[2]);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Введены некорректные значения для объекта");
        }
        if(fields[0].isEmpty() || studentsCount <=0 || studentsShouldBeExpelled <=0){
            throw new IllegalValueException("Введённые значения не валидны");
        }
        StudyGroup obj = StorageOfManagers.userInterface.studyGroupInit(name, studentsCount, studentsShouldBeExpelled);
        StorageOfManagers.collectionManager.insertWithKey(obj, args[0]);

    }
}
