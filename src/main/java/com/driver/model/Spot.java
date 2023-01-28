package com.driver.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private SpotType spotType;
    @Column(nullable = false)
    private int pricePerHour;
    @Column(nullable = false)
    private Boolean isOccupied;
    @ManyToOne
    @JoinColumn
    private ParkingLot parkingLot;
    @OneToMany(mappedBy = "spot",cascade = CascadeType.ALL)
    private List<Reservation> reservationList;
    private int numberOfWheels;
    public Spot() {
    }

    public Spot(int id, SpotType spotType, int pricePerHour, Boolean isOccupied, ParkingLot parkingLot, List<Reservation> reservationList,int numberOfWheels) {
        this.id = id;
        this.spotType = spotType;
        this.pricePerHour = pricePerHour;
        this.isOccupied = isOccupied;
        this.parkingLot = parkingLot;
        this.reservationList = reservationList;
        this.numberOfWheels=numberOfWheels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }
}
