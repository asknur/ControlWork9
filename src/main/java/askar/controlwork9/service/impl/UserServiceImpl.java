package askar.controlwork9.service.impl;

import askar.controlwork9.dto.UserDto;
import askar.controlwork9.model.User;
import askar.controlwork9.repository.BookingRepository;
import askar.controlwork9.repository.TicketRepository;
import askar.controlwork9.repository.UserRepository;
import askar.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(String email, String password, String fullName) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email is booked");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullName);
        user.setAccountType("ROLE_USER");
        user.setEnabled(true);
        userRepository.save(user);
        log.info("Registered: {}", email);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public UserDto getDtoByEmail(String email) {
        return toDto(getByEmail(email));
    }

    @Override
    public List<User> getAllCompanies() {
        return userRepository.findByAccountType("ROLE_COMPANY");
    }

    @Override
    public List<UserDto> getAllCompaniesDto() {
        return getAllCompanies().stream().map(this::toDto).toList();
    }

    @Override
    public void createCompany(String name, String email, String password) {
        User company = new User();
        company.setCompanyName(name);
        company.setEmail(email);
        company.setPassword(passwordEncoder.encode(password));
        company.setAccountType("ROLE_COMPANY");
        company.setEnabled(true);
        userRepository.save(company);
        log.info("Created company: {}", name);
    }

    @Override
    public void getEnable(Long id, BookingRepository bookingRepository,
                          TicketRepository ticketRepository) {
        User company = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (company.getEnabled()) {
            boolean hasBookings = ticketRepository.findAll().stream()
                    .filter(t -> t.getFlight().getCompany().getId().equals(id))
                    .anyMatch(t -> bookingRepository.existsByTicket(t));
            if (hasBookings) {
                throw new RuntimeException("cant freeze");
            }
        }
        company.setEnabled(!company.getEnabled());
        userRepository.save(company);
    }

    @Override
    public void saveLogo(MultipartFile file, String email) throws IOException {
        User company = getByEmail(email);
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads/logos/" + filename);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        company.setLogo(filename);
        userRepository.save(company);
    }

    public UserDto toDto(User u) {
        UserDto dto = new UserDto();
        dto.setId(u.getId());
        dto.setEmail(u.getEmail());
        dto.setFullName(u.getFullName());
        dto.setCompanyName(u.getCompanyName());
        dto.setLogo(u.getLogo());
        dto.setAccountType(u.getAccountType());
        dto.setEnabled(u.getEnabled());
        return dto;
    }


}
