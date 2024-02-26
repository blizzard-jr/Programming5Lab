package org.example.classes;

import exception.IllegalValueException;

import java.text.Normalizer;

public enum FormOfEducation {
    DISTANCE_EDUCATION("Дистанционное"),
    FULL_TIME_EDUCATION("Очное"),
    EVENING_CLASSES("Вечернее");
    final String rus;
    FormOfEducation(String rus){
        this.rus = rus;
    }
    public String getRus(){
        return this.rus;
    }
    public static boolean findForm(String s){
        for(FormOfEducation value : FormOfEducation.values()){
            if(value.getRus().equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    public static FormOfEducation getForm(String s){
        for(FormOfEducation value : FormOfEducation.values()){
            if(value.getRus().equalsIgnoreCase(s)){
                return value;
            }
        }
        throw new IllegalValueException("значение константы не распознано");// Проверить надобность
    }




}
