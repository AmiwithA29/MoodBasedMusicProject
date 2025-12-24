# MoodBasedMusicProject
The project is primarily designed for academic demonstration but allows scalable extension. It highlights practical implementation of JSP, JDBC, Servlets, DAO pattern, MVC architecture, database concepts, and flow control using Java-based web technologies. 
# 🎵 Mood-Based Music Recommendation Web App  
(Java Servlets + JSP + JDBC + MYSQl + DAO + MVC)

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

## OOPs (Object-Oriented Programming)
- Song.java is a POJO class using encapsulation with private variables and public getters/setters.
- Inheritance is applied as MoodServlet extends HttpServlet.
- Abstraction is achieved through separate layers (Controller, Service, DAO).
- Method overriding is used in doPost() from HttpServlet.
- Classes are modular and reusable, following clean object-oriented design.

## Multithreading in Servlets
- Servlets are inherently multithreaded; each HTTP request is processed in a separate thread.
- MoodServlet handles multiple user requests simultaneously.
- Only request-scoped variables are used, ensuring thread safety.
- No shared mutable state is maintained across requests.

## Java Collection Framework
- List<Song> is used to store multiple songs retrieved from DAO.
- ArrayList is preferred due to its fast traversal and dynamic resizing.
- Collections are used while iterating in DAO and while displaying data in JSP.
- Batch insertion into database also utilizes collections for efficient handling.


## 💻 Key Code Snippets

## 🗄️ Database & JDBC Connectivity (Backend Layer)

The application uses **MySQL database** and **JDBC (Java Database Connectivity)** to fetch songs based on user mood.

### 🔹 Database Structure

```
-- Create database for mood based music app
CREATE DATABASE IF NOT EXISTS mood_music_db;

-- Select database
USE mood_music_db;

-- Create SONG table
CREATE TABLE SONG (
    id INT PRIMARY KEY AUTO_INCREMENT,      -- unique song id
    title VARCHAR(100) NOT NULL,            -- song title
    artist VARCHAR(100) NOT NULL,           -- artist name
    album VARCHAR(100),                     -- album name (optional)
    mood VARCHAR(50) NOT NULL,              -- mood category
    url VARCHAR(255) NOT NULL               -- song url
);

```

## SongDAO.java

```Code
public class SongDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/moodappdb";
    private static final String USER = "root";
    private static final String PASS = "Samriddh_009";

    // Establish database connection using JDBC
    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
```
### Servlet
 MoodServlet acts as the Controller in the MVC architecture.
 It handles HTTP POST requests triggered when the user submits mood from index.jsp.
 It calls MoodService to apply business logic and retrieve songs based on the mood.
 The processed data is forwarded to result.jsp, which acts as the View in MVC.
 @WebServlet("/mood") maps this servlet to the URL path "/mood".
 RequestDispatcher.forward() transfers data to JSP without changing the URL.

### 1️⃣ Core Feature Implementation

Implemented Features

User Mood Input (JSP)
The application captures user mood through a JSP-based interface (index.jsp) using an HTML form.

Request Handling (Servlet Controller)
User requests are processed by MoodServlet, which acts as the Controller in the MVC architecture and handles HTTP POST requests.

Business Logic Layer (Service)
All mood-based decision-making and validation logic is implemented inside MoodService, ensuring separation of concerns.

Database Interaction (DAO Pattern)
SongDAO manages all database operations using JDBC, following the DAO pattern to isolate data access logic.

Dynamic Result Rendering (JSP View)
Recommended songs are dynamically displayed to the user through result.jsp using server-side rendering.


### 2️⃣ Error Handling & Robustness
Server-Side Error Handling

Null and empty mood validation

Safe handling of empty database results

Exception handling during JDBC operations

```code -
if (mood == null || mood.trim().isEmpty()) {
    return new ArrayList<>();
}
 ```
### Database Safety
PreparedStatement used

SQL Injection prevention

Controlled exception propagation

### 3️⃣ Integration of Components
Layer Integration
Layer	Responsibility
JSP	User Interface
Servlet	Controller
Service	Business Logic
DAO	Data Access
Model	Data Representation


### 4️⃣ Event Handling & Request Processing
Event Flow

HTML form submit (POST request)

Servlet doPost() execution

Request forwarding using RequestDispatcher

Server-side rendering

### 5️⃣ Data Validation
Client-Side

Required form fields

Controlled input options (mood)

Server-Side (Primary)

Validation inside Service layer

Defensive programming approach


# 📌 Summary

The Mood-Based Music Recommendation System is a Java-based web application built using JSP, Servlets, JDBC, and the DAO pattern, following the MVC architecture. The application enables users to select a mood, processes the request through a well-structured backend, and retrieves relevant song recommendations from a database.

The project emphasizes modular design, separation of concerns, and server-side rendering, ensuring maintainable and scalable code. It demonstrates practical implementation of Java web technologies, database connectivity, and clean application flow suitable for academic and real-world use.





        
