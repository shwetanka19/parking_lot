package com.gojek.parking_lot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private Integer size;
    private Integer currentCars;
    private Integer nearest;
    private Car[] cars;

    public ParkingLot(Integer size){
        this.size = size;
        cars = new Car[size];
        nearest = 0;
    }

    public Boolean isFull(){
        return currentCars == size;
    }

    public Integer parkCar(Car car){
        if (currentCars == size){
            return -1;
        }
        cars[nearest] = car;
        currentCars++;

        //Increase nearest
        for (int i=nearest+1;i<size;i++){
            if (cars[i] == null){
                nearest = i;
                break;
            }
        }
        return nearest+1;
    }

    public boolean removeCar(Integer slot){
        if (slot > size || slot <1){
            return false;
        }
        if (cars[slot-1] == null){
            return false;
        }
        cars[slot-1] = null;
        currentCars--;
        if (slot-1 < nearest){
            nearest = slot-1;
        }
        return true;
    }

    public List<ParkedCar> status(){
        List<ParkedCar> parkedCars = new ArrayList<>();
        for (int i=0;i<size;i++){
            if (cars[i] != null){
                parkedCars.add(new ParkedCar(i+1, cars[i]));
            }
        }
        return parkedCars;
    }

    public List<ParkedCar> getCarsByColor(String color){
        List<ParkedCar> parkedCars = new ArrayList<>();
        for (int i=0;i<size;i++){
            if (cars[i] != null && cars[i].getColor().equalsIgnoreCase(color)){
                parkedCars.add(new ParkedCar(i+1, cars[i]));
            }
        }
        return parkedCars;
    }

    public ParkedCar getCarByRegistration(String registrationNo){
        for (int i=0;i<size;i++){
            if (cars[i] != null && cars[i].getRegistrationNo().equalsIgnoreCase(registrationNo)){
                return new ParkedCar(i+1, cars[i]);
            }
        }
        return null;
    }
}
