package de.tjackiz.entertainmentService.repository;

import de.tjackiz.entertainmentService.model.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActorRepository extends CrudRepository<Actor, UUID> {
}
