/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectvivianapp.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vivia
 */
public class DatabaseStorage implements DataStorage {
    private String databasePath;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading SQLite JDBC driver: " + e.getMessage());
        }
    }

    public DatabaseStorage(String databasePath) {
        this.databasePath = databasePath;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS notes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "note TEXT NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    @Override
    public void writeData(String note) {
        String insertSQL = "INSERT INTO notes (note) VALUES (?);";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, note);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting note: " + e.getMessage());
        }
    }

    @Override
    public List<String> readData() {
        List<String> notes = new ArrayList<>();
        String selectSQL = "SELECT note FROM notes ORDER BY id DESC;";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                notes.add(rs.getString("note"));
            }
        } catch (SQLException e) {
            System.err.println("Error reading notes: " + e.getMessage());
        }

        return notes;
    }

    @Override
    public void deleteData(String note) {
        String deleteSQL = "DELETE FROM notes WHERE note = ?;";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, note);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting note: " + e.getMessage());
        }
    }
}
