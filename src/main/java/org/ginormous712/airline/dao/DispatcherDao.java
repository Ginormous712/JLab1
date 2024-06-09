package org.ginormous712.airline.dao;

import org.ginormous712.airline.model.Dispatcher;
import org.ginormous712.airline.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DispatcherDao {

    public void addDispatcher(Dispatcher dispatcher) {
        String sql = "INSERT INTO managers (first_name, last_name, number, email, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, dispatcher.getFirstName());
            preparedStatement.setString(2, dispatcher.getLastName());
            preparedStatement.setInt(3, dispatcher.getNumber());
            preparedStatement.setString(4, dispatcher.getEmail());
            preparedStatement.setBoolean(5, false); // role is false for Dispatcher
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                dispatcher.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Dispatcher> getDispatchers() {
        List<Dispatcher> dispatchers = new ArrayList<>();
        String sql = "SELECT * FROM managers WHERE role = false";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Dispatcher dispatcher = new Dispatcher();
                dispatcher.setId(resultSet.getInt("id"));
                dispatcher.setFirstName(resultSet.getString("first_name"));
                dispatcher.setLastName(resultSet.getString("last_name"));
                dispatcher.setNumber(resultSet.getInt("number"));
                dispatcher.setEmail(resultSet.getString("email"));
                dispatchers.add(dispatcher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dispatchers;
    }

    public void updateDispatcher(Dispatcher dispatcher) {
        String sql = "UPDATE managers SET first_name = ?, last_name = ?, number = ?, email = ? WHERE id = ? AND role = false";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, dispatcher.getFirstName());
            preparedStatement.setString(2, dispatcher.getLastName());
            preparedStatement.setInt(3, dispatcher.getNumber());
            preparedStatement.setString(4, dispatcher.getEmail());
            preparedStatement.setInt(5, dispatcher.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDispatcher(int id) {
        String sql = "DELETE FROM managers WHERE id = ? AND role = false";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
