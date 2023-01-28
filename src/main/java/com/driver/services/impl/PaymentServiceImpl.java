package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.model.Spot;
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
        Payment payment = new Payment();
        mode = mode.toUpperCase();
        Reservation reservation1 = reservationRepository2.findById(reservationId).get();

        Spot spot = reservation1.getSpot();
        int numberOfhours = reservation1.getNumberOfHours();
        int bill = numberOfhours*spot.getPricePerHour();
        if(amountSent<bill){
            throw new Exception("Insufficient Amount");
        }
        if(mode != "CASH" && mode != "UPI" && mode != "CASH"){
            throw new Exception("Payment mode not detected");
        }
        spot.setOccupied(false);
        if(mode == "CASH"){
            payment.setPaymentMode(PaymentMode.CARD);
        }
        if(mode == "UPI"){
            payment.setPaymentMode(PaymentMode.UPI);
        }
        if(mode == "CARD"){
            payment.setPaymentMode(PaymentMode.CARD);
        }

        payment.setReservation(reservation1);
        payment.setPaymentCompleted(true);

        reservation1.setPayment(payment);
        reservationRepository2.save(reservation1);//we are saving  reservation no need to save payment

        return payment;

    }
}
