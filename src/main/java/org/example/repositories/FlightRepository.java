package org.example.repositories;

import org.example.entities.Flight;
import org.example.entities.Passenger;

import java.util.ArrayList;
import java.util.List;

public class FlightRepository {
    private List<Flight> flights = new ArrayList<>();

    public void save(Flight flight) {
        flights.add(flight);
    }

    public List<Flight> listAll() {
        return flights;
    }
    public Flight findById(int id) {
        return flights.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
