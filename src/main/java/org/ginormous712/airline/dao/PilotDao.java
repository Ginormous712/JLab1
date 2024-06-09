package org.ginormous712.airline.dao;

import org.ginormous712.airline.model.Pilot;
import org.ginormous712.airline.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotDao {
    public void addPilot(Pilot pilot) {
        String sql = "INSERT INTO pilots (first_name, last_name, number, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pilot.getFirstName());
            preparedStatement.setString(2, pilot.getLastName());
            preparedStatement.setInt(3, pilot.getNumber());
            preparedStatement.setString(4, pilot.getEmail());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                pilot.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pilot> getPilots() {
        List<Pilot> pilots = new ArrayList<>();
        String sql = "SELECT * FROM pilots";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Pilot pilot = new Pilot();
                pilot.setId(resultSet.getInt("id"));
                pilot.setFirstName(resultSet.getString("first_name"));
                pilot.setLastName(resultSet.getString("last_name"));
                pilot.setNumber(resultSet.getInt("number"));
                pilot.setEmail(resultSet.getString("email"));
                pilots.add(pilot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pilots;
    }

    public void updatePilot(Pilot pilot) {
        String sql = "UPDATE pilots SET first_name = ?, last_name = ?, number = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pilot.getFirstName());
            preparedStatement.setString(2, pilot.getLastName());
            preparedStatement.setInt(3, pilot.getNumber());
            preparedStatement.setString(4, pilot.getEmail());
            preparedStatement.setInt(5, pilot.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePilot(int pilotId) {
        String sql = "DELETE FROM pilots WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pilotId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
