package org.example.facade;


import org.example.entities.*;
import org.example.repositories.*;
import org.example.services.ReserveService;

import java.util.List;

public class ReserveFacade {
    private ReserveRepository reserveRepository = new ReserveRepository();
    private ReserveService reserveService = new ReserveService();
    private PassengerRepository passengerRepository;
    private FlightRepository flightRepository;

    public ReserveFacade(PassengerRepository passengerRepository, FlightRepository flightRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    public boolean makeReservation(int passengerId, int flightId) {
        Passenger passenger = passengerRepository.findById(passengerId);
        Flight flight = flightRepository.findById(flightId);

        if (passenger == null || flight == null) return false;

        try {
            Reserve reserve = reserveService.createReserve(passenger, flight);
            reserveRepository.save(reserve);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar reserva: " + e.getMessage());
            return false;
        }
    }

    public List<Reserve> listAll() {
        return reserveRepository.listAll();
    }
}
