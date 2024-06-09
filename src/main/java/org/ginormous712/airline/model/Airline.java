package org.ginormous712.airline.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Airline {

    private Administrator administrator;
    private Dispatcher dispatcher;
    private List<Flight> flights;
    private List<Pilot> pilots;
    private List<Stewardess> stewardesses;
    private List<RadioOperator> radioOperators;
    private List<Navigator> navigators;

    public void addEmployee(Employee employee){
        if (employee instanceof Pilot){
            this.pilots.add((Pilot) employee);
        } else if(employee instanceof Stewardess){
            this.stewardesses.add((Stewardess) employee);
        } else if (employee instanceof RadioOperator) {
            this.radioOperators.add((RadioOperator) employee);
        } else if (employee instanceof Navigator) {
            this.navigators.add((Navigator) employee);
        }
    }

    /*try-catch blocks*/
    public void addFlight(String destination, String departure, LocalDateTime departureTime, LocalDateTime arrivalTime){
        Flight flight = new Flight();

        /* Creating flight w/out crew*/
        flight = administrator.createFlight(destination, departure, departureTime, arrivalTime);

        /* Forming Flight Crew and adding it to flight*/
        FlightCrew flightCrew = this.dispatcher.formFlightCrew(this.flights, flight, this.pilots, this.stewardesses, this.radioOperators, this.navigators);
        flight.setFlightCrew(flightCrew);
        flights.add(flight);
    }



    public void addPilot(Pilot pilot){
        this.pilots.add(pilot);
    }

    public void addStewardess(Stewardess stewardess){
        this.stewardesses.add(stewardess);
    }

    public void addRadioOperator(RadioOperator radioOperator){
        this.radioOperators.add(radioOperator);
    }

    public void addNavigator(Navigator navigator){
        this.navigators.add(navigator);
    }

}
