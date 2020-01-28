package com.gojek.parking_lot;

import java.io.Serializable;

public class ParkedCar implements Serializable {
    private Integer slot;
    private Car car;

    public ParkedCar(Integer slot, Car car) {
        this.slot = slot;
        this.car = car;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
