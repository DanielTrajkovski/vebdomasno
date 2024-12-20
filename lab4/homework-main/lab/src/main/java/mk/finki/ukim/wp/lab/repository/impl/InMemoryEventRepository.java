package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class InMemoryEventRepository {
    public List<Event> findAll() {
        return DataHolder.events;
    }
    public List<Event> searchEvents(String text){
        return DataHolder.events.stream().filter(e-> e.getDescription().contains(text)||e.getName().contains(text)).toList();

    }
    public Optional<Event> findById(Long id) {
        return DataHolder.events.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
    public Optional<Event> saveEvent(Event event){
        DataHolder.events.removeIf(i->i.getName().equals(event.getName()));
        DataHolder.events.add(event);
        return Optional.of(event);

    }
    public void deleteById(Long id) {
        DataHolder.events.removeIf(i -> i.getId().equals(id));
    }

}



