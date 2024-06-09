package org.ginormous712.airline.dao;

import org.ginormous712.airline.model.Administrator;
import org.ginormous712.airline.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDao {

    public void addAdministrator(Administrator administrator) {
        String sql = "INSERT INTO managers (first_name, last_name, number, email, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, administrator.getFirstName());
            preparedStatement.setString(2, administrator.getLastName());
            preparedStatement.setInt(3, administrator.getNumber());
            preparedStatement.setString(4, administrator.getEmail());
            preparedStatement.setBoolean(5, true); // role is true for Administrator
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                administrator.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Administrator> getAdministrators() {
        List<Administrator> administrators = new ArrayList<>();
        String sql = "SELECT * FROM managers WHERE role = true";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Administrator administrator = new Administrator();
                administrator.setId(resultSet.getInt("id"));
                administrator.setFirstName(resultSet.getString("first_name"));
                administrator.setLastName(resultSet.getString("last_name"));
                administrator.setNumber(resultSet.getInt("number"));
                administrator.setEmail(resultSet.getString("email"));
                administrators.add(administrator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrators;
    }

    public void updateAdministrator(Administrator administrator) {
        String sql = "UPDATE managers SET first_name = ?, last_name = ?, number = ?, email = ? WHERE id = ? AND role = true";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, administrator.getFirstName());
            preparedStatement.setString(2, administrator.getLastName());
            preparedStatement.setInt(3, administrator.getNumber());
            preparedStatement.setString(4, administrator.getEmail());
            preparedStatement.setInt(5, administrator.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdministrator(int id) {
        String sql = "DELETE FROM managers WHERE id = ? AND role = true";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
