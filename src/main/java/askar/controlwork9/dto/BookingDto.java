package askar.controlwork9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private String departureTime;
    private String seatNumber;
    private String seatClass;
    private BigDecimal price;
    private String bookedAt;
}
