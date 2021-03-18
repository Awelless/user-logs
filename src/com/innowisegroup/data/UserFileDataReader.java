package com.innowisegroup.data;

import com.innowisegroup.FileConfiguration;
import com.innowisegroup.entity.User;

import java.io.*;
import java.util.List;

public final class UserFileDataReader {

    private UserFileDataReader() {
    }

    //in users file there are always valid list of users
    @SuppressWarnings("unchecked")
    public static List<User> read() throws IOException, ClassNotFoundException {

        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(FileConfiguration.USERS_FILE_NAME))) {

            return (List<User>) inputStream.readObject();
        }
    }
}
