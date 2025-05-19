package org.example.facades;

import org.example.entities.Passenger;
import org.example.facade.PassengerFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PassengerFacadeTest {
    private PassengerFacade passengerFacade;
    private String nomeValido;
    private String cpfValido;
    private String cpfInvalido;
    private String emailValido;

    @BeforeEach
    public void setup() {
        passengerFacade = new PassengerFacade();
        nomeValido = "Ana Souza";
        cpfValido = "52998224725";
        cpfInvalido = "12345678900";
        emailValido = "ana.souza@email.com";

        passengerFacade.cadastrarPassageiro(nomeValido, cpfValido, emailValido);
    }

    @Test
    public void testarCadastrarPassageiroValido() {
        List<Passenger> passengers = passengerFacade.list();
        Assertions.assertEquals(1, passengers.size());
    }

    @Test
    public void testarCadastrarPassageiroCpfInvalido() {
        boolean resultado = passengerFacade.cadastrarPassageiro("João Silva", cpfInvalido, "joao@email.com");
        List<Passenger> passengers = passengerFacade.list();

        Assertions.assertFalse(resultado);
        Assertions.assertEquals(1, passengers.size());
    }

    @Test
    public void testarListarPassageirosAposCadastros() {
        passengerFacade.cadastrarPassageiro("Maria", "80456229035", "maria@email.com");
        List<Passenger> passengers = passengerFacade.list();

        Assertions.assertEquals(2, passengers.size());
    }
}
