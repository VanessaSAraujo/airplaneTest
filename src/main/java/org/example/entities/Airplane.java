package org.example.entities;

public class Airplane {
    private int id;
    private String model;
    private int capacity;
    private String manufacturer;

    public Airplane(int id, String model, int capacity, String manufacturer) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.model = model;
        this.capacity = capacity;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
