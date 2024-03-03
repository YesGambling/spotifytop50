/*
 * this class handles the Spotify OAuth services.
 */
package com.shivenanddaniel;

import com.wrapper.spotify.SpotifyApi; // spotify API wrapper
import com.wrapper.spotify.exceptions.SpotifyWebApiException; // exception handling for Spotify API
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest; // spotify Client Credentials Request object

import org.apache.hc.core5.http.ParseException; // exception handling for parsing errors

import java.io.IOException; // exception handling for I/O operations

public class SpotifyAuthenticator {
    // client ID and secret for Spotify API
    private static final String clientId = "9a29e2ff97c44483b531227c22083cb8";
    private static final String clientSecret = "755da35c1fbc46ed90036cbe3b15f7d5";

    // spotify API instance
    private SpotifyApi spotifyApi;

    // constructor initializes the spotify API with the client ID and secret
    public SpotifyAuthenticator() {
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    }

    // method to authenticate the spotify API and set the access token
    public SpotifyApi authenticate() throws IOException, SpotifyWebApiException, ParseException {
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        spotifyApi.setAccessToken(clientCredentialsRequest.execute().getAccessToken());
        return spotifyApi;
    }
}