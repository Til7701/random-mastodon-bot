package de.holube.rmb;

import de.holube.rmb.mastodon.MyMastodonClient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

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
        client.sendMessage("Test");
    }

}