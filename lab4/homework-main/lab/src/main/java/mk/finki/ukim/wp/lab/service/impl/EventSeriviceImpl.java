package mk.finki.ukim.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.repository.jpa.EventRepository;
import mk.finki.ukim.wp.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventSeriviceImpl  implements EventService {
    private final EventRepository eventRep;
    private  final LocationRepository locationRepository;

    public EventSeriviceImpl(EventRepository eventRep, LocationRepository locationRepository) {
        this.eventRep = eventRep;
        this.locationRepository = locationRepository;
    }


    @Override
    public List<Event> listAll() {
       return eventRep.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        text ="%"+text+"%";
     return  eventRep.findAllByNameLikeOrDescriptionLike(text,text);
    }

    @Override
    @Transactional
    public Optional<Event> saveEvents(Event event) {
    this.eventRep.deleteByName(event.getName());

    return Optional.of(this.eventRep.save(event));

    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRep.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.eventRep.deleteById(id);
    }

    @Override
    public List<Event> findByLocation(Long LocationID) {
       return this.eventRep.findAllByLocation_Id(LocationID);

    }
}
