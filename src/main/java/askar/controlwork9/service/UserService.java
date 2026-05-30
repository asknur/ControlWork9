package askar.controlwork9.service;

import askar.controlwork9.dto.UserDto;
import askar.controlwork9.exception.UserNotFoundException;
import askar.controlwork9.model.User;
import askar.controlwork9.repository.BookingRepository;
import askar.controlwork9.repository.TicketRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void register(String email, String password, String fullName);

    User getByEmail(String email);

    UserDto getDtoByEmail(String email);

    List<User> getAllCompanies();

    List<UserDto> getAllCompaniesDto();

    void createCompany(String name, String email, String password);

    void getEnable(Long id, BookingRepository bookingRepository,
                   TicketRepository ticketRepository);

    void saveLogo(MultipartFile file, String email) throws IOException;
}
