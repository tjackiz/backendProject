package de.tjackiz.entertainmentService.service;

import de.tjackiz.entertainmentService.model.Actor;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Set;
import java.util.UUID;

@Validated
public interface ActorService {

    UUID createActor(@Valid Actor actor);

    Actor getActorById(@Valid UUID id);

    Actor updateActor(@Valid UUID id, @Valid Actor actor);

    Set<Actor> getActorSet();

    void deleteActor(@Valid UUID id);
}
