package org.example.classes;

import exception.IllegalValueException;

public enum Semester {
    FIRST("Первый"),
    FOURTH("Четвёртый"),
    FIFTH("Пятый");
    final String rus;
    Semester(String rus){
        this.rus = rus;
    }

    public String getRus() {
        return this.rus;
    }

    public static boolean findSem(String s){
        for(Semester value : Semester.values()){
            if(value.getRus().equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    public static Semester getSem(String s){
        for(Semester value : Semester.values()){
            if(value.getRus().equalsIgnoreCase(s)){
                return value;
            }
        }
        throw new IllegalValueException("Значение константы не распознано");
    }
}
