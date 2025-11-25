package com.moodapp.dao;

import java.sql.*;
import java.util.*;
import com.moodapp.model.Song;

/**
 * Data Access Object (DAO) for handling database operations related to Song entities.
 * Provides methods for retrieving and saving songs based on mood categories.
 */
public class SongDAO {

    // JDBC URL for connecting to the MySQL database
    private final String URL = "jdbc:mysql://localhost:3306/moodappdb";

    // Database username
    private final String USER = "root";

    // Database password
    private final String PASS = "Samriddh_009";

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return A valid Connection object.
     * @throws Exception If the JDBC driver is not found or connection fails.
     */
    public Connection getConnection() throws Exception {
        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish and return database connection
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * Retrieves all songs associated with a given mood from the database.
     *
     * @param mood The mood category to filter songs by (e.g., "happy", "sad").
     * @return A list of Song objects that match the specified mood.
     */
    public List<Song> getSongsByMood(String mood) {
        List<Song> list = new ArrayList<>();

        try (Connection con = getConnection()) {

            // Prepare SQL query to fetch songs by mood
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM songs WHERE mood = ?"
            );
            ps.setString(1, mood);

            // Execute query and map results to Song objects
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("source"),
                        rs.getString("album"),
                        rs.getString("url")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log exception for debugging
        }

        return list;
    }

    /**
     * Saves a list of songs into the database and assigns each song to a mood category.
     *
     * @param songs A list of Song objects to insert into the database.
     * @param mood  The mood that all songs in the list should be associated with.
     */
    public void saveSongs(List<Song> songs, String mood) {
        try (Connection con = getConnection()) {

            // Insert query with 6 placeholders matching 6 column values
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO songs(title, artist, source, album, url, mood) VALUES (?,?,?,?,?,?)"
            );

            // Add each song as a batch insert
            for (Song s : songs) {
                ps.setString(1, s.getTitle());
                ps.setString(2, s.getArtist());
                ps.setString(3, s.getSource()); // Preview audio
                ps.setString(4, s.getAlbum());
                ps.setString(5, s.getUrl());    // Artwork image
                ps.setString(6, mood);          // Mood classification
                ps.addBatch();
            }

            // Execute all batched insert commands
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace(); // Log exception for debugging
        }
    }
}
