package askar.controlwork9.controller;

import askar.controlwork9.dto.FlightDto;
import askar.controlwork9.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final FlightService flightService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String departureCity,
                        @RequestParam(defaultValue = "") String arrivalCity,
                        @RequestParam(defaultValue = "") String dateFrom,
                        @RequestParam(defaultValue = "") String dateTo,
                        @RequestParam(defaultValue = "0") int page,
                        Model model) {
        Page<FlightDto> flightPage = flightService.searchDto(departureCity, arrivalCity, dateFrom, dateTo, page);
        model.addAttribute("flights", flightPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", flightPage.getTotalPages());
        model.addAttribute("departureCity", departureCity);
        model.addAttribute("arrivalCity", arrivalCity);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "index";
    }
}
