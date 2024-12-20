package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.EventBooking;
import mk.finki.ukim.wp.lab.repository.jpa.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class BookingRepository {
    private final EventRepository eventRepository;

    public BookingRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public List<EventBooking> findAll() {
        return DataHolder.bookingList;
    }

    public EventBooking book(EventBooking booking) {
        DataHolder.bookingList.add(booking);
        return booking;
    }
}
