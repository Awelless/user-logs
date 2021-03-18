package com.innowisegroup.observer;

import com.innowisegroup.FileConfiguration;
import com.innowisegroup.util.IdGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Used to save actual {@link IdGenerator} value to file
 */
public class IdGeneratorObserver implements Observer {
    @Override
    public void update() throws ObserverException {

        IdGenerator generator = IdGenerator.getInstance();
        int lastValue = generator.getLastValue();

        byte[] data = String.valueOf(lastValue).getBytes();

        Path filePath = Path.of(FileConfiguration.ID_FILE_NAME);

        try {
            Files.write(filePath, data);
        } catch (IOException e) {
            throw new ObserverException(e);
        }
    }
}
