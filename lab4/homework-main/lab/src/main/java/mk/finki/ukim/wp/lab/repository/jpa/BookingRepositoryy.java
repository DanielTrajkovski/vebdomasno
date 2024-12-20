package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepositoryy extends JpaRepository<EventBooking,Long> {
}
