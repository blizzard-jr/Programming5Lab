package org.example.details;

import org.example.commands.CommandsManager;

/**
 * Класс для хранения менеджеров, позволяет избежать использования синглтонов
 */

public class StorageOfManagers {
    public static Storage storage;
    public static CollectionManager collectionManager;
    public static UserInterface userInterface;
    public static FileSystem fileSystem;
    public static CommandsManager commandsManager;
    public StorageOfManagers(CollectionManager collectionManager, UserInterface userInterface, FileSystem fileSystem, CommandsManager commandsManager, Storage storage){
        StorageOfManagers.collectionManager = collectionManager;
        StorageOfManagers.userInterface = userInterface;
        StorageOfManagers.fileSystem = fileSystem;
        StorageOfManagers.commandsManager = commandsManager;
        StorageOfManagers.storage = storage;
    }


}
