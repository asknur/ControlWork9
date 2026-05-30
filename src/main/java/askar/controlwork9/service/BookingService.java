package askar.controlwork9.service;

import askar.controlwork9.dto.BookingDto;
import askar.controlwork9.model.Booking;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingService {
    @Transactional
    void book(Long ticketId, String email);

    List<Booking> getUserBookings(String email);

    List<BookingDto> getUserBookingsDto(String email);
}
