package org.ginormous712.airline.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightCrew{
    final static int PILOTS_NUMBER = 2;
    final static int STEWARDESSES_NUMBER = 2;

    private Pilot[] pilots = new Pilot[PILOTS_NUMBER];
    private Stewardess[] stewardesses = new Stewardess[STEWARDESSES_NUMBER];
    private RadioOperator radioOperator;
    private Navigator navigator;
}
