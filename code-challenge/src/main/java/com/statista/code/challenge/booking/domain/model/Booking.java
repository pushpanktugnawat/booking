package com.statista.code.challenge.booking.domain.model;

import lombok.Data;

@Data
public class Booking {

    private String bookingId;

    private String description;

    private Double price;

    private String currency;

    private Long subscriptionStartDate;

    private String email;

    private String department;
}
