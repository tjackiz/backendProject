package de.tjackiz.entertainmentService.controller;

import de.tjackiz.entertainmentService.model.Actor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable UUID id) {
        Actor actor = new Actor();
        actor.setBirthdate(new Date());
        actor.setForename("harald");
        actor.setSurname("haraldsen");
        actor.setImdbRating(5);
        actor.setId(id);
        return ResponseEntity.ok(actor);
    }

    @PostMapping
    public ResponseEntity<UUID> createActor(@RequestBody Actor actor) {
        System.out.println("\t### actor: " + actor.toString());
        return new ResponseEntity<>(UUID.randomUUID(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateActor(@PathVariable UUID id, @RequestBody Actor actor) {
        System.out.println("\t### updated actor: " + actor.toString());
        // TODO service call
        // TODO service could load object from db and overwrite all fields or at least id property with new value und write to db
        // TODO OR assign id and write directly to db, avoid reading request
        return ResponseEntity.ok(UUID.randomUUID());
    }
}
