package com.company;

public class Vehicle {
    private boolean isStarted;
    private boolean isStopped;
    private float currentSpeed;
    public VehicleConfiguration config;

    public Vehicle(VehicleConfiguration config) {
        this.isStarted = false;
        this.isStopped = true;
        this.currentSpeed = 0;
        this.config = config;
    }

    public void start() {
        if (!this.isStarted) {
            this.isStarted = true;
            this.isStopped = false;
            System.out.println("Vehicle started successfully.");
        }
        else {
            System.out.println("Vehicle is in start state only.");
        }
    }

    public void stop() {
        if (!this.isStopped) {
            this.isStopped = true;
            this.isStarted = false;
            this.currentSpeed = 0;
            System.out.println("Vehicle stopped successfully.");
        } else {
            System.out.println("Vehicle is in stop state only.");
        }
    }

    public void accelerate(float factor, String accelerationType) throws InvalidOperationException, OverSpeedException {
        if (this.isStarted && accelerationType.equalsIgnoreCase(accelerationTypes.KILOMETERPERHOUR.name())) {
            if ((this.currentSpeed + factor) <= this.config.speedLimit) {
                this.currentSpeed += factor;
                System.out.println("Vehicle accelerated successfully.");
            } else {
                throw new OverSpeedException("Speed of vehicle exceeded the speed limit");
            }
        } else if (this.isStarted && accelerationType.equalsIgnoreCase(accelerationTypes.PERCENTAGE.name())) {
            float accelerateBySpeed = this.currentSpeed * (factor / 100);
            if ((this.currentSpeed + accelerateBySpeed) <= this.config.speedLimit) {
                this.currentSpeed += accelerateBySpeed;
                System.out.println("Vehicle accelerated successfully.");
            } else {
                throw new OverSpeedException("Speed of vehicle exceeded the speed limit");
            }
        } else {
            throw new InvalidOperationException("Attempted to accelerate before starting the vehicle");
        }
    }

    public String toString() {
        String stateString = "";
        stateString = stateString
                + "isStarted: " + isStarted + "\n"
                + "isStopped: " + isStopped + "\n"
                + "Current Speed: " + currentSpeed;
        return stateString;
    }
}
