package askar.controlwork9.service;

import askar.controlwork9.dto.FlightDto;
import askar.controlwork9.dto.TicketDto;
import askar.controlwork9.model.Flight;
import askar.controlwork9.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface FlightService {
    Page<Flight> search(String dep, String arr, String from, String to, int page);

    Page<FlightDto> searchDto(String dep, String arr, String from, String to, int page);

    List<Flight> getByCompany(String email);

    List<FlightDto> getByCompanyDto(String email);

    @Transactional
    void create(String depCity, String arrCity,
                String depTime, String arrTime,
                String companyEmail);

    void saveTicket(Flight f, String seat, String cls, BigDecimal price);

    Flight getById(Long id);

    List<Ticket> getTickets(Long flightId);

    List<TicketDto> getTicketsDto(Long flightId);

    long countFree(Flight f);

    FlightDto toDto(Flight f);
}
