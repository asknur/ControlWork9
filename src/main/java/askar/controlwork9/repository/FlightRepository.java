package askar.controlwork9.repository;

import askar.controlwork9.model.Flight;
import askar.controlwork9.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByCompany(User user);

    @Query("SELECT f FROM Flight f WHERE " +
            "(:dep = '' OR f.departureCity LIKE %:dep%) AND " +
            "(:arr = '' OR f.arrivalCity LIKE %:arr%) AND " +
            "(:from IS NULL OR f.departureTime >= :from) AND " +
            "(:to IS NULL OR f.departureTime <= :to)")
    Page<Flight> search(@Param("dep") String dep,
                        @Param("arr") String arr,
                        @Param("from") LocalDateTime from,
                        @Param("to") LocalDateTime to,
                        Pageable pageable);
}
