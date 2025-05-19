package org.example.repositories;

import org.example.entities.Airplane;

import java.util.ArrayList;
import java.util.List;

public class AirplaneRepository {
    private List<Airplane> airplanes = new ArrayList<>();

    public void save(Airplane airplane) {
        airplanes.add(airplane);
    }

    public List<Airplane> listAll() {
        return airplanes;
    }

    public Airplane findById(int id) {
        return airplanes.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
