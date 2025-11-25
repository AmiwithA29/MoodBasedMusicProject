package com.moodapp.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import com.moodapp.services.MoodService;
import com.moodapp.model.Song;

/**
 * Servlet responsible for handling mood-based song requests.
 * Receives a mood from the user, fetches related songs using MoodService,
 * and forwards the results to result.jsp for display.
 */
@WebServlet("/mood")
public class MoodServlet extends HttpServlet {

    /**
     * Handles POST requests from the frontend (form submission).
     * Steps:
     *   1. Read the mood selected by the user.
     *   2. Call the service layer to retrieve songs.
     *   3. Attach songs + mood to the request object.
     *   4. Forward the request to result.jsp for rendering.
     *
     * @param req  The HttpServletRequest containing the user-submitted data.
     * @param resp The HttpServletResponse for sending results back to the browser.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Get the mood selected by the user from the form
        String mood = req.getParameter("mood");

        // 2. Call service to fetch songs based on mood
        MoodService service = new MoodService();
        List<Song> songs = service.getSongs(mood);

        // 3. Pass results to JSP as request attributes
        req.setAttribute("songs", songs);
        req.setAttribute("mood", mood);

        // 4. Forward to result.jsp to display the songs
        RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
        rd.forward(req, resp);
    }
}
