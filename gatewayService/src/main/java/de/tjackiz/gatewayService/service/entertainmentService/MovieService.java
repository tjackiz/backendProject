package de.tjackiz.gatewayService.service.entertainmentService;

import de.tjackiz.gatewayService.model.entertainmentService.movie.AbstractMovie;
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
