package de.tjackiz.entertainmentService.service.impl;

import de.tjackiz.entertainmentService.model.Actor;
import de.tjackiz.entertainmentService.repository.ActorRepository;
import de.tjackiz.entertainmentService.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public Actor updateActor(UUID id, Actor actor) {
        actor.setId(id);
        return actorRepository.save(actor);
    }

    @Override
    public Set<Actor> getActorSet() {
        return StreamSupport.stream(actorRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public void deleteActor(UUID id) {
        actorRepository.deleteById(id);
    }
}
