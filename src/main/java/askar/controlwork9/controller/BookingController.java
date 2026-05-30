package askar.controlwork9.controller;

import askar.controlwork9.dto.TicketDto;
import askar.controlwork9.service.BookingService;
import askar.controlwork9.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final FlightService flightService;

    @PostMapping("/book/{ticketId}")
    public String book(@PathVariable Long ticketId, Principal principal) {
        bookingService.book(ticketId, principal.getName());
        return "redirect:/";
    }

    @GetMapping("/my")
    public String myBookings(Model model, Principal principal) {
        model.addAttribute("bookings", bookingService.getUserBookingsDto(principal.getName()));
        return "profile/booking";
    }

    @GetMapping("/tickets/{flightId}")
    public String ticket(@PathVariable Long flightId, Model model) {
        model.addAttribute("tickets", flightService.getTicketsDto(flightId));
        return "ticket";
    }
}
