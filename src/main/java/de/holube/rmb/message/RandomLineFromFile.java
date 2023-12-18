package de.holube.rmb.message;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class RandomLineFromFile implements MessageConstructor {

    private final String pathToFile;

    @Override
    public String getMessage() {
        try {
            String[] lines = Files.readAllLines(Path.of(pathToFile)).toArray(new String[]{});
            return lines[ThreadLocalRandom.current().nextInt(lines.length)];
        } catch (IOException e) {
            return null;
        }
    }

}
