/*
 * this file will handle the Spotify OAuth services. 
 */
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class SpotifyAuthenticator {
    private static final String clientId = "9a29e2ff97c44483b531227c22083cb8";
    private static final String clientSecret = "755da35c1fbc46ed90036cbe3b15f7d5";

    private SpotifyApi spotifyApi;

    public SpotifyAuthenticator() {
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    }

    public SpotifyApi authenticate() throws IOException, SpotifyWebApiException, ParseException {
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        spotifyApi.setAccessToken(clientCredentialsRequest.execute().getAccessToken());
        return spotifyApi;
    }
}
