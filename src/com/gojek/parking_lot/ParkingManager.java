package com.gojek.parking_lot;

import java.util.List;
import java.util.Scanner;

public class ParkingManager {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid command supplied. Usage - create 10");
            return;
        }

        if (!args[0].equalsIgnoreCase("create")) {
            System.out.println("Invalid command supplied. Only create is supported");
            return;
        }

        int size;
        try {
            size = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            System.out.println("Run with proper size");
            return;
        }

        ParkingLot parkingLot = new ParkingLot(size);
        //IParkingLot lot = new CarParkingLot(size);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String cmd = sc.nextLine();

            String[] ops = cmd.split(" ");

            switch (ops[0].toLowerCase()) {
                case "park": {
                    Car car = new Car(ops[1], ops[2]);
                    int slot = parkingLot.parkCar(car);
                    System.out.println("Car parked in slot - " + slot);

                    //lot.park(car);
                    break;
                }
                case "remove": {
                    int slot;
                    boolean res;
                    boolean isSlot = false;
                    try {
                        slot = Integer.parseInt(ops[1]);
                        res = parkingLot.removeCar(slot);
                        if (res) {
                            System.out.println("Car successfully checked out");
                        } else {
                            System.out.println("No car parked for given slot/registration");
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid parking slot.");
                    }
                    break;
                }
                case "status": {
                    List<ParkedCar> parkedCars = parkingLot.status();
                    for (ParkedCar car : parkedCars) {
                        System.out.println(car.getSlot() + " ,  " + car.getCar().getRegistrationNo() + " ,  " + car.getCar().getColor());
                    }
                    break;
                }
                case "getbycolor": {
                    List<ParkedCar> parkedCars = parkingLot.getCarsByColor(ops[1]);
                    for (ParkedCar car : parkedCars) {
                        System.out.println(car.getSlot() + " ,  " + car.getCar().getRegistrationNo() + " ,  " + car.getCar().getColor());
                    }
                    break;
                }
                case "exit":
                    System.out.println("Exiting Parking Lot...");
                    return;
                default:
                    System.out.println("InValid command.");
            }
        }
    }
}