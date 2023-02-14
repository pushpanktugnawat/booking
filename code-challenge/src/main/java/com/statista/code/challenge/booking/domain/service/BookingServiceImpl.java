package com.statista.code.challenge.booking.domain.service;

import com.statista.code.challenge.booking.domain.factory.DepartmentFactory;
import com.statista.code.challenge.booking.domain.model.Booking;
import com.statista.code.challenge.booking.exception.BookingNotFoundException;
import com.statista.code.challenge.booking.port.inbound.BookingService;
import com.statista.code.challenge.booking.port.outbound.BookingRepository;
import com.statista.code.challenge.email.inbound.EmailService;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final DepartmentFactory departmentFactory;
    private final EmailService emailService;
    private final Map<String, Set<Booking>> bookings = new HashMap<>();

    @Override
    public Booking createBooking(Booking booking) {
        if (Objects.isNull(booking.getBookingId())) {
            booking.setBookingId(UUID.randomUUID().toString());
        }
        var value = bookings.getOrDefault(booking.getDepartment(), new HashSet<>());
        value.add(booking);
        bookings.put(booking.getDepartment(), value);
        CompletableFuture.runAsync(() -> emailService.sendEmail());
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(String bookingId, Booking booking) {
        var value = bookings.getOrDefault(booking.getDepartment(), new HashSet<>());
        var existingBooking = value.
            stream().
            filter(booking1 -> booking1.getBookingId().equalsIgnoreCase(bookingId)).
            findFirst();
        existingBooking.ifPresentOrElse((booking1) -> {
            BeanUtils.copyProperties(booking, booking1);
            booking1.setBookingId(bookingId);
            bookings.put(booking1.getDepartment(), Set.of(booking1));
        }, () -> createBooking(booking));
        return bookingRepository.save(booking);
    }

    @Override
    public Booking findById(String bookingId) {
        var values = values();
        return values.
            stream().
            filter(booking1 -> booking1.getBookingId().equalsIgnoreCase(bookingId)).
            findFirst().orElseThrow(() -> new BookingNotFoundException(bookingId));
    }

    @Override
    public Set<Booking> findAllByDepartment(String department) {
        return bookings.get(department);
    }

    @Override
    public Set<String> findAllCurrencies() {
        var values = values();
        return values.
            stream().
            map(Booking::getCurrency).
            collect(Collectors.toSet());
    }

    @Override
    public Double sumOfCurrency(String currency) {
        var values = values();
        return values.
            stream().
            filter(booking -> booking.getCurrency().equalsIgnoreCase(currency)).
            mapToDouble(Booking::getPrice).
            sum();
    }

    @Override
    public String doBusiness(String bookingId) throws Exception {
        var booking = findById(bookingId);
        if (Objects.nonNull(booking)) {
            var departmentService = departmentFactory.execute(booking.getDepartment());
            return (String) departmentService.doBusiness();
        }
        throw new BookingNotFoundException(bookingId);
    }

    private Set<Booking> values() {
        return bookings.values().stream()
            .flatMap(Collection::stream).collect(Collectors.toSet());
    }
}
