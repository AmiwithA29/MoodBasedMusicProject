<!-- index.jsp - Main page with mood input form -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find songs for your mood</title>
</head>
<body>
    <h1>Enter your mood</h1>
    <!-- Form posts mood to the servlet -->
    <form action="MoodServlet" method="post">
        <label for="mood">Mood:</label>
        <input type="text" id="mood" name="mood" required>
        <button type="submit">Get Songs</button>
    </form>
</body>
</html>
