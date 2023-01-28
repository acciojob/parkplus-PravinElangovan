package com.driver.services.impl;


import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;

import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
        return parkingLotRepository1.save(parkingLot);
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        //check is there parking lot
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).orElse(null);
        if(parkingLot==null){
            return null;
        }
        Spot spot=new Spot();
        spot.setParkingLot(parkingLot);
        spot.setPricePerHour(pricePerHour);
        spot.setNumberOfWheels(numberOfWheels);

        return spotRepository1.save(spot);
    }

    @Override
    public void deleteSpot(int spotId) {
        spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
       ParkingLot parkinglot=parkingLotRepository1.findById(parkingLotId).orElse(null);
       if(parkinglot==null){
           return null;
       }
       Spot spot=spotRepository1.findById(spotId).orElse(null);
       spot.setPricePerHour(pricePerHour);
       return spotRepository1.save(spot);
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
