package com.statista.code.challenge.booking.exception;

public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException(String bookingId) {
        super(String.format("Booking not found for id: %s", bookingId));
    }

}
