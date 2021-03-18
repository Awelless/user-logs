package com.innowisegroup.initializer;

import com.innowisegroup.FileConfiguration;
import com.innowisegroup.data.UserFileDataReader;
import com.innowisegroup.entity.User;
import com.innowisegroup.observer.IdGeneratorObserver;
import com.innowisegroup.observer.Observer;
import com.innowisegroup.observer.UserRepositoryObserver;
import com.innowisegroup.repository.UserRepository;
import com.innowisegroup.util.IdGenerator;

import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Initializer {

    public static void initialize() throws InitializationException {
        try {
            createFiles();
            initIdGenerator();
            initUserRepository();
            attachObservers();
        } catch (IllegalAccessException | IOException | ClassNotFoundException e) {
            throw new InitializationException(e);
        }
    }

    private static void createFiles() throws IllegalAccessException, IOException {
        Field[] fields = FileConfiguration.class.getDeclaredFields();

        for (Field field : fields) {
            String value = null;

            if (field.getType() == String.class) {
                value = (String) field.get(null);
            }

            Path path = Path.of(value);

            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        }
    }

    private static void initIdGenerator() throws IOException, InitializationException {

        String data = Files.readString(Path.of(FileConfiguration.ID_FILE_NAME));

        int id;
        try {
            id = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            id = 0;
        }

        IdGenerator.init(id);
    }

    private static void initUserRepository() throws IOException, ClassNotFoundException, InitializationException {

        List<User> users;

        try {
            users = UserFileDataReader.read();
        } catch (EOFException e) {
            users = new ArrayList<>();
        }

        UserRepository.init(IdGenerator.getInstance());

        UserRepository repository = UserRepository.getInstance();
        repository.saveAll(users);
    }

    private static void attachObservers() {
        Observer idGeneratorObserver = new IdGeneratorObserver();
        IdGenerator.getInstance().attachObserver(idGeneratorObserver);

        Observer userRepositoryObserver = new UserRepositoryObserver();
        UserRepository.getInstance().attachObserver(userRepositoryObserver);
    }
}
