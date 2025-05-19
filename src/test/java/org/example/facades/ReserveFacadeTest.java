package org.example.facades;

import org.example.entities.Airplane;
import org.example.entities.Flight;
import org.example.entities.Passenger;
import org.example.entities.Reserve;
import org.example.facade.AirplaneFacade;
import org.example.facade.FlightFacade;
import org.example.facade.PassengerFacade;
import org.example.facade.ReserveFacade;
import org.example.repositories.FlightRepository;
import org.example.repositories.PassengerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class ReserveFacadeTest {
    private PassengerRepository passengerRepository;
    private FlightRepository flightRepository;
    private PassengerFacade passengerFacade;
    private AirplaneFacade airplaneFacade;
    private FlightFacade flightFacade;
    private ReserveFacade reserveFacade;

    private Passenger passageiro;
    private Passenger passageiroExtra;
    private Flight voo;

    @BeforeEach
    public void setUp() {
        passengerRepository = new PassengerRepository();
        flightRepository = new FlightRepository();

        passengerFacade = new PassengerFacade();
        airplaneFacade = new AirplaneFacade();
        flightFacade = new FlightFacade(airplaneFacade.getRepository());

        reserveFacade = new ReserveFacade(passengerRepository, flightRepository);

        passengerFacade.cadastrarPassageiro("Ana", "20404866077", "ana@email.com");
        passengerFacade.cadastrarPassageiro("Carlos", "95313879073", "carlos@email.com");

        for (Passenger p : passengerFacade.list()) {
            passengerRepository.save(p);
        }

        List<Passenger> passageiros = passengerRepository.listAll();
        passageiro = passageiros.get(0);
        passageiroExtra = passageiros.get(1);

        airplaneFacade.registerPlane("MiniJet", 1, "Boeing");
        Airplane aviao = airplaneFacade.list().get(0);

        flightFacade.registerFlight("SP", "RJ", LocalDate.now().plusDays(1).atStartOfDay(), aviao.getId());

        for (Flight f : flightFacade.list()) {
            flightRepository.save(f);
        }

        voo = flightRepository.listAll().get(0);
    }

    @Test
    public void criarReservaComVagaDisponivel() {
        boolean resultado = reserveFacade.makeReservation(passageiro.getId(), voo.getId());
        List<Reserve> reservas = reserveFacade.listAll();

        Assertions.assertTrue(resultado);
        Assertions.assertEquals(1, reservas.size());
    }

    @Test
    public void criarReservaQuandoLotado() {
        reserveFacade.makeReservation(passageiro.getId(), voo.getId());

        boolean resultado = reserveFacade.makeReservation(passageiroExtra.getId(), voo.getId());

        Assertions.assertFalse(resultado);
    }

    @Test
    public void listarReservas() {
        reserveFacade.makeReservation(passageiro.getId(), voo.getId());

        List<Reserve> reservas = reserveFacade.listAll();

        Assertions.assertFalse(reservas.isEmpty());
    }
}
