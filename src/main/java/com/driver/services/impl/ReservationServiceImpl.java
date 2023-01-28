package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        User user=userRepository3.findById(userId).orElseThrow(()->new Exception("User not found"));

        ParkingLot parkingLot=parkingLotRepository3.findById(parkingLotId).orElseThrow(()->new Exception("Parking Lot not found"));

        Spot spot = spotRepository3.findById(parkingLotId).orElseThrow(()->new Exception("No spot is available"));

        if(spot.getNumberOfWheels()!=numberOfWheels){
            throw new Exception("Spot is not available");
        }

        LocalDateTime endTime=LocalDateTime.now().plusHours(timeInHours);
        Reservation reservation=new Reservation(user,spot,LocalDateTime.now(),endTime);
        reservationRepository3.save(reservation);
        spot.setOccupied(true);
        spotRepository3.save(spot);

        return reservation;
    }
}
