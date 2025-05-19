package org.example.entities;

import java.time.LocalDateTime;

public class Reserve {
    private int id;
    private Passenger passenger;
    private Flight flight;
    private LocalDateTime dateReserve;

    public Reserve(int id, Passenger passenger, Flight flight, LocalDateTime dateReserve) {
        if (flight.getAvailableVacancies() <= 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.passenger = passenger;
        this.flight = flight;
        this.dateReserve = dateReserve;
        flight.addReservation(this);
    }

    public int getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public LocalDateTime getDateReserve() {
        return dateReserve;
    }
}
