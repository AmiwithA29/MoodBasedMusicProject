# MoodBasedMusicProject
The project is primarily designed for academic demonstration but allows scalable extension. It highlights practical implementation of JSP, Servlets, DAO pattern, MVC architecture, database concepts, and flow control using Java-based web technologies. 
# 🎵 Mood-Based Music Recommendation Web App  
(Java Servlets + JSP + DAO + MVC)

---

## 📌 Overview
This repository contains a Java-based web application that recommends songs based on user mood. It demonstrates **MVC architecture**, **Servlet lifecycle**, **DAO pattern**, and optional **database/API integration**.  
Designed for **academic project submission with scalable structure**.

---

## 📂 Included for Review
- `src/` – Complete Java source code (Servlet, Service, DAO, Model)  
- `WebContent/` – JSP pages (UI)  
- `WEB-INF/web.xml` – Deployment descriptor  
- `WEB-INF/lib/` – Required JARs (e.g., json-20210307.jar)  
- `project_documentation.pdf` – Theory, Flowchart, UML, ER Diagram  
- `README.md` – (this file)

---

## 🛠 Technologies Used
| Layer | Technology |
|-------|------------|
| Frontend | JSP (`index.jsp`, `result.jsp`) |
| Controller | Java Servlet (`MoodServlet.java`) |
| Business Logic | `MoodService.java` |
| Data Access | `SongDAO.java` |
| Model | `Song.java` |
| Optional DB | MySQL (JDBC Driver) |
| API Parsing | `org.json` |
| Deployment | WAR (Tomcat / Servlet 6.0) |

---

## 📁 Project Structure
  
MoodBasedMusicProject/
├── src/
│   ├── model/
│   │   └── Song.java
│   ├── dao/
│   │   └── SongDAO.java
│   ├── service/
│   │   └── MoodService.java
│   └── servlet/
│       └── MoodServlet.java
│
├── WebContent/
│   ├── index.jsp
│   └── result.jsp
│
└── WEB-INF/
    ├── web.xml
    └── lib/
        └── json-20210307.jar

---

## Flowchart -

+------------------+
|      User        |
+------------------+
          |
          v
+------------------+
|   index.jsp      |
|  (Enter Mood)    |
+------------------+
          |
          v
+------------------+
|   MoodServlet    |
|  (Controller)    |
+------------------+
          |
          v
+------------------+
|   MoodService    |
| (Business Logic) |
+------------------+
          |
          v
+------------------+
|    SongDAO       |
| (Data Access)    |
+------------------+
          |
          v
+------------------+
|     JDBC Layer   |
| (DriverManager / |
|   DataSource)    |
+------------------+
          |
          v
+------------------+
|   Database (SQL) |
|  SONG Table      |
+------------------+
          |
          v
+------------------+
|   result.jsp     |
| (Display Songs)  |
+------------------+
          |
          v
+------------------+
|      User        |
| (Plays Songs)    |
+------------------+


## 💻 Key Code Snippets

### `MoodServlet.java`
```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String mood = req.getParameter("mood");
    List<Song> songs = new MoodService().getSongsForMood(mood);
    req.setAttribute("songs", songs);
    req.getRequestDispatcher("result.jsp").forward(req, resp);
}

        
