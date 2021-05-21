CREATE TABLE admin (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	deleted_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

DESCRIBE admin;

SELECT * FROM admin;

INSERT INTO admin(name,email,password) VALUES('admin', 'admin@mail.com','password');

Drop TABLE admin;


CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    is_premium BOOLEAN NOT NULL,
    is_payed BOOLEAN NOT NULL,
    country VARCHAR(20) NOT NULL DEFAULT('default country'),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

DESCRIBE users;

SELECT * FROM users;

INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('normal1','normal1@mail.com','password',FALSE,FALSE,'Turkey');

INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('normal2','normal2@mail.com','password',FALSE,FALSE,'Turkey');

INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('normal3','normal3@mail.com','password',FALSE,FALSE,'Turkey');

INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('normal4','normal4@mail.com','password',FALSE,FALSE,'Turkey');

INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('normal5','normal5@mail.com','password',TRUE,FALSE,'Turkey');


INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('premium1','premium1@mail.com','password',TRUE,TRUE,'Turkey');

INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('premium2','premium2@mail.com','password',TRUE,TRUE,'Turkey');

INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('premium3','premium3@mail.com','password',TRUE,TRUE,'Turkey');

INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('premium4','premium4@mail.com','password',TRUE,TRUE,'Turkey');

INSERT INTO users(name,email,password,is_premium,is_payed,country)
 VALUES('premium5','premium5@mail.com','password',TRUE,TRUE,'Turkey');

Drop TABLE users;


CREATE TABLE user_followers(
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT ,
    follower_id INT ,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY(follower_id) REFERENCES users(id)  ON DELETE CASCADE
);

DESCRIBE user_followers;

SELECT * FROM user_followers;

INSERT INTO user_followers(user_id,follower_id) VALUES(6,1);
INSERT INTO user_followers(user_id,follower_id) VALUES(6,2);
INSERT INTO user_followers(user_id,follower_id) VALUES(6,3);

INSERT INTO user_followers(user_id,follower_id) VALUES(7,4);

INSERT INTO user_followers(user_id,follower_id) VALUES(8,5);

Drop TABLE user_followers;


CREATE TABLE albums(
    id INT NOT NULL AUTO_INCREMENT,
    genre_id INT NULL,
    artist_song_id INT NULL,
    name VARCHAR(30) NOT NULL,
    release_date INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(genre_id) REFERENCES genre(id) ON DELETE CASCADE,
    FOREIGN KEY(artist_song_id) REFERENCES artist_song(id) ON DELETE CASCADE
);

DESCRIBE albums;

SELECT * FROM albums;

INSERT INTO albums(genre_id,artist_song_id,name,release_date) VALUES (1,1, 'Uppercut', 2020);
INSERT INTO albums(genre_id,artist_song_id,name,release_date) VALUES (1,2, 'C-137', 2020);
INSERT INTO albums(genre_id,artist_song_id,name,release_date) VALUES (1,3, 'Microphone Show', 2015);

INSERT INTO albums(genre_id,artist_song_id,name,release_date) VALUES (2,4, 'Californication', 1999);
INSERT INTO albums(genre_id,artist_song_id,name,release_date) VALUES (2,4, 'Stadium Arcadium', 2006);
INSERT INTO albums(genre_id,artist_song_id,name,release_date) VALUES (2,5, 'Saintmoteltelevision', 2016);

INSERT INTO albums(genre_id,artist_song_id,name,release_date) VALUES (3,6, 'Master of Puppets', 1986);
INSERT INTO albums(genre_id,artist_song_id,name,release_date) VALUES (3,7, 'Sehnsucht', 1997);
INSERT INTO albums(genre_id,artist_song_id,name,release_date) VALUES (3,8, 'Ace of Spaces', 1980);

Drop TABLE albums;


CREATE TABLE artists(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    country VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME  NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

DESCRIBE artists;

SELECT * FROM artists;

INSERT INTO artists(name,country) VALUES ('Defkhan','Turkey');
INSERT INTO artists(name,country) VALUES ('Hidra','Turkey');
INSERT INTO artists(name,country) VALUES ('Joker','Turkey');

INSERT INTO artists(name,country) VALUES ('Red Hot Chili Peppers','America');
INSERT INTO artists(name,country) VALUES ('Saint Motel','America');

INSERT INTO artists(name,country) VALUES ('Metallica','America');
INSERT INTO artists(name,country) VALUES ('Rammstein','Germany');
INSERT INTO artists(name,country) VALUES ('Motorhead','America');




Drop TABLE artists;

CREATE TABLE artist_song(
    id INT NOT NULL AUTO_INCREMENT,
    artist1_id INT NOT NULL,
    artist2_id INT NULL,
    artist3_id INT NULL,
    FOREIGN KEY(artist1_id) REFERENCES artists(id) ON DELETE CASCADE,
    FOREIGN KEY(artist2_id) REFERENCES artists(id) ON DELETE CASCADE,
    FOREIGN KEY(artist3_id) REFERENCES artists(id) ON DELETE CASCADE,
    PRIMARY KEY(id)
);

SELECT * FROM artist_song;

INSERT INTO artist_song(artist1_id) VALUE (1);
INSERT INTO artist_song(artist1_id) VALUE (2);
INSERT INTO artist_song(artist1_id) VALUE (3);
INSERT INTO artist_song(artist1_id) VALUE (4);
INSERT INTO artist_song(artist1_id) VALUE (5);
INSERT INTO artist_song(artist1_id) VALUE (6);
INSERT INTO artist_song(artist1_id) VALUE (7);
INSERT INTO artist_song(artist1_id) VALUE (8);


CREATE TABLE genre(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY(id)
);

DESCRIBE genre;

SELECT * FROM genre;

INSERT INTO genre(name) VALUES ('Rap');
INSERT INTO genre(name) VALUES ('Rock');
INSERT INTO genre(name) VALUES ('Metal');

Drop TABLE genre;


CREATE TABLE playlist (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME  NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

DESCRIBE playlist;

SELECT * FROM  playlist;

INSERT INTO playlist(name) VALUES ('Rap');
INSERT INTO playlist(name) VALUES ('Rock');
INSERT INTO playlist(name) VALUES ('Metal');
INSERT INTO playlist(name) VALUES ('Top10 Rap');
INSERT INTO playlist(name) VALUES ('Top10 Rock');
INSERT INTO playlist(name) VALUES ('Top10 Metal');
INSERT INTO playlist(name) VALUES ('Top10 General');


Drop TABLE  playlist;


CREATE TABLE songs (
    id INT NOT NULL AUTO_INCREMENT,
    album_id INT NULL,
    genre_id INT NULL,
    artist_song_id INT NULL,
    name VARCHAR(20) NOT NULL,
    release_date INT NOT NULL,
    lenght FLOAT NOT NULL,
    plays INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(album_id) REFERENCES albums(id) ON DELETE CASCADE,
    FOREIGN KEY(genre_id) REFERENCES genre(id) ON DELETE CASCADE,
    FOREIGN KEY(artist_song_id) REFERENCES artist_song(id) ON DELETE CASCADE   
);

DESCRIBE songs;

SELECT * FROM  songs;

INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (1,1,1,'Sade King', 2020, 3.55, 106889);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (1,1,1,'Tornavida', 2020, 3.21, 68489);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (1,1,1,'Bayrak', 2020, 3.06, 108489);


INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (2,1,2, 'High', 2020, 3.13, 1019621);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (2,1,2, 'Lirikal Ag', 2020, 3.00, 1367681);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (2,1,2, 'Bagirin', 2020, 3.56, 4049259);


INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (3,1,3,'Karistir Kartlari',2015, 3.55, 1068489);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (3,1,3,'Kafana Takma', 2015, 3.34, 155023);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (3,1,3,'Gel Barisalim', 2015, 3.15, 146364);



INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (4,2,4,'Around the World', 1999, 3.58, 101946665);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (4,2,4,'Parallel Universe', 1999, 4.29, 70113549);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (4,2,4,'Scar Tissue', 1999, 3.35, 410750841);



INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (5,2,4,'Dani California',2006, 4.42, 388875435);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (5,2,4,'Snow', 2006, 5.34, 634141024);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (5,2,4,'Charlie', 2006, 4.37, 40671324);


INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (6,2,5,'Move',2016, 3.07, 43735836);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (6,2,5,'Getaway', 2016, 3.01, 8280300);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (6,2,5,'Destroyer', 2016, 3.11, 9451750);



INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (7,3,6,'Master of Puppets',1986, 8.35, 328260361);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (7,3,6,'Battery', 1986, 5.12, 82256626);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (7,3,6,'One', 1986, 6.36, 29061611);


INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (8,3,7,'Sehnsucht', 1997, 4.04, 31461942);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (8,3,7,'Engel', 1997, 4.24, 115254569);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (8,3,7,'Tier', 1997, 3.46, 21780293);


INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (9,3,8,'Ace of Spades', 1980, 2.46, 252606228);
 INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (9,3,8,'King of Kings', 1980, 2.38, 3484432);
INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (9,3,8,'Motorhead', 1980, 3.20, 4871124);

 INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)
 VALUES (9,3,8,'Motorhead1', 1980, 3.20, 4871124);


Drop TABLE  songs;


CREATE TABLE user_album (
    id INT NOT NULL AUTO_INCREMENT,
    album_id INT NULL,
    user_id INT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(album_id) REFERENCES albums(id) ON DELETE CASCADE,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);


DESCRIBE user_album;

SELECT * FROM user_album;

INSERT INTO user_album(album_id, user_id) VALUES (1,1);
INSERT INTO user_album(album_id, user_id) VALUES (2,1);
INSERT INTO user_album(album_id, user_id) VALUES (2,2);
INSERT INTO user_album(album_id, user_id) VALUES (3,3);
INSERT INTO user_album(album_id, user_id) VALUES (3,4);
INSERT INTO user_album(album_id, user_id) VALUES (4,5);

INSERT INTO user_album(album_id, user_id) VALUES (5,6);
INSERT INTO user_album(album_id, user_id) VALUES (4,7);
INSERT INTO user_album(album_id, user_id) VALUES (6,8);
INSERT INTO user_album(album_id, user_id) VALUES (7,9);
INSERT INTO user_album(album_id, user_id) VALUES (8,10);
INSERT INTO user_album(album_id, user_id) VALUES (1,10);
INSERT INTO user_album(album_id, user_id) VALUES (2,10);

Drop TABLE user_album;


CREATE TABLE user_artist (
    id INT NOT NULL AUTO_INCREMENT,
    artist_song_id INT NULL,
    user_id INT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(artist_song_id) REFERENCES artist_song(id) ON DELETE CASCADE,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE 
);


DESCRIBE user_artist;

SELECT * FROM user_artist;

INSERT INTO user_artist(artist_song_id, user_id) VALUES (1,1);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (7,1);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (6,2);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (5,3);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (3,4);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (4,5);

INSERT INTO user_artist(artist_song_id, user_id) VALUES (3,6);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (2,7);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (5,8);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (4,9);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (5,10);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (6,10);
INSERT INTO user_artist(artist_song_id, user_id) VALUES (7,10);

Drop TABLE user_artist;


CREATE TABLE user_playlist (
    id INT NOT NULL AUTO_INCREMENT,
    genre_id INT NULL,
    playlist_id INT NULL,
    user_id INT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(genre_id) REFERENCES genre(id) ON DELETE CASCADE,
    FOREIGN KEY(playlist_id) REFERENCES playlist(id) ON DELETE CASCADE,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE 
);


DESCRIBE user_playlist;

SELECT * FROM user_playlist;

INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,1);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,1);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,1);

INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,2);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,2);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,2);

INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,3);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,3);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,3);

INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,4);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,4);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,4);

INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,5);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,5);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,5);


INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,6);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,6);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,6);

INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,7);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,7);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,7);

INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,8);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,8);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,8);

INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,9);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,9);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,9);

INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (1,1,10);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (2,2,10);
INSERT INTO user_playlist(genre_id, playlist_id, user_id) VALUES (3,3,10);

Drop TABLE user_playlist;

CREATE TABLE playlist_songs (
    id INT NOT NULL AUTO_INCREMENT,
    playlist_id INT NULL,
    song_id INT NULL,
    user_id INT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(playlist_id) REFERENCES playlist(id) ON DELETE CASCADE,
    FOREIGN KEY(song_id) REFERENCES songs(id) ON DELETE CASCADE,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE 
);



DESCRIBE playlist_songs;

SELECT * FROM playlist_songs;

INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,1,1);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,4,1);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,7,1);

INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,2,2);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,5,2);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,8,2);

INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,3,3);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,6,3);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,9,3);

INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,1,4);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,4,4);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,7,4);

INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,2,5);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,5,5);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,8,5);


INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,3,6);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,6,6);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,9,6);

INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,1,7);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,4,7);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,7,7);

INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,2,8);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,5,8);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,8,8);

INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,3,9);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,6,9);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,9,9);

INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (1,1,10);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (2,4,10);
INSERT INTO playlist_songs(playlist_id, song_id, user_id) VALUES (3,7,10);

Drop TABLE playlist_songs;

SET GLOBAL FOREIGN_KEY_CHECKS=0