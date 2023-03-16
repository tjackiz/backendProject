package de.tjackiz.gatewayService.service.entertainmentService.impl;

import de.tjackiz.gatewayService.model.entertainmentService.actor.Actor;
import de.tjackiz.gatewayService.service.entertainmentService.ActorService;
import de.tjackiz.gatewayService.service.webClientService.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class ActorServiceImpl implements ActorService {

    private final WebClientService webClientService;

    @Autowired
    public ActorServiceImpl(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @Override
    public Actor getActorById(UUID id) {

        String uri = "http://localhost:8082/actors/" + id.toString();
        Mono<Actor> mono = webClientService.get(uri, new ParameterizedTypeReference<Actor>() {
        });

        return mono.block();
    }

    @Override
    public UUID createActor(Actor actor) {
        String uri = "http://localhost:8082/actors";
        Mono<UUID> mono = webClientService.post(uri, actor, new ParameterizedTypeReference<UUID>() {
        });

        return mono.block();
    }

    @Override
    public Actor updateActor(UUID id, Actor actor) {
        String uri = "http://localhost:8082/actors/" + id.toString();
        Mono<Actor> mono = webClientService.put(uri, actor, new ParameterizedTypeReference<Actor>() {
        });

        return mono.block();
    }

    // TODO replace with pagination
    @Override
    public Set<Actor> getActorSet() {
        String uri = "http://localhost:8082/actors";
        Mono<Set<Actor>> mono = webClientService.get(uri, new ParameterizedTypeReference<Set<Actor>>() {
        });

        return mono.block();
    }

    @Override
    public void deleteActor(UUID id) {
        String uri = "http://localhost:8082/actors/" + id.toString();
        webClientService.delete(uri);
    }
}
