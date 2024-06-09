package org.ginormous712.airline.model;

import lombok.Data;

import java.time.LocalDateTime;

//@EqualsAndHashCode(callSuper = true)
@Data
public class Administrator extends Employee{
    private boolean isAdministrator = true;
    public Flight createFlight(String destination, String departure, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        return new Flight(destination, departure, departureTime, arrivalTime);
    }
    /*
    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }

    public void updateFlight(Flight toUpdate, Flight by) {
        int index = flights.indexOf(toUpdate);
        flights.set(index, by);
    }
    */



}
