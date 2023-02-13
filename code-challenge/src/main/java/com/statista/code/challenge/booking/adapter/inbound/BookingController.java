package com.statista.code.challenge.booking.adapter.inbound;

import com.statista.code.challenge.booking.domain.mapper.BookingMapper;
import com.statista.code.challenge.booking.domain.model.Booking;
import com.statista.code.challenge.booking.port.inbound.BookingService;
import java.util.Set;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookingservice/bookings")
@Slf4j
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @PostMapping
    public BookingResponseDTO createBooking(@RequestBody @Valid BookingDTO bookingRequest) {
        Booking booking = bookingMapper.toBooking(bookingRequest);
        Booking bookingResponse = bookingService.createBooking(booking);
        return bookingMapper.toBookingDTO(bookingResponse);
    }

    @PutMapping("/{bookingId}")
    public BookingResponseDTO updateBooking(@PathVariable String bookingId, @RequestBody @Valid BookingDTO bookingRequest) {
        Booking booking = bookingMapper.toBooking(bookingRequest);
        Booking bookingResponse = bookingService.updateBooking(bookingId, booking);
        return bookingMapper.toBookingDTO(bookingResponse);
    }

    @GetMapping("/{bookingId}")
    public BookingResponseDTO getBookingById(@PathVariable String bookingId) {
        Booking bookingResponse = bookingService.findById(bookingId);
        return bookingMapper.toBookingDTO(bookingResponse);
    }

    @GetMapping("/department/{department}")
    public Set<BookingResponseDTO> getBookingsOfType(@PathVariable String department) {
        Set<Booking> bookingResponse = bookingService.findAllByDepartment(department);
        return bookingMapper.toBookingDTOs(bookingResponse);
    }

    @GetMapping("/currencies")
    public Set<String> getCurrencies() {
        return bookingService.findAllCurrencies();
    }

    @GetMapping("/sum/{currency}")
    public Double sumOfCurrency(@PathVariable String currency) {
        return bookingService.sumOfCurrency(currency);
    }

    @GetMapping("/dobusiness/{bookingId}")
    public String doBusiness(@PathVariable String bookingId) throws Exception {
        return bookingService.doBusiness(bookingId);
    }
}
