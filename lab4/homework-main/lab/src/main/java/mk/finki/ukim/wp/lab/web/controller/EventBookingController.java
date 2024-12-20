package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.service.EventBookingService;
import mk.finki.ukim.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final EventService eventService;

    public EventBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;

        this.eventService = eventService;
    }

    @PostMapping("/confirmation")
    public String bookingPage(HttpServletRequest req, Model model) {
        String attendeeName = req.getParameter("attendeeName");
        String attendeeAddress = req.getRemoteAddr();
        String eventName = req.getParameter("eventName");
        int numTickets = Integer.parseInt(req.getParameter("numTickets"));

        try {
            eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, (long) numTickets);
        } catch (RuntimeException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("toprint", false);
        }
        List<Event> list = eventService.searchEvents(eventName);
        Event available = list.get(0);
        if (available.getAvailable() >= numTickets) {
            model.addAttribute("attendeeName", attendeeName);
            model.addAttribute("attendeeAddress", attendeeAddress);
            model.addAttribute("event", eventName);
            model.addAttribute("numTickets", numTickets);
            model.addAttribute("toprint", true);
        }
        return "bookingConfirmation";
    }

}
