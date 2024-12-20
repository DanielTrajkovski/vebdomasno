package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.EventBooking;

public interface EventBookingService{
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets);

}

