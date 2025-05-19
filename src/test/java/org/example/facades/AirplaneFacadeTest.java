package org.example.facades;

import org.example.entities.Airplane;
import org.example.facade.AirplaneFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AirplaneFacadeTest {
    private AirplaneFacade airplaneFacade;
    private String modeloValido;
    private int capacidadeValida;
    private int capacidadeZero;
    private int capacidadeNegativa;
    private String fabricante;

    @BeforeEach
    public void setUp() {
        airplaneFacade = new AirplaneFacade();

        modeloValido = "Boeing 737";
        capacidadeValida = 150;
        capacidadeZero = 0;
        capacidadeNegativa = -5;
        fabricante = "Boeing";

        airplaneFacade.registerPlane(modeloValido, capacidadeValida, fabricante);
    }

    @Test
    public void cadastrarAviaoValido() {
        List<Airplane> aviões = airplaneFacade.list();

        Assertions.assertEquals(1, aviões.size());
    }

    @Test
    public void cadastrarAviaoComCapacidadeZero() {
        boolean resultado = airplaneFacade.registerPlane("Airbus A320", capacidadeZero, "Airbus");

        Assertions.assertFalse(resultado);
    }

    @Test
    public void cadastrarAviaoComCapacidadeNegativa() {
        boolean resultado = airplaneFacade.registerPlane("Cessna 172", capacidadeNegativa, "Cessna");

        Assertions.assertFalse(resultado);
    }

    @Test
    public void listarAvioesAposCadastros() {
        airplaneFacade.registerPlane("Embraer E190", 100, "Embraer");

        List<Airplane> aviões = airplaneFacade.list();

        Assertions.assertEquals(2, aviões.size());
    }

}
