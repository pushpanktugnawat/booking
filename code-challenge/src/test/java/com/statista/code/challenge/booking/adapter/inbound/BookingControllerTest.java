package com.statista.code.challenge.booking.adapter.inbound;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.statista.code.challenge.booking.domain.mapper.BookingMapper;
import com.statista.code.challenge.booking.domain.model.Booking;
import com.statista.code.challenge.booking.port.inbound.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @Mock
    private BookingMapper bookingMapper;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    void should_createBooking() throws Exception {
        BookingDTO bookingDTO = createBookingDTO();
        ArgumentCaptor<Booking> argumentCaptor = ArgumentCaptor.forClass(Booking.class);

        //when
        ResultActions result = mockMvc.perform(post("/bookingservice/bookings")
            .content(objectMapper.writeValueAsString(bookingDTO))
            .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(bookingService).createBooking(argumentCaptor.capture());
        result.andExpect(status().isOk());
    }

    private BookingDTO createBookingDTO() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCurrency("USD");
        bookingDTO.setDepartment("sales-department");
        bookingDTO.setEmail("test@est.com");
        return bookingDTO;
    }

    @Test
    void should_updateBooking() throws Exception {
        BookingDTO bookingDTO = createBookingDTO();
        ArgumentCaptor<Booking> argumentCaptor = ArgumentCaptor.forClass(Booking.class);

        //when
        ResultActions result = mockMvc.perform(put("/bookingservice/bookings/d5kd-kldf-rhr-3938")
            .content(objectMapper.writeValueAsString(bookingDTO))
            .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(bookingService).updateBooking(anyString(),argumentCaptor.capture());
        result.andExpect(status().isOk());
    }

    @Test
    void should_fetchBooking() throws Exception {

        //when
        ResultActions result = mockMvc.perform(get("/bookingservice/bookings/d5kd-kldf-rhr-3938")
            .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(bookingService).findById(anyString());
        result.andExpect(status().isOk());
    }

    @Test
    void should_getBookingsOfType() throws Exception {

        //when
        ResultActions result = mockMvc.perform(get("/bookingservice/bookings/department/sales-department")
            .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(bookingService).findAllByDepartment(anyString());
        result.andExpect(status().isOk());
    }

    @Test
    void should_getCurrencies() throws Exception {

        //when
        ResultActions result = mockMvc.perform(get("/bookingservice/bookings/currencies")
            .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(bookingService).findAllCurrencies();
        result.andExpect(status().isOk());
    }

    @Test
    void should_sumOfAllCurrencies() throws Exception {

        //when
        ResultActions result = mockMvc.perform(get("/bookingservice/bookings/sum/USD")
            .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(bookingService).sumOfCurrency(anyString());
        result.andExpect(status().isOk());
    }

    @Test
    void should_doBusiness() throws Exception {

        //when
        ResultActions result = mockMvc.perform(get("/bookingservice/bookings/dobusiness/jsk-jdjd-sdjddh")
            .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(bookingService).doBusiness(anyString());
        result.andExpect(status().isOk());
    }
}