package de.tjackiz.gatewayService.service.entertainmentService.impl;

import de.tjackiz.gatewayService.model.entertainmentService.movie.AbstractMovie;
import de.tjackiz.gatewayService.service.entertainmentService.MovieService;
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
public class MovieServiceImpl implements MovieService {

    private final WebClientService webClientService;

    @Autowired
    public MovieServiceImpl(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @Override
    public AbstractMovie getMovieById(UUID id) {
        String uri = "http://localhost:8082/movies/" + id.toString();
        Mono<AbstractMovie> mono = webClientService.get(uri, new ParameterizedTypeReference<AbstractMovie>() {
        });

        return mono.block();
    }

    @Override
    public UUID createMovie(AbstractMovie movie) {
        String uri = "http://localhost:8082/movies";
        Mono<UUID> mono = webClientService.post(uri, movie, new ParameterizedTypeReference<UUID>() {
        });

        return mono.block();
    }

    @Override
    public AbstractMovie updateMovie(UUID id, AbstractMovie movie) {
        String uri = "http://localhost:8082/movies/" + id.toString();
        Mono<AbstractMovie> mono = webClientService.put(uri, movie, new ParameterizedTypeReference<AbstractMovie>() {
        });

        return mono.block();
    }

    // TODO replace with pagination
    @Override
    public Set<AbstractMovie> getMovieSet() {
        String uri = "http://localhost:8082/movies";
        Mono<Set<AbstractMovie>> mono = webClientService.get(uri, new ParameterizedTypeReference<Set<AbstractMovie>>() {
        });

        return mono.block();
    }

    @Override
    public void deleteMovie(UUID id) {
        String uri = "http://localhost:8082/movies/" + id.toString();
        webClientService.delete(uri);
    }
}
