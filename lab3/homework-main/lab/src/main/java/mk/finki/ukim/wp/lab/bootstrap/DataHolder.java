package mk.finki.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static List<EventBooking> bookingList = null;
    public static List<Location> locationList = null;
    public static List<User> users = null;


    @PostConstruct
    public void init() {
        events = new ArrayList<>();
        events.add(new Event("brucoska", "party", 2.0, 5, new Location(1L, "Skopje", "232", "232", "323")));
        events.add(new Event("Event1", "dis1", 4.2, 2, new Location(2L, "Kumanovo", "232", "232", "323")));
        events.add(new Event("Event2", "dis2", 4.2, 6, new Location(3L, "Bitola", "232", "232", "323")));
        events.add(new Event("Event3", "dis3", 4.4, 5, new Location(4L, "Ohrid", "232", "232", "323")));
        events.add(new Event("Oppenheimer", "movie", 3.2, 9, new Location(5L, "Tetovo", "232", "232", "323")));
        events.add(new Event("Barbie", "movie", 5.0, 8, new Location(1L, "Skopje", "232", "232", "323")));
        events.add(new Event("Titanic", "movie", 2.4, 7, new Location(2L, "Kumanovo", "232", "232", "323")));
        events.add(new Event("Christmas-party", "party", 2.0, 6, new Location(3L, "Bitola", "232", "232", "323")));
        events.add(new Event("game_night", "entertainment", 4.2, 7, new Location(4L, "Ohrid", "232", "232", "323")));
        events.add(new Event("stnad_up_show0", "entertainment", 4.2, 3, new Location(5L, "Tetovo", "232", "232", "323")));


        bookingList = new ArrayList<>();

        locationList = new ArrayList<>();
        locationList.add(new Location(1L, "Skopje", "232", "232", "323"));
        locationList.add(new Location(2L, "Kumanovo", "232", "232", "323"));
        locationList.add(new Location(3L, "Bitola", "232", "232", "323"));
        locationList.add(new Location(4L, "Ohird", "232", "232", "323"));
        locationList.add(new Location(5L, "Tetovo", "232", "232", "323"));

        users = new ArrayList<>();
        users.add(new User("daniel", "dt", "Daniel ", "Trajkovski"));


    }
}
