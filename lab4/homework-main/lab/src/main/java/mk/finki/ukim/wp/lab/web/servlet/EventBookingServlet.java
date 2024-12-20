package mk.finki.ukim.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.service.EventBookingService;
import mk.finki.ukim.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

//@WebServlet(name = "BookingServlet", value = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine springTemplateEngine;
    private final EventService eventService;

    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.springTemplateEngine = springTemplateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        String attendeeName = req.getParameter("attendeeName");
        String attendeeAddress = req.getRemoteAddr();
        String eventName = req.getParameter("eventName");
        int numTickets = Integer.parseInt(req.getParameter("numTickets"));

        try {
            eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, (long) numTickets);
        } catch (RuntimeException e) {
            context.setVariable("hasError", true);
            context.setVariable("error", e.getMessage());
            context.setVariable("toprint",false);
            springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());

        }

        List<Event> list=eventService.searchEvents(eventName);
        Event available=list.get(0);
        if(available.getAvailable()>=numTickets) {
            context.setVariable("toprint",true);
            context.setVariable("attendeeName", attendeeName);
            context.setVariable("attendeeAddress", attendeeAddress);
            context.setVariable("event", eventName);
            context.setVariable("numTickets", numTickets);

            springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());

        }
    }
}
