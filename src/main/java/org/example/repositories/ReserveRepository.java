package org.example.repositories;

import org.example.entities.Reserve;

import java.util.ArrayList;
import java.util.List;

public class ReserveRepository {
    private List<Reserve> reserves = new ArrayList<>();

    public void save(Reserve reserve) {
        reserves.add(reserve);
    }

    public List<Reserve> listAll() {
        return reserves;
    }
}
