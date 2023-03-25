package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    int typeOfWheeler(SpotType spotType)
    {
        if(spotType.equals(SpotType.TWO_WHEELER))
        {
            return 2;
        }
        else if(spotType.equals(SpotType.FOUR_WHEELER))
        {
            return 4;
        }

        return 5;
    }
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {


        User user;
        try{
            user = userRepository3.findById(userId).get();
        }
        catch(Exception e)
        {
            throw new Exception("Cannot make reservation");
        }
        ParkingLot parkingLot;
        try{
            parkingLot = parkingLotRepository3.findById(parkingLotId).get();
        }
        catch(Exception e)
        {
            throw new Exception("Cannot make reservation");
        }
        List<Spot> spotList = parkingLot.getSpotList();
        int spotId=0;
        int min = Integer.MAX_VALUE;
        for(Spot i:spotList)
        {
            if(i.getOccupied()==false)
            {
               int wheels = typeOfWheeler(i.getSpotType());
               if(wheels>=numberOfWheels)
               {
                   if(min<i.getPricePerHour())
                   {
                       min = i.getPricePerHour();
                       spotId = i.getId();
                   }
               }
            }
        }

        Spot spot;
        try{
            spot = spotRepository3.findById(spotId).get();
        }
        catch(Exception e)
        {
            throw new Exception("Cannot make reservation");
        }

        Reservation reservation = new Reservation();
        reservation.setNumberOfHours(timeInHours);
        reservation.setUser(user);
        reservation.setSpot(spot);
        spot.getReservationList().add(reservation);
        user.getReservationList().add(reservation);

        reservationRepository3.save(reservation);



        return reservation;


    }
}
