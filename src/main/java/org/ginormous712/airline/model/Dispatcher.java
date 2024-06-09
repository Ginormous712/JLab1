package org.ginormous712.airline.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Dispatcher extends Employee {
    private boolean isAdministrator = false;
    public FlightCrew formFlightCrew(final List<Flight> flights, Flight flight, List<Pilot> pilots, List<Stewardess> stewardesses,
                               List<RadioOperator> radioOperators, List<Navigator> navigators) {
        FlightCrew flightCrew = new FlightCrew();

        List<Flight> sameTimeFlights = new ArrayList<>();
        LocalDateTime start = flight.getDepartureTime();
        LocalDateTime end = flight.getArrivalTime();

        for (Flight checkedFlight : flights) {
            if ((checkedFlight.getDepartureTime().isAfter(start) || checkedFlight.getDepartureTime().isEqual(start)) &&
                    (checkedFlight.getDepartureTime().isBefore(end) || checkedFlight.getDepartureTime().isEqual(end))) {
                sameTimeFlights.add(checkedFlight);
                continue;
            }
            if ((checkedFlight.getArrivalTime().isAfter(start) || checkedFlight.getArrivalTime().isEqual(start)) &&
                    (checkedFlight.getArrivalTime().isBefore(end) || checkedFlight.getArrivalTime().isEqual(end))) {
                sameTimeFlights.add(checkedFlight);
            }
        }
        int pilotsCounter = 0;
        int stewardessesCounter = 0;

        List<Pilot> busyPilots = new LinkedList<>();
        List<Stewardess> busyStewardesses = new LinkedList<>();
        List<RadioOperator> busyRadioOperators = new LinkedList<>();
        List<Navigator> busyNavigators = new LinkedList<>();

        for (Flight checkedFlight : sameTimeFlights) {
            while (pilotsCounter < FlightCrew.PILOTS_NUMBER) {
                busyPilots.add(checkedFlight.getFlightCrew().getPilots()[pilotsCounter]);
                pilotsCounter++;
            }
            while (stewardessesCounter < FlightCrew.PILOTS_NUMBER) {
                busyStewardesses.add(checkedFlight.getFlightCrew().getStewardesses()[stewardessesCounter]);
                stewardessesCounter++;
            }
            busyRadioOperators.add(checkedFlight.getFlightCrew().getRadioOperator());
            busyNavigators.add(checkedFlight.getFlightCrew().getNavigator());
        }

        pilotsCounter = 0;
        stewardessesCounter = 0;
        for (Pilot pilot : pilots){
            if (pilotsCounter >= FlightCrew.PILOTS_NUMBER){
                break;
            }
            if(busyPilots.contains(pilot)){
                continue;
            }
            flightCrew.getPilots()[pilotsCounter] = pilot;
            pilotsCounter++;

        }
        for (Stewardess stewardess : stewardesses){
            if (stewardessesCounter >= FlightCrew.STEWARDESSES_NUMBER){
                break;
            }
            if(busyStewardesses.contains(stewardess)){
                continue;
            }
            flightCrew.getStewardesses()[stewardessesCounter] = stewardess;
            stewardessesCounter++;

        }
        for (RadioOperator radioOperator : radioOperators){
            if(busyRadioOperators.contains(radioOperator)){
                continue;
            }
            flightCrew.setRadioOperator(radioOperator);
            break;
        }
        for (Navigator navigator : navigators){
            if(busyNavigators.contains(navigator)){
                continue;
            }
            flightCrew.setNavigator(navigator);
            break;
        }
        return flightCrew;
    }
}
