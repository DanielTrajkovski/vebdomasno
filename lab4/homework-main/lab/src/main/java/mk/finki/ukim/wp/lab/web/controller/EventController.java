package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.Location;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.service.EventService;
import mk.finki.ukim.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest req) {
        List<Event> events = eventService.listAll();
        List<Location> locations=locationService.findAll();
        model.addAttribute("location", locations);
        String searchText = req.getParameter("searchText");
        String minRatingStr = req.getParameter("minRating");
        String locationID= req.getParameter("location");

        if (locationID != null && !locationID.trim().isEmpty()) {
            Long locationId=Long.parseLong(locationID);
            events = eventService.findByLocation(locationId);
        }

        if (searchText != null && !searchText.trim().isEmpty()) {
            events = eventService.searchEvents(searchText);
        }

        if (minRatingStr != null && !minRatingStr.trim().isEmpty()) {
            double minRating = Double.parseDouble(minRatingStr);
            events = events.stream().filter(event -> event.getPopularityScore() >= minRating).toList();
        }


        model.addAttribute("events", events);
        model.addAttribute("error", error);
        return "listEvents";
    }

    @GetMapping("/add-form")
    public String addEventPage(Model model) {
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("location", locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationID) {
        Event event = new Event(name, description, popularityScore, 1, locationService.findById(locationID).get());
        eventService.saveEvents(event);
        return "redirect:/events";
    }

    @GetMapping("/edit/{eventId}")
    public String getEditEventForm(@PathVariable Long eventId, Model model) {
        Optional<Event> optionalEvent = this.eventService.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            List<Location> locations = this.locationService.findAll();
            model.addAttribute("location", locations);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }




}