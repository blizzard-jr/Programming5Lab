package org.example.commands;

import org.example.details.StorageOfManagers;
import exception.NoSuchCommandException;

import java.util.Map;


public class Help extends Command{
    public Help(){
        super("help", "Вывести справку по доступным командам");
    }

    @Override
    public void execute(String[] args) throws NoSuchCommandException {
        checkArgs(args);
        Map<String, Command> map = StorageOfManagers.commandsManager.getCommandsReg();
        for(Command command : map.values()){
            System.out.println(command.getName() + " - " + command.getDescription());
        }
    }
}
