package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.EventBooking;
import mk.finki.ukim.wp.lab.model.exeptions.NoTicketsExeption;
import mk.finki.ukim.wp.lab.repository.impl.BookingRepository;
import mk.finki.ukim.wp.lab.repository.impl.InMemoryEventRepository;
import mk.finki.ukim.wp.lab.repository.jpa.EventRepository;
import mk.finki.ukim.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EventServiceBookingImpl implements EventBookingService {
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;

    public EventServiceBookingImpl(BookingRepository bookingRepository, EventRepository eventRepository) {
        this.bookingRepository = bookingRepository;
        this.eventRepository=eventRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
       String eventNameMod="%"+eventName+"%";
        List<Event> list=eventRepository.findAllByNameLikeOrDescriptionLike(eventNameMod,eventNameMod);
        Event available=list.get(0);

        if(available.getAvailable()<numberOfTickets){
         throw new NoTicketsExeption();
        }
        Optional<Event> event= this.eventRepository.findByName(eventName);
        event.get().setAvailable(event.get().getAvailable()-1);
        eventRepository.save(event.get());

        return bookingRepository.book(new EventBooking(eventName,attendeeName,attendeeAddress,numberOfTickets));
    }
}
