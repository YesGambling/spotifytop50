package com.shivenanddaniel;

// import the SpotifyAPI classes
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import org.apache.hc.core5.http.ParseException;

// import the gui classes
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import the IO and utility classes
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class spotifytop50 {

    // Spotify API instance
    private static SpotifyApi spotifyApi;
    
    // list to store song URLs
    private static List<String> songUrls = new ArrayList<>();
    
    // list to store song information
    private static List<String> songInfo = new ArrayList<>();

    public static void main(String[] args) {
    
        // create a SpotifyAuthenticator instance
        SpotifyAuthenticator authenticator = new SpotifyAuthenticator();
        
        // try to authenticate with Spotify
        try {
            spotifyApi = authenticator.authenticate();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            // print any errors that occur during authentication process
            System.out.println("Whoops! An error occured: " + e.getMessage());
        }

        // create a new JFrame with the title "Spotify Top 50"
        JFrame frame = new JFrame("Spotify Top 50");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        // create a new JButton with a label
        JButton button = new JButton("Click here to get the top songs on Spotify (This may take a bit)");

        // add an ActionListener to the button that calls the getTopSongs method when clicked
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTopSongs();
            }
        });

        // add the button to the JFrame
        frame.getContentPane().add(button);

        // make the JFrame visible
        frame.setVisible(true);
    }

    // method to get the top 50 songs from Spotify
    private static void getTopSongs() {
        
        // ID of the Spotify Top 50 playlist
        String playlistId = "37i9dQZEVXbMDoHDwVN2tF";
        
        // limit the number of songs to 50
        int limit = 50;
        
        // start at the first song
        int offset = 0;

        // try to get the top 50 songs from the playlist
        try {
            PlaylistTrack[] tracks = new PlaylistTrack[50];
            PlaylistTrack[] batch = spotifyApi.getPlaylistsItems(playlistId).limit(limit).offset(offset).build().execute().getItems();
            System.arraycopy(batch, 0, tracks, offset, batch.length);

            // loop through each song and get its details
            for (int songNumber = 1; songNumber <= 50; songNumber++) {
                Track track = (Track) tracks[songNumber - 1].getTrack();
                String songDetails = "The song at position " + songNumber + " is " + track.getName() +
                                     "\nArtist: " + track.getArtists()[0].getName() +
                                     "\nAlbum: " + track.getAlbum().getName() +
                                     "\nDuration: " + track.getDurationMs() / 1000 + " seconds" +
                                     "\nPopularity: " + track.getPopularity() +
                                     "\nDate Published: " + track.getAlbum().getReleaseDate() +
                                     "\nPlaylist: " + spotifyApi.getPlaylist(playlistId).build().execute().getName();

                // get the song's Spotify URL and add it to the list
                String spotifyUrl = track.getExternalUrls().get("spotify");
                if (spotifyUrl != null) {
                    songUrls.add(spotifyUrl);
                    songInfo.add(songDetails + "\nURL: " + spotifyUrl);
                }
            }

            // prompt the user to enter a number to search through the results
            String input = JOptionPane.showInputDialog("I've searched through the top fifty songs on Spotify. Please enter a number 1-50 to search through the  results:");
            
            // parse the user's input to an integer
            int songNumber = Integer.parseInt(input);

            // display the details of the selected song in a dialog box
            JOptionPane.showMessageDialog(null, songInfo.get(songNumber - 1));

            // create a yes/no dialog box asking the user if they want to search for another song
            int option = JOptionPane.showConfirmDialog(null, "Do you want to search for another song?", "Continue your search?", JOptionPane.YES_NO_OPTION);

            // if the user clicks 'Yes', call to the getTopSongs method again
            if (option == JOptionPane.YES_OPTION) {
                getTopSongs();
            } else {
                // if the user clicks 'No', print all of the song information collected in the list to the console
                for (String info : songInfo) {
                    System.out.println(info);
                }
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            // print any errors that occur to the console
            System.out.println("Yo, we encountered an error: " + e.getMessage());
        }
    }
}
