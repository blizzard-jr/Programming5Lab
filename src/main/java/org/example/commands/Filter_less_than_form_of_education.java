package org.example.commands;

import org.example.classes.*;
import org.example.details.StorageOfManagers;
import exception.NoSuchCommandException;

public class Filter_less_than_form_of_education extends Command {
    public Filter_less_than_form_of_education(){
        super("filter_less_than_form_of_education", "Вывести элементы, значение поля formOfEducation которых меньше заданного");
        argumentCount = 1;
    }


    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        FormOfEducation form = FormOfEducation.getForm(args[0]);
        StorageOfManagers.collectionManager.filterFormOfEducation(form);
    }
}
