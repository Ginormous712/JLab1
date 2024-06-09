package org.ginormous712.airline.dao;

import org.ginormous712.airline.model.Stewardess;
import org.ginormous712.airline.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StewardessDao {
    public void addStewardess(Stewardess stewardess) {
        String sql = "INSERT INTO stewardesses (first_name, last_name, number, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, stewardess.getFirstName());
            preparedStatement.setString(2, stewardess.getLastName());
            preparedStatement.setInt(3, stewardess.getNumber());
            preparedStatement.setString(4, stewardess.getEmail());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                stewardess.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Stewardess> getStewardesses() {
        List<Stewardess> stewardesses = new ArrayList<>();
        String sql = "SELECT * FROM stewardesses";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Stewardess stewardess = new Stewardess();
                stewardess.setId(resultSet.getInt("id"));
                stewardess.setFirstName(resultSet.getString("first_name"));
                stewardess.setLastName(resultSet.getString("last_name"));
                stewardess.setNumber(resultSet.getInt("number"));
                stewardess.setEmail(resultSet.getString("email"));
                stewardesses.add(stewardess);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stewardesses;
    }

    public void updateStewardess(Stewardess stewardess) {
        String sql = "UPDATE stewardesses SET first_name = ?, last_name = ?, number = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, stewardess.getFirstName());
            preparedStatement.setString(2, stewardess.getLastName());
            preparedStatement.setInt(3, stewardess.getNumber());
            preparedStatement.setString(4, stewardess.getEmail());
            preparedStatement.setInt(5, stewardess.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStewardess(int stewardessId) {
        String sql = "DELETE FROM stewardesses WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, stewardessId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
