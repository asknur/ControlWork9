package askar.controlwork9.repository;

import askar.controlwork9.model.Flight;
import askar.controlwork9.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByFlight(Flight flight);
    long countByFlightAndIsBookedFalse(Flight flight);
}
