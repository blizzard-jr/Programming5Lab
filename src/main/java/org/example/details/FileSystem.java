package org.example.details;

import org.example.classes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exception.IllegalValueException;
import exception.NoSuchCommandException;

import java.io.*;
import java.util.*;

/**
 * Класс отвечает за работу с файлами
 */
public class FileSystem {
    private String fileName;

    /**
     * Метод десериализует информацию из файла в Map POJO
     * @param file
     * @return map
     * @throws IOException
     */
    public LinkedHashMap<Integer, StudyGroup> parseToList(String file) throws IOException {
        FileInputStream f = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(f);
        ObjectMapper o = new ObjectMapper().enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        LinkedHashMap<Integer, StudyGroup> map_one = o.readValue(input, new TypeReference<>(){});
        LinkedHashMap<Integer, StudyGroup> map = new LinkedHashMap<>();

        for(Integer keys : map_one.keySet()){
            StudyGroup group = map_one.get(keys);
            map.put(keys, group);
        }
        fileInit(file);
        return map_one;
    }

    /**
     * Метод для запоминания пути к файлу с которым работает коллекция
     * @param s
     */
    public void fileInit(String s){
        this.fileName = s;
    }

    /**
     * Метод сериализует данные из коллекции в файл
     * @param map
     */
    public void parseToFile(LinkedHashMap<Integer, StudyGroup> map)  {
        FileOutputStream f = null;
        try{
            f = new FileOutputStream(fileName);
        }catch(FileNotFoundException e){
            throw new IllegalValueException("Проблема с файлом");
        }
        OutputStreamWriter writer = new OutputStreamWriter(f);
        ObjectMapper o = new ObjectMapper();
        o.registerModules(new JavaTimeModule());
        o.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, SerializationFeature.INDENT_OUTPUT);
        //File file = new File("C:\\Users\\Кот\\IdeaProjects\\ProgLab5\\src\\details\\Data.json");
        try {
            o.writeValue(writer, map);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    /**
     * Метод для парсинга скрипта из файла
     * @param stream
     */
    public void parseScript(FileInputStream stream)  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            while (reader.ready()) {
                StorageOfManagers.commandsManager.executeCommand(reader.readLine());
            }
        }catch(IOException | NoSuchCommandException e){
            throw new IllegalValueException("Проблема с парсингом файла или команда не найдена");
        }
    }
}
