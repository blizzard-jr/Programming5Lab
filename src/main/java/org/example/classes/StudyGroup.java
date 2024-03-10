package org.example.classes;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import exception.IllegalValueException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;
import static org.example.details.StorageOfManagers.*;

/**
 * Класс - объект коллекции
 */
public class StudyGroup implements Comparable<StudyGroup> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long studentsCount; //Значение поля должно быть больше 0
    private long shouldBeExpelled; //Значение поля должно быть больше 0
    private Coordinates coordinates; //Поле не может быть null
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy-HH:mm:ss")
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null


    public StudyGroup(String name, long studentsCount, long shouldBeExpelled, Coordinates coordinates, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        setName(name);
        setStudentsCount(studentsCount);
        setShouldBeExpelled(shouldBeExpelled);
        setCoordinates(coordinates);
        this.creationDate =LocalDateTime.now();
        this.id = collectionManager.getId();
        setFormOfEducation(formOfEducation);
        setGroupAdmin(groupAdmin);
        setSemesterEnum(semesterEnum);
    }
    public StudyGroup(){
        this.id = collectionManager.getId();
        this.creationDate = LocalDateTime.now();
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setName(String name) {
        if(!collectionManager.validate(name, false)){
            throw new IllegalValueException("Поле name не может быть пустым или равняться нулю");
        }
        else {
            this.name = name;
        }
    }
    public void setShouldBeExpelled(long shouldBeExpelled) {
        if (collectionManager.validate(shouldBeExpelled, 0)){
            this.shouldBeExpelled = shouldBeExpelled;
        }
        else{
            throw new IllegalValueException("Число студентов на отчислении должно быть больше нуля");
        }
    }


    public void setStudentsCount(long studentsCount) {
        if (collectionManager.validate(studentsCount, 0)){
            this.studentsCount = studentsCount;
        }
        else{
            throw new IllegalValueException("Число студентов должно быть больше нуля");
        }
    }
    public void setCoordinates(Coordinates coordinates){
        if(collectionManager.validate(coordinates, false)){
            this.coordinates = coordinates;
        }
        else{
            throw new IllegalValueException("Объект: координаты не может быть null");
        }
    }

    public void setGroupAdmin(Person groupAdmin) {
        if(collectionManager.validate(groupAdmin, false)){
            this.groupAdmin = groupAdmin;
        }
        else{
            throw new IllegalValueException("Объект: groupAdmin не может быть null");
        }

    }
    public void setSemesterEnum(Semester semesterEnum) {
        if(collectionManager.validate(semesterEnum, false)){
            this.semesterEnum = semesterEnum;
        }
        else{
            throw new IllegalValueException("Объект: semesterEnum не может быть null");
        }
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        if(collectionManager.validate(formOfEducation, false)){
            this.formOfEducation = formOfEducation;
        }
        else{
            throw new IllegalValueException("Объект: formOfEducation не может быть null");
        }
    }
    public long getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public long getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    @Override
    public String toString(){
        return "Name: " + this.name + ", StudentsCount: " + this.studentsCount + ", ShouldBeExpelled: " + this.shouldBeExpelled + ", Coordinates: " + this.coordinates.toString() + ", creationDate: " + this.creationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ", id: " + this.id + ", formOfEducation: " + this.formOfEducation + ", semesterEnum: " + this.semesterEnum + ", groupAdmin: " + this.groupAdmin.toString();
    }
    @Override
    public int compareTo(StudyGroup group){
        return this.id.compareTo(group.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.name, this.groupAdmin);
    }


}
