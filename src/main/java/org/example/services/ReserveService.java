package org.example.services;

import org.example.entities.Flight;
import org.example.entities.Passenger;
import org.example.entities.Reserve;

import java.time.LocalDateTime;

public class ReserveService {
    private int reserveCount = 1;

    public Reserve createReserve(Passenger passenger, Flight flight) {
        if (flight.getAvailableVacancies() <= 0) {
            throw new IllegalArgumentException("Voo lotado. Nenhuma vaga disponível.");
        }

        Reserve reserve = new Reserve(reserveCount++, passenger, flight, LocalDateTime.now());
        return reserve;
    }
}
