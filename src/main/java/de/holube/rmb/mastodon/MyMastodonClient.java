package de.holube.rmb.mastodon;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyMastodonClient {

    private final String baseUrl;
    private final String accessToken;
    private final MastodonAPI api = new MastodonAPI();

    public void sendMessage(String message) {
        api.publishStatus(baseUrl, accessToken, message);
    }

}
