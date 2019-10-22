package com.company;

import java.util.ArrayList;
import java.util.regex.*;

public class VehicleWarehouse {
    private ArrayList<Vehicle> vehicles;

    public VehicleWarehouse() {
        this.vehicles = new ArrayList<Vehicle>();
    }

    public void push(Vehicle v) {
        this.vehicles.add(v);
    }

    public ArrayList<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public ArrayList<Vehicle> searchVehicles(String pattern) {
        ArrayList<Vehicle> filteredVehicles = new ArrayList<Vehicle>();

        for (Vehicle vehicle: this.vehicles) {
            if (Pattern.matches(pattern, vehicle.config.registrationNumber)) {
               filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }
}
