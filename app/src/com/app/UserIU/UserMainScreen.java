package com.app.UserIU;

import com.app.DBConnection;
import com.app.UserIU.ControlsUI.AddToPlaylistScreen;
import com.app.UserIU.ControlsUI.RemoveFromPlaylistScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMainScreen extends JFrame {
    Object[] topPlaylistColumns = {"id", "album_id", "genre_id", "artist_id", "name", "release_date",
            "length", "plays"};
    Object[] topPlaylistRows = new Object[8];
    Object[] userPlaylistColumns = {"playlist_id", "song_id", "user_id"};
    Object[] userPlaylistRows = new Object[3];

    private JPanel userMainScreenPanel;
    private JTable topPlaylistTable;
    private JTable userPlaylistTable;
    private JButton followUsersButton;
    private JButton viewFriendSProfilesButton;
    private JButton removeFromPlaylistButton;
    private JButton addToPlaylistButton;
    private JButton userRockButton;
    private JButton userRapButton;
    private JButton userMetalButton;
    private JButton userGeneralButton;
    private JButton topRapButton;
    private JButton topGeneralButton;
    private JButton topRockButton;
    private JButton topMetalButton;


    public UserMainScreen(int ID) {
        add(userMainScreenPanel);
        setSize(1400, 1000);
        setTitle("Music App / User Main");


        followUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FollowUsersScreen followUsersScreen = new FollowUsersScreen(ID);
                followUsersScreen.setVisible(true);
            }
        });

        viewFriendSProfilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewProfilesScreen viewProfilesScreen = new ViewProfilesScreen(ID);
                viewProfilesScreen.setVisible(true);
            }
        });

        removeFromPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveFromPlaylistScreen removeFromPlaylistScreen = new RemoveFromPlaylistScreen(ID);
                removeFromPlaylistScreen.setVisible(true);
            }
        });

        addToPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddToPlaylistScreen addToPlaylistScreen = new AddToPlaylistScreen(ID);
                addToPlaylistScreen.setVisible(true);
            }
        });

        userRapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printUserPlaylist(ID, 1);
            }
        });

        userRockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printUserPlaylist(ID, 2);
            }
        });

        userMetalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printUserPlaylist(ID, 3);
            }
        });

        userGeneralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printUserPlaylist(ID, 4);
            }
        });

        topRapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                printTopSongs(1);
            }
        });

        topRockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                printTopSongs(2);
            }
        });

        topMetalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                printTopSongs(3);
            }
        });

        topGeneralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                printTopSongs(4);
            }
        });
    }

    public void printTopSongs(int genreID) {
        try {
            Connection connection = DBConnection.getConnection();
            String topPlaylistSqlQuery = null;

            topPlaylistTable.setFillsViewportHeight(true);

            if (genreID <= 3) {
                topPlaylistSqlQuery = "SELECT * FROM songs WHERE genre_id = '" + genreID + "'" + "ORDER BY plays  DESC LIMIT 10";
            } else if (genreID == 4) {
                topPlaylistSqlQuery = "SELECT * FROM songs ORDER BY plays  DESC LIMIT 10";
            }

            PreparedStatement topPlaylistStatement = connection.prepareStatement(topPlaylistSqlQuery);
            ResultSet resultSet = topPlaylistStatement.executeQuery();

            DefaultTableModel topPlaylistModel = new DefaultTableModel();
            topPlaylistModel.setColumnIdentifiers(topPlaylistColumns);

            while (resultSet.next()) {
                topPlaylistRows[0] = resultSet.getString("id");
                topPlaylistRows[1] = resultSet.getString("album_id");
                topPlaylistRows[2] = resultSet.getString("genre_id");
                topPlaylistRows[3] = resultSet.getString("artist_id");
                topPlaylistRows[4] = resultSet.getString("name");
                topPlaylistRows[5] = resultSet.getString("release_date");
                topPlaylistRows[6] = resultSet.getString("length");
                topPlaylistRows[7] = resultSet.getString("plays");

                topPlaylistModel.addRow(topPlaylistRows);
                topPlaylistTable.setModel(topPlaylistModel);
            }

            connection.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printUserPlaylist(int userID, int playlistID) {
        try {
            Connection connection = DBConnection.getConnection();
            String userPlaylistSqlQuery = null;

            userPlaylistTable.setFillsViewportHeight(true);

            if (playlistID <= 3) {
                userPlaylistSqlQuery = "SELECT * FROM playlist_songs WHERE playlist_id  = '" + playlistID + "'" +
                        "AND  user_id = '" + userID + "'";
            } else if (playlistID == 4) {
                userPlaylistSqlQuery = "SELECT * FROM playlist_songs WHERE user_id = '" + userID + "'";
            }

            PreparedStatement userPlaylistStatement = connection.prepareStatement(userPlaylistSqlQuery);
            ResultSet resultSet = userPlaylistStatement.executeQuery();

            DefaultTableModel userPlaylistModel = new DefaultTableModel();
            userPlaylistModel.setColumnIdentifiers(userPlaylistColumns);

            while (resultSet.next()) {
                userPlaylistRows[0] = resultSet.getString("playlist_id");
                userPlaylistRows[1] = resultSet.getString("song_id");
                userPlaylistRows[2] = resultSet.getString("user_id");

                userPlaylistModel.addRow(userPlaylistRows);
                userPlaylistTable.setModel(userPlaylistModel);
            }

            connection.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
