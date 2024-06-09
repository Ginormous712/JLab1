package org.ginormous712.airline.dao;

import org.ginormous712.airline.model.RadioOperator;
import org.ginormous712.airline.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RadioOperatorDao {
    public void addRadioOperator(RadioOperator radioOperator) {
        String sql = "INSERT INTO radio_operators (first_name, last_name, number, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, radioOperator.getFirstName());
            preparedStatement.setString(2, radioOperator.getLastName());
            preparedStatement.setInt(3, radioOperator.getNumber());
            preparedStatement.setString(4, radioOperator.getEmail());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                radioOperator.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RadioOperator> getRadioOperators() {
        List<RadioOperator> radioOperators = new ArrayList<>();
        String sql = "SELECT * FROM radio_operators";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                RadioOperator radioOperator = new RadioOperator();
                radioOperator.setId(resultSet.getInt("id"));
                radioOperator.setFirstName(resultSet.getString("first_name"));
                radioOperator.setLastName(resultSet.getString("last_name"));
                radioOperator.setNumber(resultSet.getInt("number"));
                radioOperator.setEmail(resultSet.getString("email"));
                radioOperators.add(radioOperator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return radioOperators;
    }

    public void updateRadioOperator(RadioOperator radioOperator) {
        String sql = "UPDATE radio_operators SET first_name = ?, last_name = ?, number = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, radioOperator.getFirstName());
            preparedStatement.setString(2, radioOperator.getLastName());
            preparedStatement.setInt(3, radioOperator.getNumber());
            preparedStatement.setString(4, radioOperator.getEmail());
            preparedStatement.setInt(5, radioOperator.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRadioOperator(int radioOperatorId) {
        String sql = "DELETE FROM radio_operators WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, radioOperatorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
