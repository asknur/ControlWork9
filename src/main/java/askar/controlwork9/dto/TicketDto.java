package askar.controlwork9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long id;
    private String seatNumber;
    private String seatClass;
    private BigDecimal price;
    private Boolean booked;
}
