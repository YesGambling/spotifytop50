/*
 * this code uses the following third-party libraries:
 * - Spotify Web API Java: A Java wrapper for the Spotify Web API (https://github.com/thelinmichael/spotify-web-api-java)
 * - Apache HttpComponents: A toolset for building HTTP services (https://hc.apache.org/)
 * - Java's built-in Scanner class for user input
 *
 */

// import all necessary spotify stuff 
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;

//import parse execpetion
import org.apache.hc.core5.http.ParseException;

// imports important utils
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class fetches the top 50 songs from a Spotify playlist and allows the user to access information about a song by its position in the playlist.
 */
public class spotifytop50 {

    public static void main(String[] args) {
        SpotifyAuthenticator authenticator = new SpotifyAuthenticator(); // creates new spotify auth
        try {
            SpotifyApi spotifyApi = authenticator.authenticate();  // trys to authetnitcate
            getTopSongs(spotifyApi); //uses the getTopSongs method
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("An error occurred: " + e.getMessage()); // prints an error message if anything goes wrong
        }
    }

    /**
     * Fetches the top 50 songs from a Spotify playlist and allows the user to access information about a song by its position in the playlist.
     * @param spotifyApi The authenticated SpotifyApi instance.
     */
    private static void getTopSongs(SpotifyApi spotifyApi) {
        String playlistId = "37i9dQZEVXbMDoHDwVN2tF"; // This is the ID for Spotify's Top 50 playlist
        int limit = 50; // Maximum number of items to return per request (max is 100)
        int offset = 0; // The index of the first item to return
    
        try {
            PlaylistTrack[] tracks = new PlaylistTrack[50]; //sets the playlistTrack to 50
            PlaylistTrack[] batch = spotifyApi.getPlaylistsItems(playlistId).limit(limit).offset(offset).build().execute().getItems();
            System.arraycopy(batch, 0, tracks, offset, batch.length);
    
            Scanner scanner = new Scanner(System.in); // creates a new scanner for user input
            System.out.println("Enter a number between 1 and 50: ");
            try {
                int songNumber = scanner.nextInt();
    
                if (songNumber >= 1 && songNumber <= 50) {
                    Track track = (Track) tracks[songNumber - 1].getTrack();
                    System.out.println("The song at position " + songNumber + " is " + track.getName());
                    System.out.println("Artist: " + track.getArtists()[0].getName());
                    System.out.println("Date Published: " + track.getAlbum().getReleaseDate());
                    System.out.println("Playlist: " + spotifyApi.getPlaylist(playlistId).build().execute().getName());
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 50.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 50.");
            } finally {
                scanner.close(); // close scanner to preveent memmory leak
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("An error occurred: " + e.getMessage()); // if encoutnered these stuff, print the error message.
        }
    }
}
