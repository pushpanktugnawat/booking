package com.statista.code.challenge.booking.port.inbound;

import com.statista.code.challenge.booking.domain.model.Booking;
import java.util.Set;

public interface BookingService {

    Booking createBooking(Booking booking);

    Booking updateBooking(String bookingId, Booking booking);

    Booking findById(String bookingId);

    Set<Booking> findAllByDepartment(String department);

    Set<String> findAllCurrencies();

    Double sumOfCurrency(String currency);

    String doBusiness(String bookingId) throws Exception;
}
