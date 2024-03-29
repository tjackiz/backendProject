package de.tjackiz.gatewayService.service.entertainmentService;

import de.tjackiz.gatewayService.model.entertainmentService.actor.Actor;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Set;
import java.util.UUID;

@Validated
public interface ActorService {
    Actor getActorById(@Valid UUID id);

    UUID createActor(@Valid Actor actor);

    Actor updateActor(@Valid UUID id, @Valid Actor actor);

    Set<Actor> getActorSet();

    void deleteActor(@Valid UUID id);
}
