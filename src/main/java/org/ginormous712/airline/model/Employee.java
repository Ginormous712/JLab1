package org.ginormous712.airline.model;

import lombok.Data;

@Data
public abstract class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int number;
    private String email;
}
