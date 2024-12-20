package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
public class Event {
    private String name;
    private String description;
    private double popularityScore;
    private int available;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Location location;




    public Event(String name, String description, double popularityScore, int available, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.available = available;
        this.location=location;


    }

}



