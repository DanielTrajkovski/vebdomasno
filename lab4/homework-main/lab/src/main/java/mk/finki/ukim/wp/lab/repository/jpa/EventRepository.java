package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.events.EventTarget;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    List<Event> findAllByNameLikeOrDescriptionLike(String name,String description);
    Optional<Event> findByName(String name);
    void deleteByName(String name);
}
