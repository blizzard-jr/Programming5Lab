package org.example.details;

import org.example.classes.*;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.details.StorageOfManagers.*;


/**
 * Класс для взаимодействия с клиентом
 */

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);

    public void writeln(Object o){
        System.out.println(o);
    }

    /**
     * Метод для инициализации коллекции значениями из файла
     * @param s     Имя файла
     * @return map
     */
    public LinkedHashMap<Integer, StudyGroup> readFile(String s, String fileName) {
        writeln(s);
        String answer = scanner.nextLine();
        while(true){
            if(answer.isEmpty()){
                try{
                    return fileSystem.parseToList(fileName);
                } catch(IOException e){
                    System.out.println("Не удалось получить данные из указанного файла, введите новое имя, \"Enter\" - для использования пустой коллекции или 1 для выхода: ");
                    String ans = scanner.nextLine();
                    if(ans.equals("1")){
                        System.out.println("Всего доброго");
                        System.exit(0);
                        }
                    else if(ans.isEmpty()){
                        System.out.println("Будет использована пустая коллекция");
                        return new LinkedHashMap<>();
                        }
                    else {
                        fileName = ans;
                        }
                    }
                }
            else {
                fileSystem.setFileName(fileName);
                return new LinkedHashMap<>();
                }
            }
        }

    /**
     * Метод читает ввод пользователя с предварительным выводом сообщения, внутри используется валидация для строк
     * @param message
     * @return String
     */
    public String readWithMessage(String message){
        System.out.println(message);
        String s = scanner.nextLine();
        while(true){
            if(s.isEmpty()){
                writeErr("Поле не может быть пустым, повторите ввод");
                s = scanner.nextLine();
            }
            else{
                break;
            }
        }
        return s;
    }
    /**
     * Метод читает ввод пользователя с предварительным выводом сообщения, внутри используется валидация для числовых значений
     * @param message
     * @return String
     */
    public String readWithMessage(String message, boolean acceptCompare, int compare, boolean acceptFloat) {
        double numb;
        String s = readWithMessage(message);
        Pattern floatPattern = Pattern.compile("[-+]?[0-9]*\\.[0-9]+");
        Matcher matcherFloat = floatPattern.matcher(s);
        if(matcherFloat.find() && acceptFloat){
            while (true) {
                try {
                    numb = Double.parseDouble(s);
                    break;
                } catch (NumberFormatException e) {
                    writeErr("Некорректный ввод, попробуйте ещё разок");
                    s = readWithMessage(message);
                }
            }
        }
        else{
            while (true) {
                try {
                    numb = Long.parseLong(s);
                    break;
                } catch (NumberFormatException e) {
                    writeErr("Некорректный ввод, попробуйте ещё раз");
                    s = readWithMessage(message);
                }
            }
        }

        if(acceptCompare){
            if (!compare(numb, compare)) {
                writeErr("Требования к числовому полю не выполнены повторите ввод");
                s = readWithMessage(message, true, compare, acceptFloat);
            }
        }
        return s;
    }
    public Color colorInit(String message){
        writeln(message);
        writeln("Допустимые значения: ");
        for(Color color : Color.values()){
            System.out.println(color.getRus());
        }
        String s = scanner.nextLine();
        while(!Color.findColor(s) || s.isEmpty()){
            writeErr("Значение константы не распознано, повторите ввод");
            s = scanner.nextLine();
        }
        return Color.getColor(s);
    }

    /**
     * Метод инициализирует enum FormOfEducation - поле элемента коллекции
     * @param message
     * @return FormOfEducation
     */
    public FormOfEducation formInit(String message){
        writeln(message);
        writeln("Допустимые значения: ");
        for(FormOfEducation form : FormOfEducation.values()){
            System.out.println(form.getRus());
        }
        String s = scanner.nextLine();
        while(!FormOfEducation.findForm(s) || s.isEmpty()){
            writeErr("Значение константы не распознано, повторите ввод");
            s = scanner.nextLine();
        }
        return FormOfEducation.getForm(s);
    }
    public boolean hasNextLine(){
        return scanner.hasNextLine();
    }
    /**
     * Метод инициализирует enum Semester - поле элемента коллекции
     * @param message
     * @return FormOfEducation
     */

    public Semester semInit(String message){
        writeln(message);
        writeln("Допустимые значения: ");
        for(Semester sem : Semester.values()){
            System.out.println(sem.getRus());
        }
        String s = scanner.nextLine();
        while(!Semester.findSem(s) || s.isEmpty()){
            writeErr("Значение константы не распознано, повторите ввод");
            s = scanner.nextLine();
        }
        return Semester.getSem(s);
    }
    /**
     * Метод инициализирует location - поле элемента коллекции
     * @return FormOfEducation
     */
    public Location locationInit(){
        Long x = Long.parseLong(readWithMessage("Введите координату админа x: ", false, 0, false));
        String name = readWithMessage("Введите названия локации админа: ");
        return new Location(x, Long.parseLong(readWithMessage("Введите координату админа y: ", false, 0, false)), Integer.parseInt(readWithMessage("Введите координату админа z: ", false, 0, false)), name);
    }
    /**
     * Метод инициализирует coordinates - поле элемента коллекции
     * @return FormOfEducation
     */
    public Coordinates coordinatesInit(){
        float x = Float.parseFloat(readWithMessage("Введите координату x: ",true,  -417, true));
        double y = Double.parseDouble(readWithMessage("Введите координату y: ",true,  -574, true));
        return new Coordinates(x, y);
    }
    /**
     * Метод инициализирует groupAdmin - поле элемента коллекции
     * @return FormOfEducation
     */
    public Person personInit(){
        return new Person(readWithMessage("Введите имя админа группы: "),
                Float.parseFloat(readWithMessage("Введите рост админа: ",true,  0, true)),
                Double.parseDouble(readWithMessage("Введите вес админа: ",true,  0, true)),
                colorInit("Введите цвет волос админа: "),
                locationInit());
    }
    /**
     * Метод инициализирует элемент коллекции
     * @return FormOfEducation
     */
    public StudyGroup studyGroupInit(String name, long studentsCount, long shouldBeExpelled){
        FormOfEducation formOfEducation = formInit("Введите значение формы обучения");
        Semester sem = semInit("Введите константу семестра: ");
        return new StudyGroup(name, studentsCount, shouldBeExpelled, coordinatesInit(), formOfEducation, sem, personInit());
    }
    public boolean compare(double num, int comp){
        return num > comp;
    }
    public void writeErr(Object obj){
        System.err.println(obj);
    }


}
