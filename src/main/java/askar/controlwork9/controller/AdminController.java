package askar.controlwork9.controller;

import askar.controlwork9.repository.BookingRepository;
import askar.controlwork9.repository.TicketRepository;
import askar.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;

    @GetMapping
    public String panel(Model model) {
        model.addAttribute("companies", userService.getAllCompaniesDto());
        return "admin/panel";
    }

    @GetMapping("/companies/create")
    public String createForm() {
        return "admin/company-create";
    }

    @PostMapping("/companies/create")
    public String create(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password) {
        userService.createCompany(name, email, password);
        return "redirect:/admin";
    }

    @PostMapping("/companies/toggle/{id}")
    public String toggle(@PathVariable Long id, Model model) {
        try {
            userService.getEnable(id, bookingRepository, ticketRepository);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("companies", userService.getAllCompaniesDto());
            return "admin/panel";
        }
        return "redirect:/admin";
    }
}
