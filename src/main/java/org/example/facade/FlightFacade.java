package org.example.facade;

import org.example.entities.Airplane;
import org.example.entities.Flight;
import org.example.repositories.AirplaneRepository;
import org.example.repositories.FlightRepository;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFacade {
    private FlightRepository flightRepository = new FlightRepository();
    private AirplaneRepository airplaneRepository;
    private int flightCount = 1;

    public FlightFacade(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    public boolean registerFlight(String origin, String destination, LocalDateTime dateTime, int airplaneId) {
        Airplane airplane = airplaneRepository.findById(airplaneId);

        if (airplane == null) return false;

        Flight flight = new Flight(flightCount++, origin, destination, dateTime, airplane);
        flightRepository.save(flight);
        return true;
    }

    public List<Flight> list() {
        return flightRepository.listAll();
    }

    public Flight findById(int id) {
        return flightRepository.findById(id);
    }

    public FlightRepository getRepository() {
        return flightRepository;
    }

}
