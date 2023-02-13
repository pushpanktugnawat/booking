package com.statista.code.challenge.booking.domain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.statista.code.challenge.booking.domain.factory.DepartmentFactory;
import com.statista.code.challenge.booking.domain.model.Booking;
import com.statista.code.challenge.booking.port.outbound.BookingRepository;
import com.statista.code.challenge.email.inbound.EmailService;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private DepartmentFactory departmentFactory;

    @Mock
    private EmailService emailService;


    @Test
    void createBooking() {

        when(bookingRepository.save(any())).thenReturn(createBookingResponse());

        var result = bookingService.createBooking(createBookingRequest());

        verify(bookingRepository).save(any());
        Assertions.assertEquals("djfj-383d", result.getBookingId());
    }

    @Test
    void updateBooking() {

        when(bookingRepository.save(any())).thenReturn(createBookingResponse());

        var result = bookingService.updateBooking("test-id",createBookingRequest());

        Assertions.assertEquals("djfj-383d", result.getBookingId());
    }

    @Test
    void findById() {
        ReflectionTestUtils.setField(bookingService, "bookings", Map.of("sales-department",
            Set.of(createBookingResponse())));

        var result = bookingService.findById("djfj-383d");

        assertNotNull(result);
    }

    @Test
    void findAllByDepartment() {
        ReflectionTestUtils.setField(bookingService, "bookings", Map.of("sales-department",
            Set.of(createBookingResponse())));

        var result = bookingService.findAllByDepartment("sales-department");

        assertNotNull(result);
    }


    private Booking createBookingResponse() {
        Booking bookingResponse = new Booking();
        bookingResponse.setCurrency("USD");
        bookingResponse.setBookingId("djfj-383d");
        return bookingResponse;
    }

    private Booking createBookingRequest() {
        Booking booking = new Booking();
        booking.setCurrency("USD");
        booking.setDepartment("sales-department");
        booking.setEmail("test@est.com");
        return booking;
    }
}