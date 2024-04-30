package org.ginormous712.Airline;

import lombok.Data;

@Data
public class FlightBrigade {
    private RadioOperator radioOperator;
    private Navigator navigator;
    private Pilot[] pilots;
    private Stewardess[] stewardesses;
}
