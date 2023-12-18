package de.holube.rmb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Properties;

@ToString
@Getter
@RequiredArgsConstructor
public class Config {

    private final String baseUrl;
    private final String accessToken;

    public static Config from(Properties prop) {
        return new Config(prop.getProperty("baseUrl"), prop.getProperty("accessToken"));
    }

}

