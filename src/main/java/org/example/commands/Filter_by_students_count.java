package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.NoSuchCommandException;

public class Filter_by_students_count extends Command{
    public Filter_by_students_count(){
        super("filter_by_students_count", "Вывести элементы, значение поля studentsCount которых равно заданному");
        argumentCount = 1;
    }

    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        long studentCount;
        try{
            studentCount = Long.parseLong(args[0]);
        }catch(NumberFormatException e){
            throw new NoSuchCommandException("Проблема в аргументе команды");
        }
        StorageOfManagers.collectionManager.filterStudentsCount(studentCount);
    }
}
