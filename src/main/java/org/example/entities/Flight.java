package org.example.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private int id;
    private String origin;
    private String destination;
    private LocalDateTime flightDateTime;
    private Airplane airplane;
    private List<Reserve> reserves = new ArrayList<>();

    public Flight(int id, String origin, String destination, LocalDateTime dateTime, Airplane airplane) {
        if (airplane == null) throw new IllegalArgumentException();
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.flightDateTime = dateTime;
        this.airplane = airplane;
    }

    public int getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDateTime() {
        return flightDateTime;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public int getAvailableVacancies() {
        return airplane.getCapacity() - reserves.size();
    }

    public List<Reserve> getReserves() {
        return reserves;
    }

    public void addReservation(Reserve reserve) {
        reserves.add(reserve);
    }
}
