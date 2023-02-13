package com.statista.code.challenge.booking.adapter.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import lombok.Data;

@Data
public class BookingDTO {

    private String description;

    private String currency;

    private Double price;

    @JsonProperty("subscription_start_date")
    private Long subscriptionStartDate;

    @Email
    private String email;

    private String department;
}
