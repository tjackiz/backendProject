package de.tjackiz.gatewayService.controller.entertainmentService;

import de.tjackiz.gatewayService.model.entertainmentService.actor.Actor;
import de.tjackiz.gatewayService.service.entertainmentService.ActorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

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

        // TODO rework if writing requests are async, no distinguish needed anymore
        // add location header
        if (id != null) {
            String location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUriString();
            return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, location).body(id);
        }

        return ResponseEntity.noContent().build();
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
