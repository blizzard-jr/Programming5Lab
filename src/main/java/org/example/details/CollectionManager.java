package org.example.details;

import org.example.classes.*;
import exception.IllegalValueException;
import java.io.FileInputStream;
import java.util.*;
import static org.example.details.StorageOfManagers.*;

/**
 * Менеджер коллекции, связывает между собой UserInterface, FileSystem, Command и Storage
 */
public class CollectionManager {
    private Set<Long> idSet = new HashSet<>();

    /**
     * Метод передаёт задачу исполнения команды execute_script FileSystem
     * @param f
     */
    public void execute_script(FileInputStream f) {
        fileSystem.parseScript(f);
    }
    public void insertFormScript(ArrayList<String> args){
        try{
            int key = Integer.parseInt(args.get(0));
            long studentsCount = Long.parseLong(args.get(2));
            long shouldBeExpelled = Long.parseLong(args.get(3));
            FormOfEducation form = FormOfEducation.getForm(args.get(4));
            Semester sem = Semester.getSem(args.get(5));
            float coordinatesX = Float.parseFloat(args.get(6));
            double coordinatesY = Double.parseDouble(args.get(7));
            Float height = Float.parseFloat(args.get(9));
            double weight = Double.parseDouble(args.get(10));
            Color color = Color.getColor(args.get(11));
            Long x = Long.parseLong(args.get(12));
            long y = Long.parseLong(args.get(14));
            int z = Integer.parseInt(args.get(15));
            Location loc = new Location(x, y, z, args.get(13));
            Person admin = new Person(args.get(8), height, weight, color, loc);
            Coordinates coord = new Coordinates(coordinatesX, coordinatesY);
            StudyGroup el = new StudyGroup(args.get(1), studentsCount, shouldBeExpelled, coord, form, sem, admin);
            storage.putWithKey(key, el);
        }catch(NumberFormatException | IllegalValueException e){
            throw new IllegalValueException("Значения команды insert  в скрипте не валидны");
        }
    }

    /**
     * Метод для исполнения команды show
     */
    public void stringValue(){
        Collection<StudyGroup> s = storage.getValue();
        for (StudyGroup group : s) {
            userInterface.writeln(group.toString());
        }
        storage.getKeys();
    }

    /**
     * Метод передаёт задачу исполнения команды remove_greater Storage
     * @param group
     */
    public void remove_greater(StudyGroup group){
        storage.remove_greater(group);
    }

    /**
     * Метод выводит историю исполнения команд, хранящуюся в CommandsManager
     */
    public void history(){
        ArrayList<String> list = commandsManager.getCommandList();
        if(list.size() < 14){
            userInterface.writeErr("Количества исполненных команд не достаточно для вывода истории");
        }
        else{
            for (int i = 0; i < 14; i++) {
                userInterface.writeln(list.get(i));
            }
        }
    }

    /**
     * Метод передаёт задачу исполнения команды filter_less_than_form_of_education Storage
     * @param form
     */
    public void filterFormOfEducation(FormOfEducation form){
        storage.filterForm(form);
    }
    /**
     * Метод передаёт задачу исполнения команды filter_by_students_count Storage
     * @param count
     */
    public void filterStudentsCount(long count){
        storage.filterStudentsCount(count);
    }
    /**
     * Метод передаёт задачу исполнения команды remove_lower Storage
     * @param key
     */
    public void remove_lower(int key){
        storage.remove_lower(key);
    }
    /**
     * Метод передаёт задачу исполнения команды count_less_than_form_of_education Storage
     * @param form
     */
    public void countForm(FormOfEducation form){
        storage.countForm(form);
    }
    /*
    public int keyInit(){
        return Integer.parseInt(userInterface.readWithMessage("Добавьте ключ к объекту"));
    }

     */
    public void updateFromScript(ArrayList<String> args){
        try{
            int id = Integer.parseInt(args.get(0));
            long studentsCount = Long.parseLong(args.get(2));
            long shouldBeExpelled = Long.parseLong(args.get(3));
            FormOfEducation form = FormOfEducation.getForm(args.get(4));
            Semester sem = Semester.getSem(args.get(5));
            float coordinatesX = Float.parseFloat(args.get(6));
            double coordinatesY = Double.parseDouble(args.get(7));
            Float height = Float.parseFloat(args.get(9));
            double weight = Double.parseDouble(args.get(10));
            Color color = Color.getColor(args.get(11));
            Long x = Long.parseLong(args.get(12));
            long y = Long.parseLong(args.get(14));
            int z = Integer.parseInt(args.get(15));
            Location loc = new Location(x, y, z, args.get(13));
            Person admin = new Person(args.get(8), height, weight, color, loc);
            Coordinates coord = new Coordinates(coordinatesX, coordinatesY);
            StudyGroup el = new StudyGroup(args.get(1), studentsCount, shouldBeExpelled, coord, form, sem, admin);
            el.setId(id);
            storage.replaceElement(id, el);
        }catch(NumberFormatException | IllegalValueException e){
            throw new IllegalValueException("Значения команды update в скрипте не валидны");
        }
    }

    /**
     * Метод, используя методы Storage добавляет новый элемент с заданным ключом в коллекцию, а также добавляет его в коллекцию ключей
     * @param element
     * @param arg
     */

    public void insertWithKey(StudyGroup element, String arg){
        Integer key;
        try{
            key = Integer.parseInt(arg);
        }catch(NumberFormatException e){
            throw new IllegalValueException("Не получается получить значение ключа из аргумента");
        }
        storage.putWithKey(key, element);
        storage.putMapKeys(key, element.getId());
        userInterface.writeln("Объект успешно добавлен");
    }

    /**
     * Метод инициализирует новый элемент и заменяет на него элемент с таким же id
     * @param id
     * @param name
     * @param studentsCount
     * @param shouldBeExpelled
     */
    public void update(Integer id, String name, long studentsCount, long shouldBeExpelled){
        StudyGroup element = userInterface.studyGroupInit(name, studentsCount, shouldBeExpelled);
        element.setId(id);
        storage.replaceElement(id, element);
    }

    /**
     * Метод присваивает каждому элементу коллекции рандомный id
     * @return id
     */
    public Long getId(){
        long id = (long) (Math.random() * 1000);
        while(true){
            if(idSet.contains(id)){
                id += (long) (Math.random() * 10);
            }
            else{
                idSet.add(id);
                break;
            }
        }
        return id;
    }
    /**
     * Метод передаёт задачу исполнения команды remove_key Storage
     * @param key
     */
    public void remove(int key){
        storage.removeElement(key);
    }
    /**
     * Метод передаёт задачу исполнения команды clear Storage
     */
    public void clear(){
        storage.clear();
    }
    /**
     * Метод передаёт задачу исполнения команды save Storage
     */
    public void save() {
        fileSystem.parseToFile(storage.getMap());
    }

    /**
     * Метод проверяет валидность полей элемента коллекции
     * @param obj
     * @param acceptEmpty       Разрешено ли значение пустой строки
     */
    public boolean validate(Object obj, boolean acceptEmpty){
        return !(!acceptEmpty & (obj.equals("") || obj == null));
    }

    /**
     * Метод проверяет валидность числовых полей
     * @param numb
     * @param compare
     */
    public boolean validate(double numb,int compare){
        return numb > compare;
    }

    /**
     * Метод выводит информацию о коллекции с помощью метода UserInterface
     */
    public void info(){
        userInterface.writeln(storage.toString());
    }
}
