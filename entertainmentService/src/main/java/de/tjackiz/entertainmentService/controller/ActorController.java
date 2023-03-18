package de.tjackiz.entertainmentService.controller;

import de.tjackiz.entertainmentService.model.Actor;
import de.tjackiz.entertainmentService.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/v1/actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable UUID id) {
        Actor actor = actorService.getActorById(id);
        return actor == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(actor);
    }

    @GetMapping
    public ResponseEntity<Set<Actor>> getActors() {
        Set<Actor> actorSet = actorService.getActorSet();
        return actorSet == null || actorSet.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(actorSet);
    }

    @PostMapping
    public ResponseEntity<UUID> createActor(@RequestBody Actor actor) {
        UUID id = actorService.createActor(actor);
        return id == null ? ResponseEntity.noContent().build() : new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateActor(@PathVariable UUID id, @RequestBody Actor actor) {
        System.out.println("\t### updated actor: " + actor.toString());
        // TODO service call
        // TODO service could load object from db and overwrite all fields or at least id property with new value und write to db
        // TODO OR assign id and write directly to db, avoid reading request
        return ResponseEntity.ok(UUID.randomUUID());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteActor(@PathVariable UUID id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
