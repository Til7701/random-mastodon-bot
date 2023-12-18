package de.holube.rmb;

import de.holube.rmb.mastodon.MyMastodonClient;
import de.holube.rmb.message.MessageConstructor;
import de.holube.rmb.message.RandomLineFromFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final List<MessageConstructor> messageConstructors = new ArrayList<>();
    static {
        // https://github.com/michmech/lemmatization-lists/blob/master/lemmatization-en.txt
        messageConstructors.add(new RandomLineFromFile("lemmatization-en.txt"));
    }

    private static MyMastodonClient client;

    public static void main(String[] args) throws IOException {
        Config config = readConfig();
        System.out.println(config);
        client = new MyMastodonClient(config.getBaseUrl(), config.getAccessToken());

        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                sendRandomMessage();
            }

        }, 100, 12 * 60 * 60 * 1000L);
    }

    private static Config readConfig() throws IOException {
        Properties prop = new Properties();
        InputStream stream = Files.newInputStream(Path.of("./client.properties"));
        prop.load(stream);
        return Config.from(prop);
    }

    private static void sendRandomMessage() {
        MessageConstructor messageConstructor =
                messageConstructors.get(ThreadLocalRandom.current().nextInt(messageConstructors.size()));
        String message = messageConstructor.getMessage();
        message = message.replace("\t", " ");
        System.out.println("Sending message: " + message);
        client.sendMessage(message);
    }

}