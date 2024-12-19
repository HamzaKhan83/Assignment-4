package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CriminalSystem {
    public void addCriminal(String name, String crime, double bounty, String imagePath, boolean isImprisoned) {
        String query = "INSERT INTO Criminals (name, crime, bounty, image_path, is_imprisoned) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, crime);
            preparedStatement.setDouble(3, bounty);
            preparedStatement.setString(4, imagePath);
            preparedStatement.setBoolean(5, isImprisoned);
            preparedStatement.executeUpdate();
            System.out.println("Criminal added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllCriminals() {
        String query = "SELECT * FROM Criminals";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Criminal List:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("criminal_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Crime: " + resultSet.getString("crime"));
                System.out.println("Bounty: " + resultSet.getDouble("bounty"));
                System.out.println("Image Path: " + resultSet.getString("image_path"));
                System.out.println("Imprisoned: " + resultSet.getBoolean("is_imprisoned"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBounty(int criminalId, double newBounty) {
        String query = "UPDATE Criminals SET bounty = ? WHERE criminal_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, newBounty);
            preparedStatement.setInt(2, criminalId);
            preparedStatement.executeUpdate();
            System.out.println("Bounty updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
