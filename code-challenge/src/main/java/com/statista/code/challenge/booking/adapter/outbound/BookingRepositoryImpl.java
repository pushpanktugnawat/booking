package com.statista.code.challenge.booking.adapter.outbound;

import com.statista.code.challenge.booking.domain.model.Booking;
import com.statista.code.challenge.booking.port.outbound.BookingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {

    @Override
    public Booking save(Booking booking) {
        return booking;
    }

    @Override
    public Booking findById(Long bookingId) {
//        return bookingJPARepository.getReferenceById(bookingId);
        return null;
    }

    @Override
    public List<Booking> findAllByDepartment(String department) {
//        return bookingJPARepository.findAllByDepartment(department);
        return null;
    }
}
