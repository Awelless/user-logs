package com.innowisegroup.data;

import com.innowisegroup.FileConfiguration;
import com.innowisegroup.entity.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;

public class UserFileDataWriter {

    private UserFileDataWriter() {
    }

    public static void write(Collection<User> users) throws IOException {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(FileConfiguration.USERS_FILE_NAME))) {

            outputStream.writeObject(users);
        }
    }
}
