package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    public List<Location> findAll();

    public Optional<Location> findById(Long id);

}
