package de.tjackiz.entertainmentService.service.impl;

import de.tjackiz.entertainmentService.model.Actor;
import de.tjackiz.entertainmentService.repository.ActorRepository;
import de.tjackiz.entertainmentService.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public UUID createActor(Actor actor) {
        actor = actorRepository.save(actor);
        return actor.getId();
    }

    public Actor getActorById(UUID id) {
        return actorRepository.findById(id).orElse(null);
    }
}
