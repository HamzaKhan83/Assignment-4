package test;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecutorFunctions {
    private CriminalSystem criminalSystem = new CriminalSystem();
    private SquadSystem squadSystem = new SquadSystem();

    public void assignPunishment(int criminalId, String crime) {
        String punishment = getPunishmentForCrime(crime);
        criminalSystem.updateBounty(criminalId, 0);
        System.out.println("Punishment for " + crime + " is: " + punishment);
        System.out.println("Punishment assigned to criminal ID: " + criminalId);
    }

    public void assignCaseToOfficer(int officerId, int reportId) {
        String query = "UPDATE Reports SET status = 'Assigned to Officer ID: " + officerId + "' WHERE report_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reportId);
            preparedStatement.executeUpdate();
            System.out.println("Case assigned to Officer ID: " + officerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void revokeCaseFromOfficer(int reportId) {
        String query = "UPDATE Reports SET status = 'Pending' WHERE report_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reportId);
            preparedStatement.executeUpdate();
            System.out.println("Case revoked and marked as pending.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBountyPunishment(int criminalId, double bounty) {
        criminalSystem.updateBounty(criminalId, bounty);
        System.out.println("Bounty punishment updated to: " + bounty);
    }

    // Removed duplicate createSquad method
    public void createSquad(String squadName) {
        squadSystem.createSquad(squadName);
    }

    public void assignOfficerToSquad(int officerId, int squadId) {
        squadSystem.assignOfficerToSquad(officerId, squadId);
    }

    public void requestSquadJoin(int officerId, String squadName) {
        squadSystem.requestSquadJoin(officerId, squadName);
    }

    private String getPunishmentForCrime(String crime) {
        String punishment = null;
        String query = "SELECT punishment_description FROM Punishments WHERE crime_name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, crime);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                punishment = resultSet.getString("punishment_description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return punishment != null ? punishment : "No punishment found for this crime";
    }
}
