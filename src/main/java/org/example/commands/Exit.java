package org.example.commands;


public class Exit extends Command{
    public Exit(){
        super("exit", "Завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String[] args){
        System.out.println("I`ll be back, babe :)");
        System.exit(0);
    }
}
