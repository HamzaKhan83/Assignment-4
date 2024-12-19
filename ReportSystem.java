package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportSystem {

    // Method to create a new report
    public void createReport(String reporterName, String reporterCnic, String crimeDetails, String possibleSuspects) {
        String query = "INSERT INTO Reports (reporter_name, reporter_cnic, crime_details, possible_suspects) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Insert the report into the Reports table
            preparedStatement.setString(1, reporterName);
            preparedStatement.setString(2, reporterCnic);
            preparedStatement.setString(3, crimeDetails);
            preparedStatement.setString(4, possibleSuspects);
            preparedStatement.executeUpdate();
            System.out.println("Report created successfully.");

            // After inserting the report, add the suspects as criminals if they don't already exist
            addSuspectsAsCriminals(possibleSuspects);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add suspects as criminals if they don't already exist
    private void addSuspectsAsCriminals(String possibleSuspects) {
        if (possibleSuspects == null || possibleSuspects.isEmpty()) {
            return; // No suspects to add
        }

        String[] suspects = possibleSuspects.split(",");  // Assuming suspects are comma-separated

        for (String suspect : suspects) {
            suspect = suspect.trim(); // Trim any extra spaces
            if (!isCriminalExists(suspect)) {
                addCriminal(suspect);
            }
        }
    }

    // Method to check if a criminal already exists by name
    private boolean isCriminalExists(String name) {
        String query = "SELECT COUNT(*) FROM Criminals WHERE name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return true; // Criminal already exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Criminal does not exist
    }

    // Method to add a criminal to the database
    private void addCriminal(String name) {
        String crime = "Unknown"; // You may need to modify this based on your requirements
        double bounty = 0.0;
        String imagePath = "Not Available";  // Assuming you don't have an image path initially
        boolean isImprisoned = false;

        String query = "INSERT INTO Criminals (name, crime, bounty, image_path, is_imprisoned) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, crime);
            preparedStatement.setDouble(3, bounty);
            preparedStatement.setString(4, imagePath);
            preparedStatement.setBoolean(5, isImprisoned);
            preparedStatement.executeUpdate();
            System.out.println("Suspect " + name + " added as a criminal.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view all reports
    public void viewAllReports() {
        String query = "SELECT * FROM Reports";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Reports:");
            while (resultSet.next()) {
                System.out.println("Report ID: " + resultSet.getInt("report_id"));
                System.out.println("Reporter Name: " + resultSet.getString("reporter_name"));
                System.out.println("Crime Details: " + resultSet.getString("crime_details"));
                System.out.println("Possible Suspects: " + resultSet.getString("possible_suspects"));
                System.out.println("Status: " + resultSet.getString("status"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


