package org.ginormous712.airline.dao;

import org.ginormous712.airline.model.Flight;
import org.ginormous712.airline.util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightDao {
    public void addFlight(Flight flight) {
        String sql = "INSERT INTO flights (destination, departure, departure_time, arrival_time) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, flight.getDestination());
            preparedStatement.setString(2, flight.getDeparture());
            preparedStatement.setObject(3, flight.getDepartureTime());
            preparedStatement.setObject(4, flight.getArrivalTime());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                flight.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> getFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Flight flight = new Flight();
                flight.setId(resultSet.getInt("id"));
                flight.setDestination(resultSet.getString("destination"));
                flight.setDeparture(resultSet.getString("departure"));
                flight.setDepartureTime(resultSet.getObject("departure_time", LocalDateTime.class));
                flight.setArrivalTime(resultSet.getObject("arrival_time", LocalDateTime.class));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public void updateFlight(Flight flight) {
        String sql = "UPDATE flights SET destination = ?, departure = ?, departure_time = ?, arrival_time = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, flight.getDestination());
            preparedStatement.setString(2, flight.getDeparture());
            preparedStatement.setObject(3, flight.getDepartureTime());
            preparedStatement.setObject(4, flight.getArrivalTime());
            preparedStatement.setInt(5, flight.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlight(int flightId) {
        String sql = "DELETE FROM flights WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, flightId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
