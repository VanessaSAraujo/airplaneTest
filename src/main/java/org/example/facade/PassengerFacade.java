package org.example.facade;

import org.example.entities.Passenger;
import org.example.repositories.PassengerRepository;
import org.example.services.PassengerService;

import java.util.List;

public class PassengerFacade {
    private PassengerRepository repository = new PassengerRepository();
    private PassengerService service = new PassengerService();
    private int passengerCount = 1;

    public boolean cadastrarPassageiro(String name, String cpf, String email) {
        if (!service.validateCpf(cpf) || !service.validateEmail(email)) {
            return false;
        }
        Passenger passenger = new Passenger(passengerCount++, name, cpf, email);
        repository.save(passenger);
        return true;
    }

    public List<Passenger> list() {
        return repository.listAll();
    }

    public Passenger findById(int id) {
        return repository.findById(id);
    }

    public PassengerRepository getRepository() {
        return repository;
    }


}
