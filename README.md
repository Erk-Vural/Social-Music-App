# Social Music App

The Social Music App is a basic Java Swing application with MySQL integration, allowing users to interact with music-related features such as personalized playlists, following other users, and accessing top hits of the week. The app implements user authentication with admin and user roles, as well as distinguishing between premium and normal users.

## Features

- **User Authentication**: Admin and user separation with login functionality.
- **Premium and Normal Users**: Differentiate between premium and normal users with distinct privileges.
- **Follow Other Users**: Users can follow/unfollow other users to stay updated with their activities.
- **Personalized Playlists**: Create and manage personalized playlists.
- **Top Hits of the Week**: Access a curated list of top hits for the week.

## Technologies Used

- Java Swing
- MySQL
- JDBC (Java Database Connectivity)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your_username/social-music-app.git
   ```

2. Set up the MySQL database:
   - Create a MySQL database and import the schema from the provided `database.sql` file.

3. Configure the MySQL connection:
   - Modify the database connection settings in the Java code to match your MySQL database credentials.

4. Compile and run the Java application:
   - Use an IDE like IntelliJ IDEA or Eclipse to open and run the Java Swing application.

## Usage

1. Launch the application.
2. Log in as an admin or a user with appropriate credentials.
3. Explore the available features such as creating playlists, following other users, and accessing top hits of the week.
