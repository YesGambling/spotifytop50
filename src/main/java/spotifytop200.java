import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class spotifytop200 {
    private static final String clientId = "9a29e2ff97c44483b531227c22083cb8";
    private static final String clientSecret = "b611950d144d460ab1472dadc18fbd23";

    public static void main(String[] args) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        getTopSongs(spotifyApi);
    }

    private static void getTopSongs(SpotifyApi spotifyApi) {
        String playlistId = "37i9dQZEVXbMDoHDwVN2tF"; // This is the ID for Spotify's Top 200 playlist
        GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(playlistId).build();

        try {
            Playlist playlist = getPlaylistRequest.execute();
            PlaylistTrack[] tracks = playlist.getTracks().getItems();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a number between 1 and 200: ");
            try {
                int songNumber = scanner.nextInt();

                if (songNumber >= 1 && songNumber <= 200) {
                    System.out.println("The song at position " + songNumber + " is " + tracks[songNumber - 1].getTrack().getName());
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 200.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 200.");
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
