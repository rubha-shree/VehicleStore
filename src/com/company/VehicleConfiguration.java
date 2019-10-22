package com.company;

public class VehicleConfiguration {
    String make;
    String model;
    String registrationNumber;
    int numberOfSeats;
    String startMode;
    String transmission;
    String wheel;
    String color;
    float speedLimit;

    public VehicleConfiguration() {
        super();
    }

    public VehicleConfiguration(
            String make,
            String model,
            String registrationNumber,
            int numberOfSeats,
            String startMode,
            String transmission,
            String wheel,
            String color,
            float speedLimit
    ) {
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.numberOfSeats = numberOfSeats;
        this.startMode = startMode;
        this.transmission = transmission;
        this.wheel = wheel;
        this.color = color;
        this.speedLimit = speedLimit;
    }

    public boolean equals(VehicleConfiguration config) {
        if (
                this.make == config.make
                        && this.model == config.model
                        && this.registrationNumber == config.registrationNumber
                        && this.numberOfSeats == config.numberOfSeats
                        && this.startMode == config.startMode
                        && this.transmission == config.transmission
                        && this.wheel == config.wheel
                        && this.color == config.color
                        && this.speedLimit == config.speedLimit
        ) {
            return true;
        }

        return false;
    }

    public String toString() {
        String configString = "";
        configString = configString
                + "Make: " + make + "\n"
                + "Model: " + model + "\n"
                + "Registration Number: " + registrationNumber + "\n"
                + "Number of seats: " + numberOfSeats + "\n"
                + "Start Mode: " + startMode + "\n"
                + "Transmission: " + transmission + "\n"
                + "Wheel: " + wheel + "\n"
                + "Color: " + color + "\n"
                + "speedLimit: " + model;
        return configString;
    }
}
