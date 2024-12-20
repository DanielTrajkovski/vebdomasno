package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLocationRepository {
    public List<Location> findAll() {
        return DataHolder.locationList;
    }

    public Optional<Location> findById(Long id) {
        return  DataHolder.locationList.stream().filter(l->l.getId().equals(id)).findFirst();

    }

}
