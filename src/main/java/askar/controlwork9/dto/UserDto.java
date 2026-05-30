package askar.controlwork9.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @Email
    @NotBlank(message = "should be not empty")
    private String email;

    @NotBlank(message = "should be not empty")
    private String fullName;

    private String companyName;
    private String logo;
    private String accountType;
    private Boolean enabled;

    @NotBlank(message = "should be not empty")
    @Size(min = 2, max = 10, message = "Lenght must be >= 2 and <= 10")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$")
    private String password;
}
