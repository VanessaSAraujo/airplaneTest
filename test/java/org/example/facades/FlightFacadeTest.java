package org.example.facades;

import org.example.entities.Airplane;
import org.example.entities.Flight;
import org.example.facade.AirplaneFacade;
import org.example.facade.FlightFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFacadeTest {
    private AirplaneFacade airplaneFacade;
    private FlightFacade flightFacade;

    private String origem;
    private String destino;
    private LocalDateTime dataVoo;

    @BeforeEach
    public void setUp() {
        airplaneFacade = new AirplaneFacade();
        airplaneFacade.registerPlane("Boeing 737", 150, "Boeing");

        flightFacade = new FlightFacade(airplaneFacade.getRepository());

        origem = "São Paulo";
        destino = "Rio de Janeiro";
        dataVoo = LocalDateTime.now().plusDays(1);
    }

    @Test
    public void testarCadastrarVooValido() {
        Airplane aviao = airplaneFacade.list().get(0);

        boolean resultado = flightFacade.registerFlight(origem, destino, dataVoo, aviao.getId());
        List<Flight> voos = flightFacade.list();

        Assertions.assertTrue(resultado);
        Assertions.assertEquals(1, voos.size());
        Assertions.assertEquals(aviao, voos.get(0).getAirplane());
    }

    @Test
    public void testarCadastrarVooSemAviao() {
        boolean resultado = flightFacade.registerFlight(origem, destino, dataVoo, 999);

        Assertions.assertFalse(resultado);
    }

    @Test
    public void listarVoosAposCadastro() {
        Airplane aviao = airplaneFacade.list().get(0);
        flightFacade.registerFlight("Salvador", "Feira de Santana", LocalDateTime.now().plusDays(2), aviao.getId());

        List<Flight> voos = flightFacade.list();

        Assertions.assertEquals(1, voos.size());
    }
}
