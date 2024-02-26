package org.example.details;
import org.example.classes.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Инкапсулированный класс для хранения коллекции и работы с ней
 */
public class Storage {
    private final LocalDateTime date;
    private Map<Long, Integer> mapKey = new HashMap<>();
    private LinkedHashMap<Integer, StudyGroup> map = new LinkedHashMap<>();


    public Storage(){
        this.date = getTime();
    }

    /**
     * Метод инициализирует коллекцию элементов и коллекцию её ключей с доступом по id элемента
     * @param map
     */
    public void mapInit(LinkedHashMap<Integer, StudyGroup> map){
        this.map = map;
        for(Integer key : map.keySet()){
            this.mapKey.put(map.get(key).getId(), key);
        }
    }

    /**
     * Взаимодействие команды remove_greater с коллекцией
     * @param group
     */
    public void remove_greater(StudyGroup group){
        ArrayList<StudyGroup> keys = new ArrayList<>();
        for(StudyGroup element : map.values()){
            if(group.compareTo(element) > 0){
                keys.add(element);
            }
        }
        for(StudyGroup groups : keys){
            map.remove(findKey(groups.getId()));
        }
    }
    /**
     * Взаимодействие команды remove_lower_key с коллекцией
     * @param key
     */
    public void remove_lower(int key){
        ArrayList<Integer> keys = new ArrayList<>();
        for(Integer key_i : map.keySet()){
            if(key > key_i){
                keys.add(key_i);
            }
        }
        if(keys.size() == map.size()){
            System.out.println("Введённый вами ключ меньше всех ключей, что есть в коллекции, удаление элементов не было произведено");
        }
        else{
            for (Integer integer : keys) {
                map.remove(integer);
            }
        }
    }
    /**
     * Взаимодействие команды filter_less_than_form_of_education с коллекцией
     * @param form
     */
    public void filterForm(FormOfEducation form){
        for(StudyGroup group : map.values()){
            if(group.getFormOfEducation().compareTo(form) < 0){
                System.out.println(group);
            }
        }
    }
    /**
     * Взаимодействие команды count_less_than_form_of_education с коллекцией
     * @param form
     */
    public void countForm(FormOfEducation form){
        int count = 0;
        for(StudyGroup group : map.values()){
            if(group.getFormOfEducation().compareTo(form) > 0){
                count+=1;
            }
        }
        System.out.println("Поле FormOfEducation меньше заданного вами значения у " + count + " элементов");
    }
    public LinkedHashMap<Integer, StudyGroup> getMap(){
        return map;
    }

    public void getKeys(){
        System.out.println(map.keySet());
    }
    /*
    public int getKey(StudyGroup obj){
        int key = Math.abs(obj.hashCode() / 10000);
        while(mapKey.containsValue(key)){
            key += 5;
        }
        mapKey.put(obj.getId(), key);
        return key;
    }
     */

    /**
     * Метод возвращает ключ к элементу коллекции по id
     * @param id
     * @return key
     */
    public Integer findKey(long id){
        if(mapKey.containsKey(id)){
            return mapKey.get(id);
        }
        return null;
    }

    /**
     * Метод возвращает элемент коллекции по его id
     * @param id
     * @return StudyGroup element
     */
    public StudyGroup getObj(long id){
        for(StudyGroup el : map.values()){
            if(el.getId() == id){
                return el;
            }
        }
        return null;
    }
    public void putMapKeys(int key, long id){
        mapKey.put(id, key);
    }
    /**
     * Взаимодействие команды update_id с коллекцией
     * @param id
     * @param el
     */
    public void replaceElement(long id, StudyGroup el){
        if(map.replace(mapKey.get(id), getObj(id), el)){
            System.out.println("замена прошла успешно, " + map.get(mapKey.get(id)));
        }
        else{
            System.out.println("Замена не удалась, что-то пошло не так");
        }

    }
    /**
     * Взаимодействие команды filter_by_students_count с коллекцией
     * @param count
     */
    public void filterStudentsCount(long count){
        int flag = 0;
        for(StudyGroup el : map.values()){
            if(el.getStudentsCount() == count){
                System.out.println(el);
                flag+=1;
            }
        }
        if(flag == 0){
            System.out.println("В ходе выполнения программы совпадений не выявлено");
        }
    }
    /**
     * Взаимодействие команды clear с коллекцией
     */
    public void clear(){
        map.clear();
    }
    /**
     * Взаимодействие команды remove_key с коллекцией
     * @param key
     */
    public void removeElement(int key){
        map.remove(key);
    }

    /**
     * Метод для фиксирования времени создания коллекции
     * @return LocalDateTime
     */
    public LocalDateTime getTime(){
        return LocalDateTime.now();
    }
    public int getSize(){
        return map.size();
    }
    public Object getType(){
        return map.getClass();
    }
    public Collection<StudyGroup> getValue(){
        return map.values();
    }
    public void putWithKey(int key, StudyGroup group){
        map.put(key, group);
    }
    @Override
    public String toString(){
        return "Тип коллекции: " + this.getType() + ",\nДата инициализации: " + this.date + ",\nКоличество элементов: " + this.getSize();
    }
}
