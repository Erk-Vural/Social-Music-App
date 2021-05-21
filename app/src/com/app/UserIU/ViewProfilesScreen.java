package com.app.UserIU;

import com.app.DBConnection;
import com.app.UserIU.ControlsUI.AddAlbum;
import com.app.UserIU.ControlsUI.AddArtist;
import com.app.UserIU.ControlsUI.AddPlaylist;
import com.app.UserIU.ControlsUI.AddToPlaylistScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewProfilesScreen extends JFrame {
    int userID = 0;
    Object[] premiumUserColumns = {"id", "name", "email", "country"};
    Object[] premiumUserRows = new Object[4];
    Object[] albumColumns = {"id", "album_id", "user_id"};
    Object[] albumRows = new Object[3];
    Object[] artistColumns = {"id", "artist_id", "user_id"};
    Object[] artistRows = new Object[3];
    Object[] userPlaylistColumns = {"id", "genre_id", "playlist_id", "user_id"};
    Object[] userPlaylistRows = new Object[4];
    Object[] playlistColumns = {"playlist_id", "song_id", "user_id"};
    Object[] playlistRows = new Object[3];

    private JPanel viewProfilePanel;
    private JTextField idTextField;
    private JTable premiumUsersTable;
    private JTable songTable;
    private JTable albumTable;
    private JTable artistTable;
    private JTable playlistTable;
    private JButton goButton;
    private JButton rapButton;
    private JButton rockButton;
    private JButton metalButton;
    private JButton generalButton;
    private JButton addSongButton;
    private JButton addArtistButton;
    private JButton addAlbumButton;
    private JButton addPlaylistButton;


    public ViewProfilesScreen(int ID) {
        add(viewProfilePanel);
        setSize(1400, 1000);
        setTitle("Music App / View Profile");
        setVisible(true);

        printPremiumUser();

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = idTextField.getText();
                userID = Integer.parseInt(ID);

                printUserPlaylist();
                printAlbums();
                printArtist();
            }
        });

        rapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printSongs(userID, 1);
            }
        });

        rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printSongs(userID, 2);
            }
        });

        metalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printSongs(userID, 3);
            }
        });

        generalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printSongs(userID, 4);
            }
        });

        addSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddToPlaylistScreen addToPlaylistScreen = new AddToPlaylistScreen(ID);
                addToPlaylistScreen.setVisible(true);
            }
        });

        addAlbumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAlbum addAlbum = new AddAlbum(ID);
                addAlbum.setVisible(true);
            }
        });

        addArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddArtist addArtist = new AddArtist(ID);
                addArtist.setVisible(true);
            }
        });

        addPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPlaylist addPlaylist = new AddPlaylist(ID);
                addPlaylist.setVisible(true);
            }
        });
    }

    public void printPremiumUser() {
        try {
            Connection connection = DBConnection.getConnection();
            String premiumSqlQuery;

            premiumUsersTable.setFillsViewportHeight(true);

            premiumSqlQuery = "SELECT * FROM users WHERE is_premium = true AND is_payed = true";

            PreparedStatement premiumStatement = connection.prepareStatement(premiumSqlQuery);
            ResultSet resultSet = premiumStatement.executeQuery();

            DefaultTableModel premiumModel = new DefaultTableModel();
            premiumModel.setColumnIdentifiers(premiumUserColumns);

            while (resultSet.next()) {
                premiumUserRows[0] = resultSet.getString("id");
                premiumUserRows[1] = resultSet.getString("name");
                premiumUserRows[2] = resultSet.getString("email");
                premiumUserRows[3] = resultSet.getString("country");


                premiumModel.addRow(premiumUserRows);
                premiumUsersTable.setModel(premiumModel);
            }

            connection.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printUserPlaylist() {
        try {
            Connection connection = DBConnection.getConnection();
            String playlistSqlQuery = null;

            playlistTable.setFillsViewportHeight(true);

            playlistSqlQuery = "SELECT * FROM user_playlist WHERE user_id = '" + userID + "'";

            PreparedStatement playListStatement = connection.prepareStatement(playlistSqlQuery);
            ResultSet resultSet = playListStatement.executeQuery();

            DefaultTableModel playlistModel = new DefaultTableModel();
            playlistModel.setColumnIdentifiers(userPlaylistColumns);

            while (resultSet.next()) {
                userPlaylistRows[0] = resultSet.getString("id");
                userPlaylistRows[1] = resultSet.getString("genre_id");
                userPlaylistRows[2] = resultSet.getString("playlist_id");
                userPlaylistRows[3] = resultSet.getString("user_id");


                playlistModel.addRow(userPlaylistRows);
                playlistTable.setModel(playlistModel);
            }

            connection.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printAlbums() {
        try {
            Connection connection = DBConnection.getConnection();
            String albumSqlQuery = null;

            albumTable.setFillsViewportHeight(true);

            albumSqlQuery = "SELECT * FROM user_album WHERE user_id = '" + userID + "'";

            PreparedStatement AlbumStatement = connection.prepareStatement(albumSqlQuery);
            ResultSet resultSet = AlbumStatement.executeQuery();

            DefaultTableModel AlbumModel = new DefaultTableModel();
            AlbumModel.setColumnIdentifiers(albumColumns);

            while (resultSet.next()) {
                albumRows[0] = resultSet.getString("id");
                albumRows[1] = resultSet.getString("album_id");
                albumRows[2] = resultSet.getString("user_id");

                AlbumModel.addRow(albumRows);
                albumTable.setModel(AlbumModel);
            }

            connection.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printArtist() {
        try {
            Connection connection = DBConnection.getConnection();
            String artistSqlQuery = null;

            artistTable.setFillsViewportHeight(true);

            artistSqlQuery = "SELECT * FROM user_artist WHERE user_id = '" + userID + "'";

            PreparedStatement artistStatement = connection.prepareStatement(artistSqlQuery);
            ResultSet resultSet = artistStatement.executeQuery();

            DefaultTableModel ArtistModel = new DefaultTableModel();
            ArtistModel.setColumnIdentifiers(artistColumns);

            while (resultSet.next()) {
                artistRows[0] = resultSet.getString("id");
                artistRows[1] = resultSet.getString("artist_id");
                artistRows[2] = resultSet.getString("user_id");

                ArtistModel.addRow(artistRows);
                artistTable.setModel(ArtistModel);
            }

            connection.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printSongs(int userID, int playlistID) {
        try {
            Connection connection = DBConnection.getConnection();
            String songSqlQuery = null;

            songTable.setFillsViewportHeight(true);

            if (playlistID <= 3) {
                songSqlQuery = "SELECT * FROM playlist_songs WHERE playlist_id  = '" + playlistID + "'" +
                        "AND  user_id = '" + userID + "'";
            } else if (playlistID == 4) {
                songSqlQuery = "SELECT * FROM playlist_songs WHERE user_id = '" + userID + "'";
            }

            PreparedStatement songStatement = connection.prepareStatement(songSqlQuery);
            ResultSet resultSet = songStatement.executeQuery();

            DefaultTableModel Model = new DefaultTableModel();
            Model.setColumnIdentifiers(playlistColumns);

            while (resultSet.next()) {
                playlistRows[0] = resultSet.getString("playlist_id");
                playlistRows[1] = resultSet.getString("song_id");
                playlistRows[2] = resultSet.getString("user_id");

                Model.addRow(playlistRows);
                songTable.setModel(Model);
            }

            connection.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
