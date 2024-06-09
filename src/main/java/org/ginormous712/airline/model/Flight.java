package org.ginormous712.airline.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private int id;
    private FlightCrew flightCrew;
    private String destination;
    private String departure;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    public Flight(String destination, String departure, LocalDateTime departureTime, LocalDateTime arrivalTime){
        this.destination = destination;
        this.departure = departure;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
