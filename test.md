package com.shivenanddaniel;

// import the SpotifyAPI classes
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import org.apache.hc.core5.http.ParseException;

// import the gui classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

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

            // call the searchSong method after getting the top 50 songs
            searchSong();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            // print any errors that occur to the console
            System.out.println("Yo, we encountered an error: " + e.getMessage());
        }
    }

    // method to search for a song
    // this method prompts the user to enter a number to search through the results
    // then it displays the details of the selected song in a dialog box
    // finally, it asks the user if they want to search for another song
    // if the user clicks 'Yes', it calls itself recursively
    // if the user clicks 'No', it prints all of the song information collected in the list to the console
    private static void searchSong() {
        String input = JOptionPane.showInputDialog("I've searched through the top fifty songs on Spotify. Please enter a number 1-50 to search through the results:");
        int songNumber = Integer.parseInt(input);

        // create a new JFrame for the song information
        JFrame infoFrame = new JFrame("Song Information");
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoFrame.setSize(500, 200);

        // create a JTextArea to display the song information
        JTextArea textArea = new JTextArea(songInfo.get(songNumber - 1));
        textArea.setEditable(false);

        // create a JButton for the Spotify link
        JButton spotifyButton = new JButton("Listen on Spotify");
        spotifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open the song's Spotify URL in the user's default browser
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(songUrls.get(songNumber - 1)));
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // add the JTextArea and JButton to the JFrame
        infoFrame.getContentPane().add(textArea, BorderLayout.CENTER);
        infoFrame.getContentPane().add(spotifyButton, BorderLayout.SOUTH);

        // make the JFrame visible
        infoFrame.setVisible(true);

        int option = JOptionPane.showConfirmDialog(null, "Do you want to search for another song?", "Continue your search?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            searchSong();
        } else {
            for (String info : songInfo) {
                System.out.println(info);
            }
        }
    }
}
