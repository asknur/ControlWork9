package askar.controlwork9.repository;

import askar.controlwork9.model.Booking;
import askar.controlwork9.model.Ticket;
import askar.controlwork9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    boolean existsByTicket(Ticket ticket);
}
