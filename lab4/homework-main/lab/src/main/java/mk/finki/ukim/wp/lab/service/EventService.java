package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
     Optional<Event> saveEvents(Event event);
    Optional<Event> findById(Long id);
    public void deleteById(Long id);
    List<Event> findByLocation(Long LocationID);
}
