package askar.controlwork9.service.impl;

import askar.controlwork9.dto.FlightDto;
import askar.controlwork9.dto.TicketDto;
import askar.controlwork9.model.Flight;
import askar.controlwork9.model.Ticket;
import askar.controlwork9.model.User;
import askar.controlwork9.repository.FlightRepository;
import askar.controlwork9.repository.TicketRepository;
import askar.controlwork9.repository.UserRepository;
import askar.controlwork9.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Flight> search(String dep, String arr, String from, String to, int page) {
        LocalDateTime dateFrom = from.isBlank() ? null : LocalDate.parse(from).atStartOfDay();
        LocalDateTime dateTo = to.isBlank() ? null : LocalDate.parse(to).atTime(23, 59);
        return flightRepository.search(dep, arr, dateFrom, dateTo, PageRequest.of(page, 5));
    }

    @Override
    public Page<FlightDto> searchDto(String dep, String arr, String from, String to, int page) {
        return search(dep, arr, from, to, page).map(this::toDto);
    }

    @Override
    public List<Flight> getByCompany(String email) {
        User company = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return flightRepository.findByCompany(company);
    }

    @Override
    public List<FlightDto> getByCompanyDto(String email) {
        return getByCompany(email).stream().map(this::toDto).toList();
    }

    @Override
    public void saveTicket(Flight f, String seat, String cls, BigDecimal price) {
        Ticket t = new Ticket();
        t.setFlight(f);
        t.setSeatNumber(seat);
        t.setSeatClass(cls);
        t.setPrice(price);
        t.setIsBooked(false);
        ticketRepository.save(t);
    }

    @Transactional
    @Override
    public void create(String depCity, String arrCity, String depTime, String arrTime, String companyEmail) {
        User company = userRepository.findByEmail(companyEmail)
                .orElseThrow(() -> new RuntimeException("Not found"));

        Flight f = new Flight();
        f.setFlightNumber(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        f.setDepartureCity(depCity);
        f.setArrivalCity(arrCity);
        f.setDepartureTime(LocalDateTime.parse(depTime));
        f.setArrivalTime(LocalDateTime.parse(arrTime));
        f.setCompany(company);
        flightRepository.save(f);

        saveTicket(f, "1A", "BUSINESS", BigDecimal.valueOf(450));
        saveTicket(f, "1B", "BUSINESS", BigDecimal.valueOf(450));
        saveTicket(f, "1C", "BUSINESS", BigDecimal.valueOf(450));
        saveTicket(f, "10A", "ECONOMY", BigDecimal.valueOf(180));
        saveTicket(f, "10B", "ECONOMY", BigDecimal.valueOf(180));
        saveTicket(f, "10C", "ECONOMY", BigDecimal.valueOf(180));
        saveTicket(f, "11A", "ECONOMY", BigDecimal.valueOf(180));
        saveTicket(f, "11B", "ECONOMY", BigDecimal.valueOf(180));
        saveTicket(f, "11C", "ECONOMY", BigDecimal.valueOf(180));
        saveTicket(f, "12A", "ECONOMY", BigDecimal.valueOf(180));
        log.info("create");
    }

    @Override
    public Flight getById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<Ticket> getTickets(Long flightId) {
        return ticketRepository.findByFlight(getById(flightId));
    }

    @Override
    public List<TicketDto> getTicketsDto(Long flightId) {
        return getTickets(flightId).stream().map(this::toTicketDto).toList();
    }

    @Override
    public long countFree(Flight f) {
        return ticketRepository.countByFlightAndIsBookedFalse(f);
    }

    @Override
    public FlightDto toDto(Flight f) {
        FlightDto dto = new FlightDto();
        dto.setId(f.getId());
        dto.setFlightNumber(f.getFlightNumber());
        dto.setDepartureCity(f.getDepartureCity());
        dto.setArrivalCity(f.getArrivalCity());
        dto.setDepartureTime(f.getDepartureTime().format(DateTimeFormatter.ofPattern("dd MMM, HH:mm")));
        dto.setArrivalTime(f.getArrivalTime().format(DateTimeFormatter.ofPattern("dd MMM, HH:mm")));
        dto.setCompanyName(f.getCompany().getCompanyName());
        dto.setCompanyLogo(f.getCompany().getLogo());
        dto.setFreeSeats(countFree(f));
        return dto;
    }

    public TicketDto toTicketDto(Ticket t) {
        TicketDto dto = new TicketDto();
        dto.setId(t.getId());
        dto.setSeatNumber(t.getSeatNumber());
        dto.setSeatClass(t.getSeatClass());
        dto.setPrice(t.getPrice());
        dto.setBooked(t.getIsBooked());
        return dto;
    }
}
