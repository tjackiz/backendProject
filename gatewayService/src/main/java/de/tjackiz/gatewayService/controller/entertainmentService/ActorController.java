package de.tjackiz.gatewayService.controller.entertainmentService;

import de.tjackiz.gatewayService.model.entertainmentService.actor.Actor;
import de.tjackiz.gatewayService.service.entertainmentService.ActorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getActors() {
        List<Actor> actorList = actorService.getActorList();
        return actorList == null || actorList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(actorList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable UUID id) {
        Actor actor = actorService.getActorById(id);
        return actor == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(actor);
    }

    @PostMapping
    public ResponseEntity<UUID> createActor(@RequestBody Actor actor) {
        UUID id = actorService.createActor(actor);
        return id == null ? ResponseEntity.noContent().build() : new ResponseEntity(id, HttpStatus.CREATED);
    }

    // TODO what return type should be used here ?
    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@Valid @PathVariable UUID id, @RequestBody Actor actor) {
        actor = actorService.updateActor(id, actor);
        return actor == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(actor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteActor(@Valid @PathVariable UUID id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
