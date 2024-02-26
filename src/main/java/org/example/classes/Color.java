package org.example.classes;

import exception.IllegalValueException;

public enum Color {
    GREEN("Зелёный"),
    RED("Красный"),
    BLACK("Чёрный"),
    ORANGE("Оранжевый"),
    WHITE("Белый");
    private final String rus;
    Color(String rus){
        this.rus = rus;
    }
    public String getRus(){
        return this.rus;
    }
    public static boolean findColor(String s){
        for(Color value : Color.values()){
            if(value.getRus().equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    public static Color getColor(String s){
        for(Color value : Color.values()){
            if(value.getRus().equalsIgnoreCase(s)){
                return value;
            }
        }
        throw new IllegalValueException("Константа не распознана");
    }


}
