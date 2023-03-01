package de.tjackiz.entertainmentService.controller;

import de.tjackiz.entertainmentService.model.Actor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
