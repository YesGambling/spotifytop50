package com.shivenanddaniel;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import org.apache.hc.core5.http.ParseException;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
//import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class spotifytop50 {
    private static SpotifyApi spotifyApi;
    private static List<String> songUrls = new ArrayList<>();
    private static List<String> songInfo = new ArrayList<>();

    public static void main(String[] args) {
        SpotifyAuthenticator authenticator = new SpotifyAuthenticator();
        try {
            spotifyApi = authenticator.authenticate();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        // Create a new JFrame
        JFrame frame = new JFrame("Spotify Top 50");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        // Create a new JButton
        JButton button = new JButton("Click here to get the top songs on Spotify (This may take a bit)");

        // Add an ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTopSongs();
            }
        });

        // Add the button to the JFrame
        frame.getContentPane().add(button);

        // Make the JFrame visible
        frame.setVisible(true);
    }

    private static void getTopSongs() {
        String playlistId = "37i9dQZEVXbMDoHDwVN2tF";
        int limit = 50;
        int offset = 0;

        try {
            PlaylistTrack[] tracks = new PlaylistTrack[50];
            PlaylistTrack[] batch = spotifyApi.getPlaylistsItems(playlistId).limit(limit).offset(offset).build().execute().getItems();
            System.arraycopy(batch, 0, tracks, offset, batch.length);

            for (int songNumber = 1; songNumber <= 50; songNumber++) {
                Track track = (Track) tracks[songNumber - 1].getTrack();
                String songDetails = "The song at position " + songNumber + " is " + track.getName() +
                                     "\nArtist: " + track.getArtists()[0].getName() +
                                     "\nAlbum: " + track.getAlbum().getName() +
                                     "\nDuration: " + track.getDurationMs() / 1000 + " seconds" +
                                     "\nPopularity: " + track.getPopularity() +
                                     "\nDate Published: " + track.getAlbum().getReleaseDate() +
                                     "\nPlaylist: " + spotifyApi.getPlaylist(playlistId).build().execute().getName();

                // Get the song's Spotify URL and add it to the list
                String spotifyUrl = track.getExternalUrls().get("spotify");
                if (spotifyUrl != null) {
                    songUrls.add(spotifyUrl);
                    songInfo.add(songDetails + "\nURL: " + spotifyUrl);
                }
            }

            // Prompt the user to enter a number
            String input = JOptionPane.showInputDialog("I've searched through the top 50 playlist. Please enter a number 1-50 to see results:");
            int songNumber = Integer.parseInt(input);

            // Display the details of the selected song
            JOptionPane.showMessageDialog(null, songInfo.get(songNumber - 1));

            // Create a yes/no dialog box
            int option = JOptionPane.showConfirmDialog(null, "Do you want to search for another song?", "Continue your search?", JOptionPane.YES_NO_OPTION);

            // If the user clicks 'Yes', call getTopSongs again
            if (option == JOptionPane.YES_OPTION) {
                getTopSongs();
            } else {
                // If the user clicks 'No', display all of the songs with the info collected in the list
                for (String info : songInfo) {
                    System.out.println(info);
                }
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
