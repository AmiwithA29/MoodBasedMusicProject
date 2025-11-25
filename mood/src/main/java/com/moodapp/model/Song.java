package com.moodapp.model;

/**
 * Represents a Song entity used in the MoodApp.
 * Contains metadata such as title, artist, source, album, and URL.
 */
public class Song {

    // Unique identifier for the song
    private int id;

    // Title of the song
    private String title;

    // Artist or performer of the song
    private String artist;

    // Source of the song (e.g., local, YouTube, Spotify)
    private String source;

    // Album the song belongs to
    private String album;

    // URL to access or stream the song
    private String url;

    /**
     * Default constructor.
     */
    public Song() {}

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param id     Unique ID of the song
     * @param title  Title of the song
     * @param artist Artist of the song
     * @param source Source platform or format
     * @param album  Album name
     * @param url    Streaming or resource URL
     */
    public Song(int id, String title, String artist, String source, String album, String url) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.source = source;
        this.album = album;
        this.url = url;
    }

    // Getter for id
    public int getId() { return id; }

    // Getter for title
    public String getTitle() { return title; }

    // Getter for artist
    public String getArtist() { return artist; }

    // Getter for source
    public String getSource() { return source; }

    // Getter for album
    public String getAlbum() { return album; }

    // Getter for URL
    public String getUrl() { return url; }

    // Setter for id
    public void setId(int id) { this.id = id; }

    // Setter for title
    public void setTitle(String title) { this.title = title; }

    // Setter for artist
    public void setArtist(String artist) { this.artist = artist; }

    // Setter for source
    public void setSource(String source) { this.source = source; }

    // Setter for album
    public void setAlbum(String album) { this.album = album; }

    // Setter for URL
    public void setUrl(String url) { this.url = url; }
}
