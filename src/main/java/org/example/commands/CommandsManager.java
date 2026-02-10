package org.example.commands;

import exception.NoSuchCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс менеджер для команд
 */
public class CommandsManager {
    private Map<String, Command> commandRegistry = new HashMap<>();
    private ArrayList<String> commandList = new ArrayList<>();

    /**
     * Конструктор инициализирующий все команды приложения
     */
    public CommandsManager(){
        addCommand(new Show());
        addCommand(new Info());
        addCommand(new InsertNull());
        addCommand(new UpdateId());
        addCommand(new Remove_key());
        addCommand(new Clear());
        addCommand(new Save());
        addCommand(new Execute_script());
        addCommand(new Remove_greater());
        addCommand(new Remove_lower_key());
        addCommand(new History());
        addCommand(new Count_less_than_form_of_education());
        addCommand(new Filter_by_students_count());
        addCommand(new Filter_less_than_form_of_education());
        addCommand(new Exit());
        addCommand(new Help());
    }

    public void addCommand(Command cmd){
        commandRegistry.put(cmd.getName(), cmd);
    }
    /*
    public String getKey(Command cmd){
        return cmd.getName();
    }
     */

    /**
     * Метод принимает строку и возвращает команду, если такое имя есть в списке
     * @param s
     * @return Command
     * @throws NoSuchCommandException
     */
    public Command getCommand(String s) throws NoSuchCommandException {
        if(!commandRegistry.containsKey(s)){
            throw new NoSuchCommandException("Команда не найдена, повторите ввод");
        }
        return commandRegistry.getOrDefault(s, null);
    }

    /**
     * Метод, отвечающий за исполнение команд
     * @param s
     * @throws NoSuchCommandException
     */
    public void executeCommand(String s) throws NoSuchCommandException {
        String[] str = parseCommand(s);
        Command command = getCommand(str[0].toLowerCase());
        commandList.add(str[0]);
        String[] args = Arrays.copyOfRange(str, 1, str.length);
        command.execute(args);
    }
    public void executeFullInsert(String[] data){
        String[] str = parseCommand(data[0]);

    }

    public ArrayList<String> getCommandList() {
        return commandList;
    }
    public Map<String, Command> getCommandsReg(){
        return commandRegistry;
    }

    /**
     * Метод принимает строку, переданную через консоль, и разбивает её, позволяя разделить имя команды и аргументы
     * @param s
     * @return String[]
     */
    public String[] parseCommand(String s){
        return s.split(" ");
    }

}
