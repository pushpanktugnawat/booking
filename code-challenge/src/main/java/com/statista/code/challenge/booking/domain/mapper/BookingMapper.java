package com.statista.code.challenge.booking.domain.mapper;

import com.statista.code.challenge.booking.adapter.inbound.BookingDTO;
import com.statista.code.challenge.booking.adapter.inbound.BookingResponseDTO;
import com.statista.code.challenge.booking.domain.model.Booking;
import java.util.Set;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toBooking(BookingDTO source);

    BookingResponseDTO toBookingDTO(Booking destination);

    Set<BookingResponseDTO> toBookingDTOs(Set<Booking> bookingResponse);
}
