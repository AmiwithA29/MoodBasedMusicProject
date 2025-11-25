package com.moodapp.services;

import java.io.*;
import java.net.*;
import java.util.*;
import com.moodapp.model.Song;
import com.moodapp.dao.SongDAO;
import org.json.*;

/**
 * Service class responsible for retrieving songs based on mood.
 * First checks local database, then fetches from the iTunes API if not found.
 */
public class MoodService {

    // DAO to interact with the database
    SongDAO dao = new SongDAO();

    /**
     * Retrieves a list of songs associated with a specific mood.
     * Process:
     *   1. Check database for saved songs.
     *   2. If none exist, fetch from iTunes API.
     *   3. Save fetched songs into DB for future use.
     *
     * @param mood The mood type (e.g., "happy", "sad", "energetic").
     * @return A list of Song objects.
     */
    public List<Song> getSongs(String mood) {

        // 1. Check if songs already exist in the database for this mood
        List<Song> dbSongs = dao.getSongsByMood(mood);
        if (!dbSongs.isEmpty()) return dbSongs;

        // 2. Otherwise fetch songs from iTunes API
        List<Song> apiSongs = new ArrayList<>();

        try {
            // Build iTunes API URL using mood keyword
            String url = "https://itunes.apple.com/search?term=" +
                         URLEncoder.encode(mood + " music", "UTF-8") +
                         "&limit=10&media=music"; // Improved search accuracy

            // Open HTTP GET request
            HttpURLConnection con = (HttpURLConnection) URI.create(url).toURL().openConnection();
            con.setRequestMethod("GET");

            // Read response into a String
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                json.append(line);
            }

            // Parse JSON response from iTunes API
            JSONObject obj = new JSONObject(json.toString());
            JSONArray arr = obj.getJSONArray("results");

            // Extract song details from each JSON result
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);

                String title = o.optString("trackName", "Unknown");
                String artist = o.optString("artistName", "Unknown");
                String source = o.optString("previewUrl", "#");       // 30-second preview audio
                String album = o.optString("collectionName", "Single");
                String artwork = o.optString("artworkUrl100", "");    // Album cover image

                // Create Song object (ID = 0 since DB generates it)
                apiSongs.add(new Song(0, title, artist, source, album, artwork));
            }

            // 3. Save all fetched songs into database for caching
            dao.saveSongs(apiSongs, mood);

        } catch (Exception e) {
            e.printStackTrace(); // Log any API or parsing issues
        }

        // Return songs from API (or DB if earlier returned)
        return apiSongs;
    }
}
