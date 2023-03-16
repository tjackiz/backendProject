package de.tjackiz.entertainmentService.repository;

import de.tjackiz.entertainmentService.model.AbstractMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends CrudRepository<AbstractMovie, UUID> {
}
