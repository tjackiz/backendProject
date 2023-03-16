package de.tjackiz.entertainmentService.service;

import de.tjackiz.entertainmentService.model.AbstractMovie;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Set;
import java.util.UUID;

@Validated
public interface MovieService {

    AbstractMovie getMovieById(@Valid UUID id);

    UUID createMovie(@Valid AbstractMovie movie);

    AbstractMovie updateMovie(@Valid UUID id, @Valid AbstractMovie movie);

    Set<AbstractMovie> getMovieSet();

    void deleteMovie(@Valid UUID id);
}
