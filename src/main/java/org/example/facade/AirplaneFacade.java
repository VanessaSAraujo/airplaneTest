package org.example.facade;

import org.example.entities.Airplane;
import org.example.repositories.AirplaneRepository;

import java.util.List;

public class AirplaneFacade {
    private AirplaneRepository repository = new AirplaneRepository();
    private int airplaneCount = 1;

    public boolean registerPlane (String model, int capacity, String manufacturer) {
        if (capacity <= 0) return false;
        Airplane airplane = new Airplane(airplaneCount++, model, capacity, manufacturer);
        repository.save(airplane);
        return true;
    }

    public List<Airplane> list() {
        return repository.listAll();
    }

    public Airplane findById(int id) {
        return repository.findById(id);
    }

    public AirplaneRepository getRepository() {
        return repository;
    }
}
