// import all necessary spotify stuff 
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;

//import parse exception
import org.apache.hc.core5.http.ParseException;

// imports important utils
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class spotifytop50 {

    public static void main(String[] args) {
        SpotifyAuthenticator authenticator = new SpotifyAuthenticator();
        try {
            SpotifyApi spotifyApi = authenticator.authenticate();
            getTopSongs(spotifyApi);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void getTopSongs(SpotifyApi spotifyApi) {
        String playlistId = "37i9dQZEVXbMDoHDwVN2tF";
        int limit = 50;
        int offset = 0;
        List<String> songUrls = new ArrayList<>();
        List<String> songInfo = new ArrayList<>();

        try {
            PlaylistTrack[] tracks = new PlaylistTrack[50];
            PlaylistTrack[] batch = spotifyApi.getPlaylistsItems(playlistId).limit(limit).offset(offset).build().execute().getItems();
            System.arraycopy(batch, 0, tracks, offset, batch.length);

            Scanner scanner = new Scanner(System.in);
            boolean continueSearch = true;
            while (continueSearch) {
                System.out.println("Enter a number between 1 and 50: ");
                try {
                    int songNumber = scanner.nextInt();

                    if (songNumber >= 1 && songNumber <= 50) {
                        Track track = (Track) tracks[songNumber - 1].getTrack();
                        String songDetails = "The song at position " + songNumber + " is " + track.getName() +
                                             "\nArtist: " + track.getArtists()[0].getName() +
                                             "\nDate Published: " + track.getAlbum().getReleaseDate() +
                                             "\nPlaylist: " + spotifyApi.getPlaylist(playlistId).build().execute().getName();

                        // Get the song's Spotify URL and add it to the list
                        String spotifyUrl = track.getExternalUrls().get("spotify");
                        if (spotifyUrl != null) {
                            songUrls.add(spotifyUrl);
                            songInfo.add(songDetails + "\nURL: " + spotifyUrl);
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number between 1 and 50.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 50.");
                }

                System.out.println("Do you want to search for another song? Enter 'yes' to continue or 'no' to stop.");
                String continueInput = scanner.next();
                continueSearch = continueInput.equalsIgnoreCase("yes");
            }

            scanner.close();

            // After the loop, print the song info and URLs
            System.out.println("Here is the information of the songs you selected:");
            for (String info : songInfo) {
                System.out.println(info);
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
