package askar.controlwork9.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    private Long id;
    private String flightNumber;

    @NotBlank(message = "Set outgoing city")
    private String departureCity;

    @NotBlank(message = "Set arriving city")
    private String arrivalCity;

    @NotBlank(message = "Set outgoing time")
    private String departureTime;

    @NotBlank(message = "Set arriving city")
    private String arrivalTime;
    private String companyName;
    private String companyLogo;
    private Long freeSeats;
}
