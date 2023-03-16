package de.tjackiz.entertainmentService.controller;

import de.tjackiz.entertainmentService.model.AbstractMovie;
import de.tjackiz.entertainmentService.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Set<AbstractMovie>> getMovies() {
        Set<AbstractMovie> movieSet = movieService.getMovieSet();
        return movieSet == null || movieSet.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movieSet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbstractMovie> getMovieById(@PathVariable UUID id) {
        AbstractMovie movie = movieService.getMovieById(id);
        return movie == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<UUID> createMovie(@RequestBody AbstractMovie movie) {
        UUID id = movieService.createMovie(movie);
        return id == null ? ResponseEntity.noContent().build() : new ResponseEntity(id, HttpStatus.CREATED);
    }

    // TODO what return type should be used here ?
    @PutMapping("/{id}")
    public ResponseEntity<AbstractMovie> updateMovie(@PathVariable UUID id, @RequestBody AbstractMovie movie) {
        movie = movieService.updateMovie(id, movie);
        return movie == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
