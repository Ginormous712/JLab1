package org.ginormous712.airline.dao;

import org.ginormous712.airline.model.Navigator;
import org.ginormous712.airline.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NavigatorDao {
    public void addNavigator(Navigator navigator) {
        String sql = "INSERT INTO navigators (first_name, last_name, number, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, navigator.getFirstName());
            preparedStatement.setString(2, navigator.getLastName());
            preparedStatement.setInt(3, navigator.getNumber());
            preparedStatement.setString(4, navigator.getEmail());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                navigator.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Navigator> getNavigators() {
        List<Navigator> navigators = new ArrayList<>();
        String sql = "SELECT * FROM navigators";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Navigator navigator = new Navigator();
                navigator.setId(resultSet.getInt("id"));
                navigator.setFirstName(resultSet.getString("first_name"));
                navigator.setLastName(resultSet.getString("last_name"));
                navigator.setNumber(resultSet.getInt("number"));
                navigator.setEmail(resultSet.getString("email"));
                navigators.add(navigator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return navigators;
    }

    public void updateNavigator(Navigator navigator) {
        String sql = "UPDATE navigators SET first_name = ?, last_name = ?, number = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, navigator.getFirstName());
            preparedStatement.setString(2, navigator.getLastName());
            preparedStatement.setInt(3, navigator.getNumber());
            preparedStatement.setString(4, navigator.getEmail());
            preparedStatement.setInt(5, navigator.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNavigator(int navigatorId) {
        String sql = "DELETE FROM navigators WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, navigatorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
