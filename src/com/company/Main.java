package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

enum accelerationTypes {KILOMETERPERHOUR, PERCENTAGE};

class OverSpeedException extends Exception {
    public OverSpeedException(String s) {
        super(s);
    }
}

class InvalidOperationException extends Exception {
    public InvalidOperationException(String s) {
        super(s);
    }
}

class InvalidWarehouseTypeException extends Exception {
    public InvalidWarehouseTypeException(String s) {
        super(s);
    }
}

class InvalidInputException extends Exception {
    public InvalidInputException(String s) {
        super(s);
    }
}

public class Main {

    //Function to read inputs from user and create a vehicle config
    public static VehicleConfiguration createVehicleConfig() throws IOException, NumberFormatException, InvalidInputException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the following config details:");
        System.out.println("1. Make: ");
        String make = reader.readLine();
        System.out.println("2. Model: ");
        String model = reader.readLine();
        System.out.println("3. Registration Number: (Eg: TN36BY1234)");
        String registrationNumber = reader.readLine();
        Util.isValidRegistrationNumber(registrationNumber);
        System.out.println("4. Number of seats: ");
        int numberOfSeats = Integer.parseInt(reader.readLine());
        System.out.println("5. Start mode: ");
        String startMode = reader.readLine();
        System.out.println("5. Transmission:");
        String transmission = reader.readLine();
        System.out.println("6. Wheel:");
        String wheel = reader.readLine();
        System.out.println("7. Color:");
        String color = reader.readLine();
        System.out.println("8. Speed Limit:");
        float speedLimit = Float.parseFloat(reader.readLine());
        VehicleConfiguration config = new VehicleConfiguration(make, model, registrationNumber, numberOfSeats, startMode, transmission, wheel, color, speedLimit);

        return  config;
    }

    // Function to add vehicle to the warehouse
    // Input param: warehouse
    public static void addVehicle(VehicleWarehouse warehouse) throws IOException, InvalidInputException, NumberFormatException {
        VehicleConfiguration config = createVehicleConfig();
        ArrayList<Vehicle> filteredVehicles = warehouse.searchVehicles(config.registrationNumber);

        if (filteredVehicles.isEmpty()) {
            Vehicle vehicle = new Vehicle(config);
            warehouse.push(vehicle);
            System.out.println("Vehicle added to the warehouse.");
        } else {
            System.out.println("Vehicle already exists");
        }

    }

    // Function to search vehicle and print it
    // Input param: warehouse, registration number or pattern to match
    public static void searchVehicleAndList(VehicleWarehouse warehouse, String registrationNumber) throws IOException {
        ArrayList<Vehicle> filteredVehicles = warehouse.searchVehicles(registrationNumber);
        boolean isEnded = false;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        if (filteredVehicles.isEmpty()) {
            System.out.println("There are no matching vehicles.");
        } else {
            int index = 1;
            for (Vehicle vehicle: filteredVehicles) {
                System.out.println("VEHICLE" + index +": \n \t VEHICLE CONFIG: ");
                System.out.println(vehicle.config.toString());
                System.out.println("VEHICLE STATE: ");
                System.out.println(vehicle.toString());
            }
        }
    }

    // Function to search vehicle and drive it
    // Input param: warehouse, registration number
    public static void searchVehicleAndDrive(VehicleWarehouse warehouse, String registrationNumber) throws IOException {
        ArrayList<Vehicle> filteredVehicles = warehouse.searchVehicles(registrationNumber);
        boolean isEnded = false;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        if (filteredVehicles.isEmpty() || filteredVehicles.size() > 1) {
            System.out.println("There are no matching vehicles or there is a bug in add vehicle.");
        } else {
            do {
                System.out.println("Please select the operation to be performed: \n 1. Start \n 2. Stop \n 3. Accelerate \n 4. End Drive");
                String operationToBePerformed = reader.readLine();

                try {
                    switch (operationToBePerformed) {
                        case "1":
                            filteredVehicles.get(0).start();
                            break;
                        case "2":
                            filteredVehicles.get(0).stop();
                            break;
                        case "3":
                            System.out.println(
                                    "Please select the operation to be performed: " +
                                            "\n 1. Accelerate by KM " +
                                            "\n 2. Accelerate by percentage"
                            );
                            String accelerationType = reader.readLine();
                            switch (accelerationType) {
                                case "1":
                                    System.out.println("Enter the kilometers:");
                                    float km = Float.parseFloat(reader.readLine());
                                    filteredVehicles.get(0).accelerate(km, accelerationTypes.KILOMETERPERHOUR.name());
                                    break;
                                case "2":
                                    System.out.println("Enter the percentage:");
                                    float percentage = Float.parseFloat(reader.readLine());
                                    filteredVehicles.get(0).accelerate(percentage, accelerationTypes.PERCENTAGE.name());
                                    break;
                                default:
                                    throw new InvalidInputException("Invalid acceleration type selected.");
                            }
                            break;
                        case "4":
                            isEnded = true;
                            break;
                        default:
                            throw new InvalidInputException("Select a correct option");
                    }
                } catch (InvalidInputException | InvalidOperationException | OverSpeedException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            } while (!isEnded);
        }

    }

    // Function to select a warehouse
    // Input param: old and new warehouse
    public static VehicleWarehouse selectWarehouse(VehicleWarehouse newwarehouse, VehicleWarehouse oldwarehouse) throws IOException {
        boolean isWarehouseSelected = false;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        VehicleWarehouse warehouse = null;

        do {
            System.out.println("Please select the warehouse type: \n 1. Old \n 2. New");
            String warehouseType = reader.readLine();

            try {
                switch (warehouseType) {
                    case "1":
                        warehouse = oldwarehouse;
                        isWarehouseSelected = true;
                        break;
                    case "2":
                        warehouse = newwarehouse;
                        isWarehouseSelected = true;
                        break;
                    default:
                        throw new InvalidWarehouseTypeException("Please select the correct warehouse type");
                }
            } catch(InvalidWarehouseTypeException e) {
                System.out.println(e.getMessage());
            }
        } while (!isWarehouseSelected);

        return warehouse;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        VehicleWarehouse oldwarhouse = new VehicleWarehouse();
        VehicleWarehouse newwarhouse = new VehicleWarehouse();

        VehicleWarehouse warehouse = null;
        boolean exited = false;

        do {
            System.out.println(
                    "Please select the operation to be performed: " +
                            "\n 1. Add Vehicle " +
                            "\n 2. Search Vehicle And list " +
                            "\n 3. Search Vehicle and drive" +
                            "\n 4. Exit"
            );
            String operationToBePerformed = reader.readLine();
            System.out.println(operationToBePerformed);

            try {
                switch (operationToBePerformed) {
                    case "1":
                        try {
                            warehouse = selectWarehouse(newwarhouse, oldwarhouse);
                            addVehicle(warehouse);
                        } catch (InvalidInputException e) {
                            System.out.println(e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println(e);
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;
                    case "2":
                        warehouse = selectWarehouse(newwarhouse, oldwarhouse);
                        System.out.println("Enter to registration number or pattern to be searched:");
                        String pattern = reader.readLine();
                        searchVehicleAndList(warehouse, pattern);
                        break;
                    case "3":
                        warehouse = selectWarehouse(newwarhouse, oldwarhouse);
                        System.out.println("Enter to registration number of vehicle to drive");
                        String registrationNumber = reader.readLine();
                        searchVehicleAndDrive(warehouse, registrationNumber);
                        break;
                    case "4":
                        exited = true;
                        break;
                    default:
                        throw new InvalidInputException("Select a correct option");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (!exited);
    }
}
