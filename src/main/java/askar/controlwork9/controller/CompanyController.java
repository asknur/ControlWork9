package askar.controlwork9.controller;

import askar.controlwork9.service.FlightService;
import askar.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final FlightService flightService;
    private final UserService userService;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("company", userService.getDtoByEmail(principal.getName()));
        model.addAttribute("flights", flightService.getByCompanyDto(principal.getName()));
        return "company/profile";
    }

    @GetMapping("/flights/create")
    public String createForm() {
        return "company/flight-create";
    }

    @PostMapping("/flights/create")
    public String create(@RequestParam String departureCity,
                         @RequestParam String arrivalCity,
                         @RequestParam String departureTime,
                         @RequestParam String arrivalTime,
                         Principal principal) {
        flightService.create(departureCity, arrivalCity, departureTime, arrivalTime, principal.getName());
        return "redirect:/company/profile";
    }

    @GetMapping("/flights/{id}/tickets")
    public String tickets(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.toDto(flightService.getById(id)));
        model.addAttribute("tickets", flightService.getTicketsDto(id));
        return "company/tickets";
    }

    @PostMapping("/logo")
    public String logo(@RequestParam MultipartFile logo, Principal principal) throws IOException {
        userService.saveLogo(logo, principal.getName());
        return "redirect:/company/profile";
    }

    @GetMapping("/logos/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getLogo(@PathVariable String filename) throws Exception {
        Path path = Paths.get("uploads/logos/" + filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new UrlResource(path.toUri()));
    }
}
