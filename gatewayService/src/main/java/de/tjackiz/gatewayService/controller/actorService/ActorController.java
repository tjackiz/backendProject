package de.tjackiz.gatewayService.controller.actorService;

import de.tjackiz.gatewayService.model.actorService.Actor;
import de.tjackiz.gatewayService.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<Actor> insert(Actor actor) {
        return null;
    }

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
