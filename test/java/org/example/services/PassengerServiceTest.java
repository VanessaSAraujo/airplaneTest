package org.example.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerServiceTest {
    private PassengerService passengerService;

    @BeforeEach
    public void setup() {
        passengerService = new PassengerService();
    }

    @Test
    public void testarAceitarCpfValido() {
        boolean resultado = passengerService.validateCpf("05216102060");
        Assertions.assertTrue(resultado);
    }

    @Test
    public void testarRecusarCpfInvalido() {
        boolean resultado = passengerService.validateCpf("1111af111a1");
        Assertions.assertFalse(resultado);
    }

    @Test
    public void testarAceitarEmailValido() {
        boolean resultado = passengerService.validateEmail("jose@email.com");
        Assertions.assertTrue(resultado);
    }

    @Test
    public void testarRecusarEmailInvalido() {
        boolean resultado = passengerService.validateEmail("email");
        Assertions.assertFalse(resultado);
    }
}
