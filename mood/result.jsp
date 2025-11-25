<%@ page import="java.util.List" %>
<%@ page import="com.moodapp.model.Song" %>
<%
    // Retrieve the songs attribute set by the servlet
    List<Song> songs = (List<Song>) request.getAttribute("songs");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Song Results</title>
</head>
<body>
<h1>Recommended Songs</h1>
<% if (songs == null || songs.size() == 0) { %>
    <p>No songs found for your mood.</p>
<% } else { 
    for (Song s : songs) { %>
    <p>
        <strong><%= s.getTitle() %></strong> by <%= s.getArtist() %><br>
        Album: <%= s.getAlbum() %> | Source: <%= s.getSource() %><br>
        <!-- Play link -->
        <a href="<%= s.getUrl() %>" target="_blank">Play</a>
    </p>
    <hr>
<% } } %>
</body>
</html>
