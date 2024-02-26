package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.IllegalValueException;
import exception.NoSuchCommandException;

public class UpdateId extends Command{
    public UpdateId(){
        super("update", "Обновить значение элемента коллекции, id которого равен заданному");
        argumentCount = 4;
    }
    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        String name = args[1];
        int id;
        long studentsCount;
        long studentsShouldBeExpelled;
        try{
            id = Integer.parseInt(args[0]);
            studentsCount = Long.parseLong(args[2]);
            studentsShouldBeExpelled = Long.parseLong(args[3]);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Введены некорректные значения для объекта");
        }
        if(name.isEmpty() || studentsCount <=0 || studentsShouldBeExpelled <=0){
            throw new IllegalValueException("Введённые значения не валидны");
        }
        StorageOfManagers.collectionManager.update(id, name, studentsCount, studentsShouldBeExpelled);
        System.out.println("Объект успешно заменён");
    }
}
