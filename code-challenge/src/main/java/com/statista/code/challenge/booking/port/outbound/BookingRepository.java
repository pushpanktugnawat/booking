package com.statista.code.challenge.booking.port.outbound;

import com.statista.code.challenge.booking.domain.model.Booking;
import java.util.List;

public interface BookingRepository {

    Booking save(Booking booking);

    Booking findById(Long bookingId);

    List<Booking> findAllByDepartment(String department);
}
