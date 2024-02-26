package org.example.commands;

import exception.NoSuchCommandException;

/**
 * Абстрактный класс для всех команд, доступных в приложении
 */

public abstract class Command {
    protected int argumentCount = 0;
    private String name;
    private String description;

    /**
     * Конструктор класса, принимает имя и описание команды
     * @param name
     * @param description
     */
    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }
    /*
    public int getArgumentCount(){
        return this.argumentCount;
    }
     */
    public String getName() {
        return name;
    }

    /**
     * Метод проверяет количество аргументов команды
     * @param args
     * @throws NoSuchCommandException
     */
    public void checkArgs(String[] args) throws NoSuchCommandException {
        if(args.length != argumentCount){
            throw new NoSuchCommandException("Неверное количество аргументов команды");
        }
    }
    public String getDescription() {
        return description;
    }

    /**
     * Абстрактный метод, отвечающий за исполнение всех команд
     * @param args
     * @throws NoSuchCommandException
     */
    public abstract void execute(String[] args) throws NoSuchCommandException;
}
