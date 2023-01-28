package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        Reservation reservation=reservationRepository2.findById(reservationId).orElse(null);
        if(reservation == null){
            throw new Exception("Reservation not found");
        }
        int bill=reservation.getSpot().getPricePerHour() * reservation.getNumberOfHours();
        if(amountSent<bill){
            throw new Exception("Insufficient Amount");
        }
        PaymentMode paymentMode = null;
        try{
            paymentMode=paymentMode.valueOf(mode.toUpperCase());
        }
        catch(Exception e){
            throw new Exception("Invalid Payment mode");
        }
        Payment payment = new Payment(reservation.getId(),true,paymentMode,reservation);
        paymentRepository2.save(payment);
        return payment;

    }
}
