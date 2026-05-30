package askar.controlwork9.service.impl;

import askar.controlwork9.dto.BookingDto;
import askar.controlwork9.model.Booking;
import askar.controlwork9.model.Ticket;
import askar.controlwork9.model.User;
import askar.controlwork9.repository.BookingRepository;
import askar.controlwork9.repository.TicketRepository;
import askar.controlwork9.repository.UserRepository;
import askar.controlwork9.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void book(Long ticketId, String email) {
        Ticket t = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        if (t.getIsBooked()) throw new RuntimeException("Already booked");

        User u = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Not found"));
        t.setIsBooked(true);
        ticketRepository.save(t);
        Booking b = new Booking();
        b.setUser(u);
        b.setTicket(t);
        b.setBookedAt(LocalDateTime.now());
        bookingRepository.save(b);
        log.info("{} booked ticket {}", email, ticketId);
    }

    @Override
    public List<Booking> getUserBookings(String email) {
        User u = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return bookingRepository.findByUser(u);
    }

    @Override
    public List<BookingDto> getUserBookingsDto(String email) {
        return getUserBookings(email).stream().map(this::toDto).toList();
    }

    public BookingDto toDto(Booking b) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM, HH:mm");
        BookingDto dto = new BookingDto();
        dto.setId(b.getId());
        dto.setFlightNumber(b.getTicket().getFlight().getFlightNumber());
        dto.setDepartureCity(b.getTicket().getFlight().getDepartureCity());
        dto.setArrivalCity(b.getTicket().getFlight().getArrivalCity());
        dto.setDepartureTime(b.getTicket().getFlight().getDepartureTime().format(fmt));
        dto.setSeatNumber(b.getTicket().getSeatNumber());
        dto.setSeatClass(b.getTicket().getSeatClass());
        dto.setPrice(b.getTicket().getPrice());
        dto.setBookedAt(b.getBookedAt().format(fmt));
        return dto;
    }
}
